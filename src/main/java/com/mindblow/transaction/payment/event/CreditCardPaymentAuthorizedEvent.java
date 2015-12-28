package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.CreditCardPayment;

public class CreditCardPaymentAuthorizedEvent extends CreditCardPaymentEvent {

	public CreditCardPaymentAuthorizedEvent(CreditCardPayment creditCardpayment) {
		super(creditCardpayment, CreditCardPaymentEventType.AUTHORIZED);
	}

}
