package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.CreditCardPayment;

public class CreditCardPaymentStatusAuthorizedEvent extends CreditCardPaymentStatusEvent {

	public CreditCardPaymentStatusAuthorizedEvent(CreditCardPayment creditCardPayment) {
		super(creditCardPayment);
	}

}
