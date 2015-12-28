package com.mindblow.transaction.statemachine;

import com.mindblow.transaction.TransactionStatusType;

public interface TransactionStateMachine {

	public static final String TRANSACTION_STATE_MACHINE_NAME = "transactionStateMachine";

	public TransactionStatusType getCurrentState();

	public void waitingPay();

	public void paid();

	public void cancelled();
}
