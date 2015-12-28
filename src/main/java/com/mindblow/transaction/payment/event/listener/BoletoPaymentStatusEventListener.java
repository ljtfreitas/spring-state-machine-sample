package com.mindblow.transaction.payment.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mindblow.transaction.payment.BoletoPayment;
import com.mindblow.transaction.payment.event.BoletoPaymentStatusCancelledEvent;
import com.mindblow.transaction.payment.event.BoletoPaymentStatusPaidEvent;
import com.mindblow.transaction.payment.event.BoletoPaymentStatusPrintedEvent;
import com.mindblow.transaction.statemachine.TransactionStateMachine;
import com.mindblow.transaction.statemachine.TransactionStateMachineFactory;

@Component
public class BoletoPaymentStatusEventListener {

	@Autowired
	private TransactionStateMachineFactory transactionStateMachineFactory;

	@EventListener
	public void onPrinted(BoletoPaymentStatusPrintedEvent event) {
		BoletoPayment boletoPayment = event.getPayment();

		TransactionStateMachine transactionStateMachine = transactionStateMachineFactory.createBy(boletoPayment);

		transactionStateMachine.waitingPay();
	}

	@EventListener
	public void onPaid(BoletoPaymentStatusPaidEvent event) {
		BoletoPayment boletoPayment = event.getPayment();

		TransactionStateMachine transactionStateMachine = transactionStateMachineFactory.createBy(boletoPayment);

		transactionStateMachine.paid();
	}

	@EventListener
	public void onCancelled(BoletoPaymentStatusCancelledEvent event) {
		BoletoPayment boletoPayment = event.getPayment();

		TransactionStateMachine transactionStateMachine = transactionStateMachineFactory.createBy(boletoPayment);

		transactionStateMachine.cancelled();
	}
}
