package com.mindblow.transaction.payment.statemachine;

import org.springframework.statemachine.StateMachine;

import com.mindblow.transaction.payment.BoletoPaymentStatusType;
import com.mindblow.transaction.payment.event.BoletoPaymentEventType;

public class DefaultBoletoPaymentStateMachine
		extends AbstractPaymentStateMachine<BoletoPaymentStatusType, BoletoPaymentEventType>
		implements BoletoPaymentStateMachine {

	public DefaultBoletoPaymentStateMachine(
			StateMachine<BoletoPaymentStatusType, BoletoPaymentEventType> stateMachine) {
		super(stateMachine);
	}

	@Override
	public void printed() {
		sendEvent(BoletoPaymentEventType.PRINTED);
	}

	@Override
	public void paid() {
		sendEvent(BoletoPaymentEventType.PAID);
	}

	@Override
	public void cancelled() {
		sendEvent(BoletoPaymentEventType.CANCELLED);
	}

}
