package com.clicka.les.controller.admin.returning;

import com.clicka.les.service.admin.returning.ReceiveReturnRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/returning")
@RequiredArgsConstructor
public class ReceiveReturnRequestController {

    private final ReceiveReturnRequestService service;

    @PatchMapping("/{id}/received")
    public void receive(
            @PathVariable String id
    ) {

        service.execute(
                UUID.fromString(id)
        );
    }
}