package com.mindblow.transaction.statemachine;

import com.mindblow.transaction.Transaction;
import com.mindblow.transaction.payment.Payment;

public interface TransactionStateMachineFactory {

	public TransactionStateMachine createBy(Transaction transaction);

	public TransactionStateMachine createBy(Payment payment);
}
