package com.mindblow.transaction.payment.event;

import com.mindblow.transaction.payment.BoletoPayment;

public class BoletoPaymentStatusEvent extends PaymentStatusEvent<BoletoPayment> {

	public BoletoPaymentStatusEvent(BoletoPayment boletoPayment) {
		super(boletoPayment);
	}

}
