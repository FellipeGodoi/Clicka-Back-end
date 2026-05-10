package com.clicka.les.service.returning;

import com.clicka.les.config.exceptions.BadRequestException;
import com.clicka.les.dto.returning.ReturnResponseDTO;
import com.clicka.les.entity.returning.ReturnRequest;

import com.clicka.les.repository.returning.ReturnRequestRepository;
import com.clicka.les.utils.mappers.ReturnMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetMyReturnRequestByIdService {

    private final ReturnRequestRepository repository;

    public ReturnResponseDTO execute(
            UUID returnId,
            UUID userId
    ) {

        ReturnRequest request = repository.findById(returnId)
                .orElseThrow(() ->
                        new BadRequestException(
                                "Devolução não encontrada"
                        ));

        if (!request.getOrder()
                .getUser()
                .getId()
                .equals(userId)) {

            throw new BadRequestException(
                    "Acesso negado"
            );
        }

        return ReturnMapper.toDTO(request);
    }
}