package com.mindblow.transaction.payment.statemachine;

import com.mindblow.transaction.payment.CreditCardPaymentStatusType;
import com.mindblow.transaction.payment.event.CreditCardPaymentEventType;

public interface CreditCardPaymentStateMachine
		extends PaymentStateMachine<CreditCardPaymentStatusType, CreditCardPaymentEventType> {

	public static final String CREDIT_CARD_PAYMENT_STATE_MACHINE_NAME = "creditCardPaymentStateMachine";

	public void authorizing();

	public void authorized();

	public void cancelled();

	public void paid();
}
