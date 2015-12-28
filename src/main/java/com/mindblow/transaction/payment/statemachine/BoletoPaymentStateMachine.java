package com.mindblow.transaction.payment.statemachine;

import com.mindblow.transaction.payment.BoletoPaymentStatusType;
import com.mindblow.transaction.payment.event.BoletoPaymentEventType;

public interface BoletoPaymentStateMachine
		extends PaymentStateMachine<BoletoPaymentStatusType, BoletoPaymentEventType> {

	public static final String BOLETO_PAYMENT_STATE_MACHINE_NAME = "boletoPaymentStateMachine";

	public void printed();

	public void paid();

	public void cancelled();
}
