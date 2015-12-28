package com.mindblow.transaction.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindblow.transaction.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {

}
