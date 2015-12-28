package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.CreditCardPayment;

public class CreditCardPaymentStatusEvent extends PaymentStatusEvent<CreditCardPayment> {

	public CreditCardPaymentStatusEvent(CreditCardPayment creditCardPayment) {
		super(creditCardPayment);
	}

}
