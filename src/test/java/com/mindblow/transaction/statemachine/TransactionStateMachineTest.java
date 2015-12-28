package com.mindblow.transaction.statemachine;

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
import com.mindblow.transaction.payment.CreditCardPayment;
import com.mindblow.transaction.payment.CreditCardPaymentStatus;
import com.mindblow.transaction.payment.CreditCardPaymentStatusType;
import com.mindblow.transaction.payment.event.BoletoPaymentStatusCancelledEvent;
import com.mindblow.transaction.payment.event.BoletoPaymentStatusPaidEvent;
import com.mindblow.transaction.payment.event.BoletoPaymentStatusPrintedEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentStatusAuthorizedEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentStatusAuthorizingEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentStatusPaidEvent;
import com.mindblow.transaction.payment.event.PaymentStatusEventPublisher;
import com.mindblow.transaction.payment.event.listener.CreditCardPaymentStatusCancelledEvent;
import com.mindblow.transaction.payment.repository.PaymentRepository;
import com.mindblow.transaction.repository.TransactionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class TransactionStateMachineTest {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentStatusEventPublisher paymentStatusEventPublisher;

	private BoletoPayment boletoPayment;

	private Transaction transaction;

	@Before
	public void setup() {
		boletoPayment = new BoletoPayment(BoletoPaymentStatus.created());

		transaction = new Transaction(TransactionStatus.created());
		transaction.addPayment(boletoPayment);

		transactionRepository.save(transaction);
	}

	@Test
	public void onTransactionWaitingPayByBoletoPrinted() {
		boletoPayment.printed();
		paymentRepository.save(boletoPayment);

		paymentStatusEventPublisher.publish(new BoletoPaymentStatusPrintedEvent(boletoPayment));

		transaction = transactionRepository.findOne(transaction.getId());

		TransactionStatus status = transaction.getStatus();
		assertEquals(TransactionStatusType.WAITING_PAY, status.asType());
	}

	@Test
	public void onTransactionWaitingPayByBoletoPaidAndCreditCardAuthorizing() {
		boletoPayment.paid();
		paymentRepository.save(boletoPayment);

		CreditCardPayment creditCardPayment = new CreditCardPayment(
				new CreditCardPaymentStatus(CreditCardPaymentStatusType.AUTHORIZING));
		transaction.addPayment(creditCardPayment);
		transactionRepository.save(transaction);

		paymentStatusEventPublisher.publish(new CreditCardPaymentStatusAuthorizingEvent(creditCardPayment));

		transaction = transactionRepository.findOne(transaction.getId());

		TransactionStatus status = transaction.getStatus();
		assertEquals(TransactionStatusType.WAITING_PAY, status.asType());
	}

	@Test
	public void onTransactionWaitingPayByBoletoPaidAndCreditCardAuthorized() {
		boletoPayment.paid();
		paymentRepository.save(boletoPayment);

		CreditCardPayment creditCardPayment = new CreditCardPayment(
				new CreditCardPaymentStatus(CreditCardPaymentStatusType.AUTHORIZED));
		transaction.addPayment(creditCardPayment);
		transactionRepository.save(transaction);

		paymentStatusEventPublisher.publish(new CreditCardPaymentStatusAuthorizedEvent(creditCardPayment));

		transaction = transactionRepository.findOne(transaction.getId());

		TransactionStatus status = transaction.getStatus();
		assertEquals(TransactionStatusType.WAITING_PAY, status.asType());
	}

	@Test
	public void onTransactionPaidByBoletoPaid() {
		boletoPayment.paid();
		paymentRepository.save(boletoPayment);

		paymentStatusEventPublisher.publish(new BoletoPaymentStatusPaidEvent(boletoPayment));

		transaction = transactionRepository.findOne(transaction.getId());

		TransactionStatus status = transaction.getStatus();
		assertEquals(TransactionStatusType.PAID, status.asType());
	}

	@Test
	public void onTransactionPaidByBoletoPaidAndCreditCardPaymentPaid() {
		boletoPayment.paid();
		paymentRepository.save(boletoPayment);

		CreditCardPayment creditCardPayment = new CreditCardPayment(
				new CreditCardPaymentStatus(CreditCardPaymentStatusType.PAID));
		transaction.addPayment(creditCardPayment);
		transactionRepository.save(transaction);

		paymentStatusEventPublisher.publish(new CreditCardPaymentStatusPaidEvent(creditCardPayment));

		transaction = transactionRepository.findOne(transaction.getId());

		TransactionStatus status = transaction.getStatus();
		assertEquals(TransactionStatusType.PAID, status.asType());
	}

	@Test
	public void onTransactionCancelledByBoletoCancelled() {
		boletoPayment.cancelled();
		paymentRepository.save(boletoPayment);

		paymentStatusEventPublisher.publish(new BoletoPaymentStatusCancelledEvent(boletoPayment));

		transaction = transactionRepository.findOne(transaction.getId());

		TransactionStatus status = transaction.getStatus();
		assertEquals(TransactionStatusType.CANCELLED, status.asType());
	}

	@Test
	public void onTransactionCancelledByBoletoCancelledAndCreditCardCancelled() {
		boletoPayment.cancelled();
		paymentRepository.save(boletoPayment);

		CreditCardPayment creditCardPayment = new CreditCardPayment(
				new CreditCardPaymentStatus(CreditCardPaymentStatusType.CANCELLED));
		transaction.addPayment(creditCardPayment);
		transactionRepository.save(transaction);

		paymentStatusEventPublisher.publish(new CreditCardPaymentStatusCancelledEvent(creditCardPayment));

		transaction = transactionRepository.findOne(transaction.getId());

		TransactionStatus status = transaction.getStatus();
		assertEquals(TransactionStatusType.CANCELLED, status.asType());
	}

}
