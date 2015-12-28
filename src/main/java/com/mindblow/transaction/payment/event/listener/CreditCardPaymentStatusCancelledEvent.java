package com.mindblow.transaction.payment.event.listener;

import com.mindblow.transaction.payment.CreditCardPayment;
import com.mindblow.transaction.payment.event.CreditCardPaymentStatusEvent;

public class CreditCardPaymentStatusCancelledEvent extends CreditCardPaymentStatusEvent {

	public CreditCardPaymentStatusCancelledEvent(CreditCardPayment creditCardPayment) {
		super(creditCardPayment);
	}

}
