package com.clicka.les.controller.admin.returning;

import com.clicka.les.service.admin.returning.ApproveReturnRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/returning")
@RequiredArgsConstructor
public class ApproveReturnRequestController {

    private final ApproveReturnRequestService service;

    @PatchMapping("/{id}/approve")
    public void approve(
            @PathVariable String id
    ) {

        service.execute(
                UUID.fromString(id)
        );
    }
}