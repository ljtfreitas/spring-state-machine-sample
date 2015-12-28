package com.mindblow.transaction.payment.event;

public enum CreditCardPaymentEventType implements PaymentEventType {
	CREATED, AUTHORIZING, AUTHORIZED, CANCELLED, PAID;
}