package com.example.inmaculada.repositories.sql;


import com.example.inmaculada.domain.enums.OrderStatus;
import com.example.inmaculada.domain.models.Order;
import com.example.inmaculada.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderSQLRepository extends JpaRepository<Order,Long> {

    List<Order> findByStatus(OrderStatus status);
    List<Order> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate);
    List<Order> findByUser(User user);
    Optional<Order> findTopByUserEmailOrderByDateCreatedDesc(String userEmail);
    @Query("SELECT o FROM Order o WHERE o.dateCreated >= :hoursBefore")
    List<Order> findAllCreatedInTheLastHours(@Param("hoursBefore") LocalDateTime hoursBefore);
}
