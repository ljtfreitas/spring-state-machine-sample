package com.mindblow.transaction.statemachine.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.annotation.ExtendedStateVariable;

import com.mindblow.transaction.Transaction;
import com.mindblow.transaction.TransactionStatusType;
import com.mindblow.transaction.repository.TransactionRepository;
import com.mindblow.transaction.statemachine.annotation.OnTransactionStateMachine;
import com.mindblow.transaction.statemachine.annotation.OnTransactionStatusChanged;

@OnTransactionStateMachine
public class TransactionStateMachineEventListener {

	@Autowired
	private TransactionRepository transactionRepository;

	@OnTransactionStatusChanged(target = TransactionStatusType.WAITING_PAY)
	public void onWaitingPay(@ExtendedStateVariable("transaction") Transaction transaction) {
		transaction.waitingPay();
		transactionRepository.save(transaction);
	}

	@OnTransactionStatusChanged(target = TransactionStatusType.PAID)
	public void onPaid(@ExtendedStateVariable("transaction") Transaction transaction) {
		transaction.paid();
		transactionRepository.save(transaction);
	}

	@OnTransactionStatusChanged(target = TransactionStatusType.CANCELLED)
	public void onCancelled(@ExtendedStateVariable("transaction") Transaction transaction) {
		transaction.cancelled();
		transactionRepository.save(transaction);
	}
}
