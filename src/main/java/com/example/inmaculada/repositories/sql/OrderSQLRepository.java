package com.example.inmaculada.repositories.sql;


import com.example.inmaculada.domain.enums.OrderStatus;
import com.example.inmaculada.domain.models.Order;
import com.example.inmaculada.domain.models.User;
import com.example.inmaculada.repositories.IOrderRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderSQLRepository implements IOrderRepository {

    private final IOrderSQLRepository iOrderSQLRepository;

    public OrderSQLRepository(IOrderSQLRepository iOrderSQLRepository) {
        this.iOrderSQLRepository = iOrderSQLRepository;
    }

    @Override
    public Order save(Order order) {
        return iOrderSQLRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return iOrderSQLRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return iOrderSQLRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        iOrderSQLRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return iOrderSQLRepository.existsById(id);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return iOrderSQLRepository.findByStatus(status);
    }


    @Override
    public List<Order> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate) {
        return iOrderSQLRepository.findByDateCreatedBetween(startDate,endDate);
    }

    @Override
    public List<Order> findByUser(User user) {
        return iOrderSQLRepository.findByUser(user);
    }

    @Override
    public Optional<Order> findTopByUserEmailOrderByDateCreatedDesc(String userEmail) {
        return iOrderSQLRepository.findTopByUserEmailOrderByDateCreatedDesc(userEmail);
    }

    @Override
    public List<Order> findAllCreatedInTheLastHours(LocalDateTime hoursBefore) {
        return iOrderSQLRepository.findAllCreatedInTheLastHours(hoursBefore);
    }


}
