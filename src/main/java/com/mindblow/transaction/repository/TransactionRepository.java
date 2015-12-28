package com.mindblow.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindblow.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
