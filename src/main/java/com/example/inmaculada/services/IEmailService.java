package com.example.inmaculada.services;


import com.example.inmaculada.domain.models.Order;
import com.example.inmaculada.domain.models.Product;
import com.example.inmaculada.domain.models.User;
import jakarta.mail.MessagingException;

public interface IEmailService {
    void sendOrderConfirmationEmail(User user, Order order) throws MessagingException;
    void sendOrderStatusUpdateEmail(Order order) throws MessagingException;
    void sendNewOrderNotificationToAdmin(User admin, Order order) throws MessagingException;
    void sendPasswordResetEmail(User user, String newPassword) throws MessagingException;
    void sendOutOfStockNotificationToAdmin(User admin, Product product) throws MessagingException;
    void sendComprobanteUpdateNotificationToAdmin(User admin, Order order) throws MessagingException;
}
