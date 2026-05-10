package com.clicka.les.controller.returning;


import com.clicka.les.dto.returning.ReturnResponseDTO;
import com.clicka.les.service.returning.GetMyReturnRequestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/my-data/returning")
@RequiredArgsConstructor
public class GetMyReturnRequestsController {

    private final GetMyReturnRequestsService service;

    @GetMapping
    public List<ReturnResponseDTO> getAll(
            Authentication authentication
    ) {

        UUID userId =
                UUID.fromString(authentication.getName());

        return service.execute(userId);
    }
}