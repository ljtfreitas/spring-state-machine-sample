package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.CreditCardPayment;

public abstract class CreditCardPaymentEvent extends PaymentEvent<CreditCardPayment, CreditCardPaymentEventType> {

	protected CreditCardPaymentEvent(CreditCardPayment creditCardpayment, CreditCardPaymentEventType eventType) {
		super(creditCardpayment, eventType);
	}

}
