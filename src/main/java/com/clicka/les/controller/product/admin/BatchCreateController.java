package com.clicka.les.controller.product.admin;

import com.clicka.les.dto.product.BatchCreateDTO;
import com.clicka.les.service.product.BatchCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/batches")
@RequiredArgsConstructor
public class BatchCreateController {

    private final BatchCreateService service;

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody BatchCreateDTO dto
    ) {
        service.create(dto);
        return ResponseEntity.ok().build();
    }
}