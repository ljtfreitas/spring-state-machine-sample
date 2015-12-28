package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.BoletoPayment;

public class BoletoPaymentPaidEvent extends BoletoPaymentEvent {

	public BoletoPaymentPaidEvent(BoletoPayment boletoPayment) {
		super(boletoPayment, BoletoPaymentEventType.PAID);
	}

}
