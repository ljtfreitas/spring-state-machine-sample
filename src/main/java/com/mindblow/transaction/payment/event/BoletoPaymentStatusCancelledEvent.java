package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.BoletoPayment;

public class BoletoPaymentStatusCancelledEvent extends BoletoPaymentStatusEvent {

	public BoletoPaymentStatusCancelledEvent(BoletoPayment boletoPayment) {
		super(boletoPayment);
	}

}
