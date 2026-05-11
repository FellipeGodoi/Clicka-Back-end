package com.clicka.les.service.admin.returning;

import com.clicka.les.config.exceptions.BadRequestException;
import com.clicka.les.dto.admin.returning.FinishReturnRequestDTO;
import com.clicka.les.entity.enums.ReturnStatus;
import com.clicka.les.entity.returning.ReturnRequest;
import com.clicka.les.repository.returning.ReturnRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinishReturnRequestService {

    private final ReturnRequestRepository repository;

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

            repository.save(request);

            return;
        }

        if (dto.getRejectionReason() == null
                || dto.getRejectionReason().isBlank()) {

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