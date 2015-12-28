package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.CreditCardPayment;

public class CreditCardPaymentPaidEvent extends CreditCardPaymentEvent {

	public CreditCardPaymentPaidEvent(CreditCardPayment creditCardpayment) {
		super(creditCardpayment, CreditCardPaymentEventType.PAID);
	}

}
