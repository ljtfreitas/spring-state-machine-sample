package com.mindblow.transaction.payment.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mindblow.transaction.payment.CreditCardPayment;
import com.mindblow.transaction.payment.event.CreditCardPaymentStatusAuthorizedEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentStatusAuthorizingEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentStatusPaidEvent;
import com.mindblow.transaction.statemachine.TransactionStateMachine;
import com.mindblow.transaction.statemachine.TransactionStateMachineFactory;

@Component
public class CreditCardPaymentStatusEventListener {

	@Autowired
	private TransactionStateMachineFactory transactionStateMachineFactory;

	@EventListener
	public void onAuthorizing(CreditCardPaymentStatusAuthorizingEvent event) {
		CreditCardPayment creditCardPayment = event.getPayment();

		TransactionStateMachine transactionStateMachine = transactionStateMachineFactory.createBy(creditCardPayment);

		transactionStateMachine.waitingPay();
	}

	@EventListener
	public void onAuthorized(CreditCardPaymentStatusAuthorizedEvent event) {
		CreditCardPayment creditCardPayment = event.getPayment();

		TransactionStateMachine transactionStateMachine = transactionStateMachineFactory.createBy(creditCardPayment);

		transactionStateMachine.waitingPay();
	}

	@EventListener
	public void onPaid(CreditCardPaymentStatusPaidEvent event) {
		CreditCardPayment creditCardPayment = event.getPayment();

		TransactionStateMachine transactionStateMachine = transactionStateMachineFactory.createBy(creditCardPayment);

		transactionStateMachine.paid();
	}

	@EventListener
	public void onCancelled(CreditCardPaymentStatusCancelledEvent event) {
		CreditCardPayment creditCardPayment = event.getPayment();

		TransactionStateMachine transactionStateMachine = transactionStateMachineFactory.createBy(creditCardPayment);

		transactionStateMachine.cancelled();
	}
}
