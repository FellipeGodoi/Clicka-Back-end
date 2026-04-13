package com.clicka.les.controller.user;

import com.clicka.les.dto.user.UserListResponseDTO;
import com.clicka.les.service.user.UserFindService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserFindAllController {

    private final UserFindService userFindService;

@GetMapping
public ResponseEntity<Page<UserListResponseDTO>> findAll(
        @RequestParam(required = false) String search,
        @RequestParam(required = false) Boolean isActive,
        Pageable pageable
) {

    return ResponseEntity.ok(
            userFindService.findAll(search, isActive, pageable)
    );
}
}

// GET /users?search=fellipe&isActive=true&page=0&size=5

// GET /users?isActive=true

// GET /users?sort=name,asc
// GET /users?sort=email,desc