package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.CreditCardPayment;

public class CreditCardPaymentStatusAuthorizingEvent extends CreditCardPaymentStatusEvent {

	public CreditCardPaymentStatusAuthorizingEvent(CreditCardPayment creditCardPayment) {
		super(creditCardPayment);
	}

}
