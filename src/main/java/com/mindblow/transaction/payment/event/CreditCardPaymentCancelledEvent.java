package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.CreditCardPayment;

public class CreditCardPaymentCancelledEvent extends CreditCardPaymentEvent {

	public CreditCardPaymentCancelledEvent(CreditCardPayment creditCardpayment) {
		super(creditCardpayment, CreditCardPaymentEventType.CANCELLED);
	}

}
