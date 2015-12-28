package com.mindblow.transaction.payment.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mindblow.transaction.payment.CreditCardPayment;
import com.mindblow.transaction.payment.event.CreditCardPaymentAuthorizedEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentAuthorizingEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentCancelledEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentPaidEvent;
import com.mindblow.transaction.payment.statemachine.CreditCardPaymentStateMachine;
import com.mindblow.transaction.payment.statemachine.CreditCardPaymentStateMachineFactory;

@Component
public class CreditCardPaymentEventListener {

	@Autowired
	private CreditCardPaymentStateMachineFactory creditCardPaymentStateMachineFactory;

	@EventListener(CreditCardPaymentAuthorizingEvent.class)
	public void onAuthorizing(CreditCardPaymentAuthorizingEvent event) {

		CreditCardPayment creditCardPayment = event.getPayment();

		CreditCardPaymentStateMachine creditCardStateMachine = creditCardPaymentStateMachineFactory
				.createBy(creditCardPayment);

		creditCardStateMachine.authorizing();
	}

	@EventListener(CreditCardPaymentAuthorizedEvent.class)
	public void onAuthorized(CreditCardPaymentAuthorizedEvent event) {

		CreditCardPayment creditCardPayment = event.getPayment();

		CreditCardPaymentStateMachine creditCardStateMachine = creditCardPaymentStateMachineFactory
				.createBy(creditCardPayment);

		creditCardStateMachine.authorized();
	}

	@EventListener(CreditCardPaymentPaidEvent.class)
	public void onPaid(CreditCardPaymentPaidEvent event) {

		CreditCardPayment creditCardPayment = event.getPayment();

		CreditCardPaymentStateMachine creditCardStateMachine = creditCardPaymentStateMachineFactory
				.createBy(creditCardPayment);

		creditCardStateMachine.paid();
	}

	@EventListener(CreditCardPaymentCancelledEvent.class)
	public void onCancelled(CreditCardPaymentCancelledEvent event) {

		CreditCardPayment creditCardPayment = event.getPayment();

		CreditCardPaymentStateMachine creditCardStateMachine = creditCardPaymentStateMachineFactory
				.createBy(creditCardPayment);

		creditCardStateMachine.cancelled();
	}
}
