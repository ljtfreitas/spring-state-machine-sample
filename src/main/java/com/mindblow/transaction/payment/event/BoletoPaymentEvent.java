package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.BoletoPayment;

public abstract class BoletoPaymentEvent extends PaymentEvent<BoletoPayment, BoletoPaymentEventType> {

	protected BoletoPaymentEvent(BoletoPayment boletoPayment, BoletoPaymentEventType eventType) {
		super(boletoPayment, eventType);
	}

}
