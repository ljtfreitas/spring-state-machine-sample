package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.BoletoPayment;

public class BoletoPaymentStatusPrintedEvent extends BoletoPaymentStatusEvent {

	public BoletoPaymentStatusPrintedEvent(BoletoPayment boletoPayment) {
		super(boletoPayment);
	}

}
