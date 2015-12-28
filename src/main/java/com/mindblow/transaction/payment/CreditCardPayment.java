package com.mindblow.transaction.payment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CreditCardPayment extends Payment {

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private CreditCardPaymentStatus status;

	@Deprecated
	public CreditCardPayment() {
	}

	public CreditCardPayment(CreditCardPaymentStatus status) {
		this.status = status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CreditCardPaymentStatus getStatus() {
		return status;
	}

	public void authorizing() {
		status.authorizing();
	}

	public void authorized() {
		status.authorized();
	}

	public void cancelled() {
		status.cancelled();
	}

	public void paid() {
		status.paid();
	}
}
