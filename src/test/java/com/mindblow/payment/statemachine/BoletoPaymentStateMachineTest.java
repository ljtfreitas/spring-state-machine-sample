package com.mindblow.payment.statemachine;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mindblow.Application;
import com.mindblow.transaction.Transaction;
import com.mindblow.transaction.TransactionStatus;
import com.mindblow.transaction.TransactionStatusType;
import com.mindblow.transaction.payment.BoletoPayment;
import com.mindblow.transaction.payment.BoletoPaymentStatus;
import com.mindblow.transaction.payment.BoletoPaymentStatusType;
import com.mindblow.transaction.payment.event.BoletoPaymentCancelledEvent;
import com.mindblow.transaction.payment.event.BoletoPaymentPaidEvent;
import com.mindblow.transaction.payment.event.BoletoPaymentPrintedEvent;
import com.mindblow.transaction.payment.event.PaymentEventPublisher;
import com.mindblow.transaction.payment.repository.PaymentRepository;
import com.mindblow.transaction.repository.TransactionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class BoletoPaymentStateMachineTest {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private PaymentEventPublisher paymentEventPublisher;

	private Transaction transaction;

	private BoletoPayment boletoPayment;

	@Before
	public void setup() {
		transaction = new Transaction(TransactionStatus.created());

		boletoPayment = new BoletoPayment(BoletoPaymentStatus.created());

		transaction.addPayment(boletoPayment);
		transactionRepository.save(transaction);
	}

	@Test
	public void onBoletoPaymentPrintedEvent() {
		paymentEventPublisher.publish(new BoletoPaymentPrintedEvent(boletoPayment));

		boletoPayment = (BoletoPayment) paymentRepository.findOne(boletoPayment.getId());

		BoletoPaymentStatus boletoStatus = boletoPayment.getStatus();
		assertEquals(BoletoPaymentStatusType.PRINTED, boletoStatus.asType());

		transaction = boletoPayment.getTransaction();
		assertEquals(TransactionStatusType.WAITING_PAY, transaction.getStatus().asType());
	}

	@Test
	public void onBoletoPaymentPaidEvent() {
		boletoPayment.printed();
		paymentRepository.save(boletoPayment);

		paymentEventPublisher.publish(new BoletoPaymentPaidEvent(boletoPayment));

		boletoPayment = (BoletoPayment) paymentRepository.findOne(boletoPayment.getId());

		BoletoPaymentStatus boletoStatus = boletoPayment.getStatus();
		assertEquals(BoletoPaymentStatusType.PAID, boletoStatus.asType());

		transaction = boletoPayment.getTransaction();
		assertEquals(TransactionStatusType.PAID, transaction.getStatus().asType());
	}

	@Test
	public void onBoletoPaymentCancelledEvent() {
		boletoPayment.printed();
		paymentRepository.save(boletoPayment);

		paymentEventPublisher.publish(new BoletoPaymentCancelledEvent(boletoPayment));

		boletoPayment = (BoletoPayment) paymentRepository.findOne(boletoPayment.getId());

		BoletoPaymentStatus boletoStatus = boletoPayment.getStatus();
		assertEquals(BoletoPaymentStatusType.CANCELLED, boletoStatus.asType());

		transaction = boletoPayment.getTransaction();
		assertEquals(TransactionStatusType.CANCELLED, transaction.getStatus().asType());
	}
}
