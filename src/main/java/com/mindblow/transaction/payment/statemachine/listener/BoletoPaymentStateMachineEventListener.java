package com.mindblow.transaction.payment.statemachine.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.annotation.ExtendedStateVariable;

import com.mindblow.transaction.payment.BoletoPayment;
import com.mindblow.transaction.payment.BoletoPaymentStatusType;
import com.mindblow.transaction.payment.event.BoletoPaymentStatusCancelledEvent;
import com.mindblow.transaction.payment.event.BoletoPaymentStatusPaidEvent;
import com.mindblow.transaction.payment.event.BoletoPaymentStatusPrintedEvent;
import com.mindblow.transaction.payment.event.PaymentStatusEventPublisher;
import com.mindblow.transaction.payment.repository.PaymentRepository;
import com.mindblow.transaction.payment.statemachine.annotation.OnBoletoPaymentStateMachine;
import com.mindblow.transaction.payment.statemachine.annotation.OnBoletoPaymentStatusChanged;

@OnBoletoPaymentStateMachine
public class BoletoPaymentStateMachineEventListener {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentStatusEventPublisher paymentStatusEventPublisher;

	@OnBoletoPaymentStatusChanged(target = BoletoPaymentStatusType.PRINTED)
	public void onPrinted(@ExtendedStateVariable("payment") BoletoPayment payment) {
		payment.printed();
		payment = paymentRepository.save(payment);

		paymentStatusEventPublisher.publish(new BoletoPaymentStatusPrintedEvent(payment));
	}

	@OnBoletoPaymentStatusChanged(target = BoletoPaymentStatusType.PAID)
	public void onPaid(@ExtendedStateVariable("payment") BoletoPayment payment) {
		payment.paid();
		payment = paymentRepository.save(payment);

		paymentStatusEventPublisher.publish(new BoletoPaymentStatusPaidEvent(payment));
	}

	@OnBoletoPaymentStatusChanged(target = BoletoPaymentStatusType.CANCELLED)
	public void onCancelled(@ExtendedStateVariable("payment") BoletoPayment payment) {
		payment.cancelled();
		payment = paymentRepository.save(payment);

		paymentStatusEventPublisher.publish(new BoletoPaymentStatusCancelledEvent(payment));
	}

}
