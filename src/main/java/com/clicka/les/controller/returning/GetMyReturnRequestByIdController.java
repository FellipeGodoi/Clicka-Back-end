package com.clicka.les.controller.returning;


import com.clicka.les.dto.returning.ReturnResponseDTO;
import com.clicka.les.service.returning.GetMyReturnRequestByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/returning")
@RequiredArgsConstructor
public class GetMyReturnRequestByIdController {

    private final GetMyReturnRequestByIdService service;

    @GetMapping("/{id}")
    public ReturnResponseDTO getById(
            @PathVariable String id,
            Authentication authentication
    ) {

        UUID userId =
                UUID.fromString(authentication.getName());

        UUID returnId = UUID.fromString(id);

        return service.execute(returnId, userId);
    }
}