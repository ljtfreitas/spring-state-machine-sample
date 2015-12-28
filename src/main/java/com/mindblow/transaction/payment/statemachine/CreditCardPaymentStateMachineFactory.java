package com.mindblow.transaction.payment.statemachine;

import com.mindblow.transaction.payment.CreditCardPayment;

public interface CreditCardPaymentStateMachineFactory {

	public CreditCardPaymentStateMachine createBy(CreditCardPayment creditCardPayment);

}
