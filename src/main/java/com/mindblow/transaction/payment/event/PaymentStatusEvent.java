package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.Payment;

public class PaymentStatusEvent<P extends Payment> {

	private final P payment;

	protected PaymentStatusEvent(P payment) {
		this.payment = payment;
	}

	public P getPayment() {
		return payment;
	}
}
