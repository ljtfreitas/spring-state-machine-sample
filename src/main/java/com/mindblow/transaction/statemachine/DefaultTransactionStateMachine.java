package com.mindblow.transaction.statemachine;

import org.springframework.statemachine.StateMachine;

import com.mindblow.transaction.TransactionStatusType;
import com.mindblow.transaction.event.TransactionEventType;

public class DefaultTransactionStateMachine implements TransactionStateMachine {

	private StateMachine<TransactionStatusType, TransactionEventType> stateMachine;

	public DefaultTransactionStateMachine(StateMachine<TransactionStatusType, TransactionEventType> stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public TransactionStatusType getCurrentState() {
		return stateMachine.getState().getId();
	}

	@Override
	public void waitingPay() {
		stateMachine.sendEvent(TransactionEventType.WAITING_PAY);
	}

	@Override
	public void paid() {
		stateMachine.sendEvent(TransactionEventType.PAID);
	}

	@Override
	public void cancelled() {
		stateMachine.sendEvent(TransactionEventType.CANCELLED);
	}

}
