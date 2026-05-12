package com.clicka.les.service.admin.returning;

import com.clicka.les.config.exceptions.BadRequestException;
import com.clicka.les.dto.admin.returning.FinishReturnRequestDTO;
import com.clicka.les.entity.User;
import com.clicka.les.entity.enums.ReturnStatus;
import com.clicka.les.entity.returning.ReturnItem;
import com.clicka.les.entity.returning.ReturnRequest;
import com.clicka.les.repository.returning.ReturnRequestRepository;
import com.clicka.les.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinishReturnRequestService {

    private final ReturnRequestRepository repository;
    private final UserRepository userRepository;

    @Transactional
    public void execute(
            UUID returnRequestId,
            FinishReturnRequestDTO dto
    ) {

        ReturnRequest request = repository.findById(returnRequestId)
                .orElseThrow(() ->
                        new BadRequestException(
                                "Solicitação não encontrada"
                        ));

        if (request.getStatus() != ReturnStatus.RECEIVED) {
            throw new BadRequestException(
                    "A devolução ainda não foi recebida"
            );
        }

        if (Boolean.TRUE.equals(dto.getApproved())) {

            request.setStatus(ReturnStatus.APPROVED);

            BigDecimal refundAmount = BigDecimal.ZERO;

            for (ReturnItem item : request.getItems()) {

                BigDecimal itemTotal =
                        item.getOrderItem()
                                .getUnitPrice()
                                .multiply(
                                        BigDecimal.valueOf(
                                                item.getQuantity()
                                        )
                                );

                refundAmount = refundAmount.add(itemTotal);
            }

            User user = request.getOrder().getUser();

            if (user.getCredit() == null) {
                user.setCredit(BigDecimal.ZERO);
            }

            user.setCredit(
                    user.getCredit().add(refundAmount)
            );

            userRepository.save(user);

            repository.save(request);

            return;
        }

        if (
                dto.getRejectionReason() == null
                        || dto.getRejectionReason().isBlank()
        ) {

            throw new BadRequestException(
                    "Informe o motivo da recusa"
            );
        }

        request.setStatus(ReturnStatus.REJECTED);

        request.setRejectionReason(
                dto.getRejectionReason()
        );

        repository.save(request);
    }
}