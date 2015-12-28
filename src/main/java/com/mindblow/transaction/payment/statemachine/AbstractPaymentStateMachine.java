package com.mindblow.transaction.payment.statemachine;

import org.springframework.statemachine.StateMachine;

import com.mindblow.transaction.payment.PaymentStatusType;
import com.mindblow.transaction.payment.event.PaymentEventType;

public abstract class AbstractPaymentStateMachine<S extends PaymentStatusType, E extends PaymentEventType>
		implements PaymentStateMachine<S, E> {

	private final StateMachine<S, E> stateMachine;

	protected AbstractPaymentStateMachine(StateMachine<S, E> stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public S getCurrentState() {
		return stateMachine.getState().getId();
	}

	protected boolean sendEvent(E event) {
		return stateMachine.sendEvent(event);
	}
}
