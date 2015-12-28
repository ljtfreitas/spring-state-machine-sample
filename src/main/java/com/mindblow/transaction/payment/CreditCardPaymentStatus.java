package com.mindblow.transaction.payment;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class CreditCardPaymentStatus extends PaymentStatus {

	@Enumerated(EnumType.STRING)
	private CreditCardPaymentStatusType status;

	@Deprecated
	CreditCardPaymentStatus() {
	}

	public CreditCardPaymentStatus(CreditCardPaymentStatusType status) {
		this.status = status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CreditCardPaymentStatusType asType() {
		return status;
	}

	@Override
	public boolean isWaitingPay() {
		return status == CreditCardPaymentStatusType.AUTHORIZING || status == CreditCardPaymentStatusType.AUTHORIZED;
	}

	@Override
	public boolean isPaid() {
		return status == CreditCardPaymentStatusType.PAID;
	}

	@Override
	public boolean isCancelled() {
		return status == CreditCardPaymentStatusType.CANCELLED;
	}

	public void authorizing() {
		status = CreditCardPaymentStatusType.AUTHORIZING;
	}

	public void authorized() {
		status = CreditCardPaymentStatusType.AUTHORIZED;
	}

	public void cancelled() {
		status = CreditCardPaymentStatusType.CANCELLED;
	}

	public void paid() {
		status = CreditCardPaymentStatusType.PAID;
	}

	public static CreditCardPaymentStatus created() {
		return new CreditCardPaymentStatus(CreditCardPaymentStatusType.CREATED);
	}

}
