package com.mindblow.transaction.statemachine.rule;

import com.mindblow.transaction.Transaction;

public class TransactionPaymentWaitingPayRule implements TransactionStateMachineRule {

	@Override
	public boolean evaluate(Transaction transaction) {

		boolean isWaitingPay = transaction.getPayments().stream().filter((p) -> p.isWaitingPay()).findAny().isPresent();

		return isWaitingPay;
	}

}
