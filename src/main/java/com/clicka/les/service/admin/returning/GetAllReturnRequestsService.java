package com.clicka.les.service.admin.returning;

import com.clicka.les.dto.returning.ReturnResponseDTO;
import com.clicka.les.entity.enums.ReturnStatus;
import com.clicka.les.repository.returning.ReturnRequestRepository;
import com.clicka.les.utils.mappers.ReturnMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllReturnRequestsService {

    private final ReturnRequestRepository repository;

    public Page<ReturnResponseDTO> execute(
            ReturnStatus status,
            String search,
            int page,
            int size
    ) {

        PageRequest pageable = PageRequest.of(page, size);

        if (search != null && !search.isBlank()) {

            if (status != null) {

                return repository
                        .searchReturnsByStatus(
                                search,
                                status,
                                pageable
                        )
                        .map(ReturnMapper::toDTO);
            }

            return repository
                    .searchReturns(search, pageable)
                    .map(ReturnMapper::toDTO);
        }

        if (status != null) {

            return repository
                    .findByStatusOrderByCreatedAtDesc(
                            status,
                            pageable
                    )
                    .map(ReturnMapper::toDTO);
        }

        return repository
                .findAllByOrderByCreatedAtDesc(pageable)
                .map(ReturnMapper::toDTO);
    }
}