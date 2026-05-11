package com.clicka.les.controller.admin.returning;

import com.clicka.les.dto.returning.ReturnResponseDTO;
import com.clicka.les.entity.enums.ReturnStatus;
import com.clicka.les.service.admin.returning.GetAllReturnRequestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/returning")
@RequiredArgsConstructor
public class AdminReturnRequestQueryController {

    private final GetAllReturnRequestsService service;

    @GetMapping
    public Page<ReturnResponseDTO> getAll(

            @RequestParam(required = false)
            ReturnStatus status,

            @RequestParam(required = false)
            String search,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ) {

        return service.execute(
                status,
                search,
                page,
                size
        );
    }
}