package com.mindblow.transaction.payment;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class BoletoPaymentStatus extends PaymentStatus {

	@Enumerated(EnumType.STRING)
	private BoletoPaymentStatusType status;

	@Deprecated
	BoletoPaymentStatus() {
	}

	private BoletoPaymentStatus(BoletoPaymentStatusType status) {
		this.status = status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BoletoPaymentStatusType asType() {
		return status;
	}

	@Override
	public boolean isWaitingPay() {
		return status == BoletoPaymentStatusType.PRINTED;
	}

	@Override
	public boolean isPaid() {
		return status == BoletoPaymentStatusType.PAID;
	}

	@Override
	public boolean isCancelled() {
		return status == BoletoPaymentStatusType.CANCELLED;
	}

	public void printed() {
		status = BoletoPaymentStatusType.PRINTED;
	}

	public void paid() {
		status = BoletoPaymentStatusType.PAID;
	}

	public void cancelled() {
		status = BoletoPaymentStatusType.CANCELLED;
	}

	public static BoletoPaymentStatus created() {
		return new BoletoPaymentStatus(BoletoPaymentStatusType.CREATED);
	}

}
