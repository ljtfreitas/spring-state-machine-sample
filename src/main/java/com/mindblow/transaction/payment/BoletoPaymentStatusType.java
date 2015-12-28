package com.mindblow.transaction.payment;

public enum BoletoPaymentStatusType implements PaymentStatusType {
	CREATED, PRINTED, CANCELLED, PAID
}