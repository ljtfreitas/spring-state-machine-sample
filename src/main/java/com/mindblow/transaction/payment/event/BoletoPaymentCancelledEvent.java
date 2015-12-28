package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.BoletoPayment;

public class BoletoPaymentCancelledEvent extends BoletoPaymentEvent {

	public BoletoPaymentCancelledEvent(BoletoPayment boletoPayment) {
		super(boletoPayment, BoletoPaymentEventType.CANCELLED);
	}

}
