package com.mindblow.transaction.payment.statemachine;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import com.mindblow.transaction.payment.BoletoPayment;
import com.mindblow.transaction.payment.BoletoPaymentStatusType;
import com.mindblow.transaction.payment.event.BoletoPaymentEventType;

public class DefaultBoletoPaymentStateMachineFactory
		extends AbstractPaymentStateMachineFactory<BoletoPaymentStatusType, BoletoPaymentEventType>
		implements BoletoPaymentStateMachineFactory {

	public DefaultBoletoPaymentStateMachineFactory(
			StateMachineFactory<BoletoPaymentStatusType, BoletoPaymentEventType> factory) {
		super(factory);
	}

	@Override
	public BoletoPaymentStateMachine createBy(BoletoPayment boletoPayment) {
		StateMachine<BoletoPaymentStatusType, BoletoPaymentEventType> machine = super.initializeBy(boletoPayment);
		return new DefaultBoletoPaymentStateMachine(machine);
	}

}
