package com.mindblow.transaction.payment;

public enum CreditCardPaymentStatusType implements PaymentStatusType {
	CREATED, AUTHORIZING, AUTHORIZED, CANCELLED, PAID;
}