package com.clicka.les.controller.admin.returning;

import com.clicka.les.dto.admin.returning.FinishReturnRequestDTO;
import com.clicka.les.service.admin.returning.FinishReturnRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/returning")
@RequiredArgsConstructor
public class FinishReturnRequestController {

    private final FinishReturnRequestService service;

    @PatchMapping("/{id}/finish")
    public void finish(
            @PathVariable String id,
            @RequestBody FinishReturnRequestDTO dto
    ) {

        service.execute(
                UUID.fromString(id),
                dto
        );
    }
}