package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.BoletoPayment;

public class BoletoPaymentStatusPaidEvent extends BoletoPaymentStatusEvent {

	public BoletoPaymentStatusPaidEvent(BoletoPayment boletoPayment) {
		super(boletoPayment);
	}

}
