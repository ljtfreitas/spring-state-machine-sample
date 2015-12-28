package com.mindblow.transaction.payment.statemachine;

import org.springframework.statemachine.StateMachine;

import com.mindblow.transaction.payment.CreditCardPaymentStatusType;
import com.mindblow.transaction.payment.event.CreditCardPaymentEventType;

public class DefaultCreditCardPaymentStateMachine
		extends AbstractPaymentStateMachine<CreditCardPaymentStatusType, CreditCardPaymentEventType>
		implements CreditCardPaymentStateMachine {

	public DefaultCreditCardPaymentStateMachine(
			StateMachine<CreditCardPaymentStatusType, CreditCardPaymentEventType> stateMachine) {
		super(stateMachine);
	}

	@Override
	public void authorizing() {
		sendEvent(CreditCardPaymentEventType.AUTHORIZING);
	}

	@Override
	public void authorized() {
		sendEvent(CreditCardPaymentEventType.AUTHORIZED);
	}

	@Override
	public void cancelled() {
		sendEvent(CreditCardPaymentEventType.CANCELLED);
	}

	@Override
	public void paid() {
		sendEvent(CreditCardPaymentEventType.PAID);
	}

}
