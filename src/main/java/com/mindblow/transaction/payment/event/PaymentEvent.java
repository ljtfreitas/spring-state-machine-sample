package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.Payment;

public abstract class PaymentEvent<P extends Payment, E extends PaymentEventType> {

	private final P payment;
	private final E eventType;

	public PaymentEvent(P payment, E eventType) {
		this.payment = payment;
		this.eventType = eventType;
	}

	public P getPayment() {
		return payment;
	}

	public E getEventType() {
		return eventType;
	}
}
