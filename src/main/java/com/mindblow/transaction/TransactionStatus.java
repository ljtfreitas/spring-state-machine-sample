package com.mindblow.transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TransactionStatus {

	@Id
	@GeneratedValue
	private Long id;

	private TransactionStatusType status;

	@Deprecated
	TransactionStatus() {
	}

	public TransactionStatus(TransactionStatusType status) {
		this.status = status;
	}

	public TransactionStatusType asType() {
		return status;
	}

	public void waitingPay() {
		status = TransactionStatusType.WAITING_PAY;
	}

	public void paid() {
		status = TransactionStatusType.PAID;
	}

	public void cancelled() {
		status = TransactionStatusType.CANCELLED;
	}

	public static TransactionStatus created() {
		return new TransactionStatus(TransactionStatusType.CREATED);
	}

}
