package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.CreditCardPayment;

public class CreditCardPaymentStatusPaidEvent extends CreditCardPaymentStatusEvent {

	public CreditCardPaymentStatusPaidEvent(CreditCardPayment creditCardPayment) {
		super(creditCardPayment);
	}

}
