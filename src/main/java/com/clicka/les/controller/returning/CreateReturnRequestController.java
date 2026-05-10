package com.clicka.les.controller.returning;

import com.clicka.les.dto.returning.CreateReturnRequestDTO;
import com.clicka.les.service.returning.CreateReturnRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/returning")
@RequiredArgsConstructor
public class CreateReturnRequestController {

    private final CreateReturnRequestService service;

    @PostMapping
    public void create(
            @RequestBody CreateReturnRequestDTO dto,
            Authentication authentication
    ) {

        UUID userId =
                UUID.fromString(authentication.getName());

        service.execute(dto, userId);
    }
}