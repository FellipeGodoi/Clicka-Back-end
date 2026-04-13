package com.clicka.les.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clicka.les.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    @Query("""
                SELECT u FROM User u
                WHERE
                    (:search IS NULL OR
                        LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                        LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) OR
                        u.cpf LIKE CONCAT('%', :search, '%')
                    )
                AND
                    (:isActive IS NULL OR u.isActive = :isActive)
            """)
    Page<User> searchUsers(
            @Param("search") String search,
            @Param("isActive") Boolean isActive,
            Pageable pageable);

}