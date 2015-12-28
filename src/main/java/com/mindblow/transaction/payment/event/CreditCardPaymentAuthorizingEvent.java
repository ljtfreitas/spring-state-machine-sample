package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.CreditCardPayment;

public class CreditCardPaymentAuthorizingEvent extends CreditCardPaymentEvent {

	public CreditCardPaymentAuthorizingEvent(CreditCardPayment creditCardpayment) {
		super(creditCardpayment, CreditCardPaymentEventType.AUTHORIZING);
	}

}
