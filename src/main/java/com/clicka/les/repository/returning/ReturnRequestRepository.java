package com.clicka.les.repository.returning;

import com.clicka.les.entity.enums.ReturnStatus;
import com.clicka.les.entity.returning.ReturnRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReturnRequestRepository
        extends JpaRepository<ReturnRequest, UUID> {

    List<ReturnRequest> findByOrderUserIdOrderByCreatedAtDesc(
            UUID userId
    );

    Page<ReturnRequest> findAllByOrderByCreatedAtDesc(
            Pageable pageable
    );

    Page<ReturnRequest> findByStatusOrderByCreatedAtDesc(
            ReturnStatus status,
            Pageable pageable
    );

    @Query("""
        SELECT r
        FROM ReturnRequest r
        JOIN r.order o
        JOIN o.user u
        WHERE
            LOWER(CAST(r.id AS string)) LIKE LOWER(CONCAT(:search, '%'))
            OR u.cpf LIKE CONCAT(:search, '%')
        ORDER BY r.createdAt DESC
    """)
    Page<ReturnRequest> searchReturns(
            String search,
            Pageable pageable
    );

    @Query("""
        SELECT r
        FROM ReturnRequest r
        JOIN r.order o
        JOIN o.user u
        WHERE
            (
                LOWER(CAST(r.id AS string)) LIKE LOWER(CONCAT(:search, '%'))
                OR u.cpf LIKE CONCAT(:search, '%')
            )
            AND r.status = :status
        ORDER BY r.createdAt DESC
    """)
    Page<ReturnRequest> searchReturnsByStatus(
            String search,
            ReturnStatus status,
            Pageable pageable
    );
}