package com.clicka.les.controller.user;

import com.clicka.les.dto.user.*;
import com.clicka.les.service.user.UserCreateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCreateController {

    private final UserCreateService userCreateService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UserCreateDTO dto) {
        try {
            UserDetailResponseDTO response = userCreateService.execute(dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

/*{
  "name": "Fellipe Atualizado",
  "cpf": "12345678900",
  "email": "fellipe@email.com",
  "password": "123456",
}*/