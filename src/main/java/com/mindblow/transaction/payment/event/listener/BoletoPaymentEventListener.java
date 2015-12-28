package com.mindblow.transaction.payment.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mindblow.transaction.payment.BoletoPayment;
import com.mindblow.transaction.payment.event.BoletoPaymentCancelledEvent;
import com.mindblow.transaction.payment.event.BoletoPaymentPaidEvent;
import com.mindblow.transaction.payment.event.BoletoPaymentPrintedEvent;
import com.mindblow.transaction.payment.statemachine.BoletoPaymentStateMachine;
import com.mindblow.transaction.payment.statemachine.BoletoPaymentStateMachineFactory;

@Component
public class BoletoPaymentEventListener {

	@Autowired
	private BoletoPaymentStateMachineFactory boletoPaymentStateMachineFactory;

	@EventListener
	public void onPrinted(BoletoPaymentPrintedEvent event) {

		BoletoPayment boletoPayment = event.getPayment();

		BoletoPaymentStateMachine boletoStateMachine = boletoPaymentStateMachineFactory.createBy(boletoPayment);

		boletoStateMachine.printed();
	}

	@EventListener
	public void onPaid(BoletoPaymentPaidEvent event) {

		BoletoPayment boletoPayment = event.getPayment();

		BoletoPaymentStateMachine boletoStateMachine = boletoPaymentStateMachineFactory.createBy(boletoPayment);

		boletoStateMachine.paid();
	}

	@EventListener
	public void onCancelled(BoletoPaymentCancelledEvent event) {

		BoletoPayment boletoPayment = event.getPayment();

		BoletoPaymentStateMachine boletoStateMachine = boletoPaymentStateMachineFactory.createBy(boletoPayment);

		boletoStateMachine.cancelled();
	}
}
