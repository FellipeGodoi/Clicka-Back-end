package com.clicka.les.service.returning;


import com.clicka.les.dto.returning.ReturnResponseDTO;
import com.clicka.les.repository.returning.ReturnRequestRepository;
import com.clicka.les.utils.mappers.ReturnMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetMyReturnRequestsService {

    private final ReturnRequestRepository repository;

    public List<ReturnResponseDTO> execute(UUID userId) {

        return repository
                .findByOrderUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(ReturnMapper::toDTO)
                .toList();
    }
}