package com.clicka.les.service.admin.returning;

import com.clicka.les.config.exceptions.BadRequestException;
import com.clicka.les.entity.enums.ReturnStatus;
import com.clicka.les.entity.returning.ReturnRequest;
import com.clicka.les.repository.returning.ReturnRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReceiveReturnRequestService {

    private final ReturnRequestRepository repository;

    @Transactional
    public void execute(UUID returnRequestId) {

        ReturnRequest request = repository.findById(returnRequestId)
                .orElseThrow(() ->
                        new BadRequestException(
                                "Solicitação de devolução não encontrada"
                        ));

        if (request.getStatus() != ReturnStatus.SENT) {
            throw new BadRequestException(
                    "A devolução ainda não foi enviada pelo cliente"
            );
        }

        request.setStatus(ReturnStatus.RECEIVED);

        repository.save(request);
    }
}