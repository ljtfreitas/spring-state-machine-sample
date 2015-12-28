package com.mindblow.transaction.payment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class BoletoPayment extends Payment {

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	protected BoletoPaymentStatus status;

	@Deprecated
	BoletoPayment() {
	}

	public BoletoPayment(BoletoPaymentStatus status) {
		this.status = status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BoletoPaymentStatus getStatus() {
		return status;
	}

	public void printed() {
		status.printed();
	}

	public void paid() {
		status.paid();
	}

	public void cancelled() {
		status.cancelled();
	}
}
