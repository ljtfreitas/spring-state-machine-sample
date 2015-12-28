package com.mindblow.transaction.payment.statemachine.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.annotation.ExtendedStateVariable;

import com.mindblow.transaction.payment.CreditCardPayment;
import com.mindblow.transaction.payment.CreditCardPaymentStatusType;
import com.mindblow.transaction.payment.event.CreditCardPaymentStatusAuthorizedEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentStatusAuthorizingEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentStatusPaidEvent;
import com.mindblow.transaction.payment.event.PaymentStatusEventPublisher;
import com.mindblow.transaction.payment.event.listener.CreditCardPaymentStatusCancelledEvent;
import com.mindblow.transaction.payment.repository.PaymentRepository;
import com.mindblow.transaction.payment.statemachine.annotation.OnCreditCardPaymentStateMachine;
import com.mindblow.transaction.payment.statemachine.annotation.OnCreditCardPaymentStatusChanged;

@OnCreditCardPaymentStateMachine
public class CreditCardPaymentStateMachineEventListener {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentStatusEventPublisher paymentStatusEventPublisher;

	@OnCreditCardPaymentStatusChanged(target = CreditCardPaymentStatusType.AUTHORIZING)
	public void onAuthorizing(@ExtendedStateVariable("payment") CreditCardPayment payment) {
		payment.authorizing();
		payment = paymentRepository.save(payment);

		paymentStatusEventPublisher.publish(new CreditCardPaymentStatusAuthorizingEvent(payment));
	}

	@OnCreditCardPaymentStatusChanged(target = CreditCardPaymentStatusType.AUTHORIZED)
	public void onAuthorized(@ExtendedStateVariable("payment") CreditCardPayment payment) {
		payment.authorized();
		payment = paymentRepository.save(payment);

		paymentStatusEventPublisher.publish(new CreditCardPaymentStatusAuthorizedEvent(payment));
	}

	@OnCreditCardPaymentStatusChanged(target = CreditCardPaymentStatusType.PAID)
	public void onPaid(@ExtendedStateVariable("payment") CreditCardPayment payment) {
		payment.paid();
		payment = paymentRepository.save(payment);

		paymentStatusEventPublisher.publish(new CreditCardPaymentStatusPaidEvent(payment));
	}

	@OnCreditCardPaymentStatusChanged(target = CreditCardPaymentStatusType.CANCELLED)
	public void onCancelled(@ExtendedStateVariable("payment") CreditCardPayment payment) {
		payment.cancelled();
		payment = paymentRepository.save(payment);

		paymentStatusEventPublisher.publish(new CreditCardPaymentStatusCancelledEvent(payment));
	}

}
