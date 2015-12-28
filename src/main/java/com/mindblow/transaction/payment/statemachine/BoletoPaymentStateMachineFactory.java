package com.mindblow.transaction.payment.statemachine;

import com.mindblow.transaction.payment.BoletoPayment;

public interface BoletoPaymentStateMachineFactory {

	public BoletoPaymentStateMachine createBy(BoletoPayment boletoPayment);

}
