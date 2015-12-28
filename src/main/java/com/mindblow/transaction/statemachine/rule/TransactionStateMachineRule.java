package com.mindblow.transaction.statemachine.rule;

import com.mindblow.transaction.Transaction;

public interface TransactionStateMachineRule {

	public boolean evaluate(Transaction transaction);

	public default TransactionStateMachineRule or(TransactionStateMachineRule other) {
		return (t) -> evaluate(t) || other.evaluate(t);
	}

}
