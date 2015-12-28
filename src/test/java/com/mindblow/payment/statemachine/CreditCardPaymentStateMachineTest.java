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
import com.mindblow.transaction.payment.CreditCardPayment;
import com.mindblow.transaction.payment.CreditCardPaymentStatus;
import com.mindblow.transaction.payment.CreditCardPaymentStatusType;
import com.mindblow.transaction.payment.event.CreditCardPaymentAuthorizedEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentAuthorizingEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentCancelledEvent;
import com.mindblow.transaction.payment.event.CreditCardPaymentPaidEvent;
import com.mindblow.transaction.payment.event.PaymentEventPublisher;
import com.mindblow.transaction.payment.repository.PaymentRepository;
import com.mindblow.transaction.repository.TransactionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class CreditCardPaymentStateMachineTest {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private PaymentEventPublisher paymentEventPublisher;

	private Transaction transaction;

	private CreditCardPayment creditCardPayment;

	@Before
	public void setup() {
		transaction = new Transaction(TransactionStatus.created());

		creditCardPayment = new CreditCardPayment(CreditCardPaymentStatus.created());

		transaction.addPayment(creditCardPayment);
		transactionRepository.save(transaction);
	}

	@Test
	public void onCreditCardPaymentAuthorizingEvent() {
		paymentEventPublisher.publish(new CreditCardPaymentAuthorizingEvent(creditCardPayment));

		creditCardPayment = (CreditCardPayment) paymentRepository.findOne(creditCardPayment.getId());

		CreditCardPaymentStatus creditCardStatus = creditCardPayment.getStatus();
		assertEquals(CreditCardPaymentStatusType.AUTHORIZING, creditCardStatus.asType());

		transaction = creditCardPayment.getTransaction();
		assertEquals(TransactionStatusType.WAITING_PAY, transaction.getStatus().asType());
	}

	@Test
	public void onCreditCardPaymentAuthorizedEvent() {
		creditCardPayment.authorizing();
		paymentRepository.save(creditCardPayment);

		paymentEventPublisher.publish(new CreditCardPaymentAuthorizedEvent(creditCardPayment));

		creditCardPayment = (CreditCardPayment) paymentRepository.findOne(creditCardPayment.getId());

		CreditCardPaymentStatus creditCardStatus = creditCardPayment.getStatus();
		assertEquals(CreditCardPaymentStatusType.AUTHORIZED, creditCardStatus.asType());

		transaction = creditCardPayment.getTransaction();
		assertEquals(TransactionStatusType.WAITING_PAY, transaction.getStatus().asType());
	}

	@Test
	public void onCreditCardPaymentPaidEvent() {
		creditCardPayment.authorized();
		paymentRepository.save(creditCardPayment);

		paymentEventPublisher.publish(new CreditCardPaymentPaidEvent(creditCardPayment));

		creditCardPayment = (CreditCardPayment) paymentRepository.findOne(creditCardPayment.getId());

		CreditCardPaymentStatus creditCardStatus = creditCardPayment.getStatus();
		assertEquals(CreditCardPaymentStatusType.PAID, creditCardStatus.asType());

		transaction = creditCardPayment.getTransaction();
		assertEquals(TransactionStatusType.PAID, transaction.getStatus().asType());
	}

	@Test
	public void onCreditCardPaymentCancelledEvent() {
		creditCardPayment.authorizing();
		paymentRepository.save(creditCardPayment);

		paymentEventPublisher.publish(new CreditCardPaymentCancelledEvent(creditCardPayment));

		creditCardPayment = (CreditCardPayment) paymentRepository.findOne(creditCardPayment.getId());

		CreditCardPaymentStatus creditCardStatus = creditCardPayment.getStatus();
		assertEquals(CreditCardPaymentStatusType.CANCELLED, creditCardStatus.asType());

		transaction = creditCardPayment.getTransaction();
		assertEquals(TransactionStatusType.CANCELLED, transaction.getStatus().asType());
	}
}
