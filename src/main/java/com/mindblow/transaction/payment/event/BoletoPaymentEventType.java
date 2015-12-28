package com.mindblow.transaction.payment.event;

public enum BoletoPaymentEventType implements PaymentEventType {
	CREATED, PRINTED, CANCELLED, PAID
}