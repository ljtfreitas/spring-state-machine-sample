package com.mindblow.transaction.payment.statemachine;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import com.mindblow.transaction.payment.CreditCardPayment;
import com.mindblow.transaction.payment.CreditCardPaymentStatusType;
import com.mindblow.transaction.payment.event.CreditCardPaymentEventType;

public class DefaultCreditCardPaymentStateMachineFactory
		extends AbstractPaymentStateMachineFactory<CreditCardPaymentStatusType, CreditCardPaymentEventType>
		implements CreditCardPaymentStateMachineFactory {

	public DefaultCreditCardPaymentStateMachineFactory(
			StateMachineFactory<CreditCardPaymentStatusType, CreditCardPaymentEventType> factory) {
		super(factory);
	}

	@Override
	public CreditCardPaymentStateMachine createBy(CreditCardPayment creditCardPayment) {
		StateMachine<CreditCardPaymentStatusType, CreditCardPaymentEventType> machine = super.initializeBy(
				creditCardPayment);
		return new DefaultCreditCardPaymentStateMachine(machine);
	}

}
