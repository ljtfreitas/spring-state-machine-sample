package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.BoletoPayment;

public class BoletoPaymentPrintedEvent extends BoletoPaymentEvent {

	public BoletoPaymentPrintedEvent(BoletoPayment boletoPayment) {
		super(boletoPayment, BoletoPaymentEventType.PRINTED);
	}

}
