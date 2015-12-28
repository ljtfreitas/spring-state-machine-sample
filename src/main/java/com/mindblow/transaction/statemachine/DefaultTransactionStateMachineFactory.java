package com.mindblow.transaction.statemachine;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import com.mindblow.transaction.Transaction;
import com.mindblow.transaction.TransactionStatus;
import com.mindblow.transaction.TransactionStatusType;
import com.mindblow.transaction.event.TransactionEventType;
import com.mindblow.transaction.payment.Payment;

public class DefaultTransactionStateMachineFactory implements TransactionStateMachineFactory {

	private StateMachineFactory<TransactionStatusType, TransactionEventType> factory;

	public DefaultTransactionStateMachineFactory(
			StateMachineFactory<TransactionStatusType, TransactionEventType> factory) {
		this.factory = factory;
	}

	@Override
	public TransactionStateMachine createBy(Payment payment) {
		return this.createBy(payment.getTransaction());
	}

	@Override
	public TransactionStateMachine createBy(Transaction transaction) {
		StateMachine<TransactionStatusType, TransactionEventType> machine = factory.getStateMachine();

		TransactionStatus status = transaction.getStatus();

		machine.getStateMachineAccessor().withAllRegions().stream().forEach((r) -> {
			r.resetStateMachine(new DefaultStateMachineContext<>(status.asType(), null, null, null));
		});

		machine.getExtendedState().getVariables().put("transaction", transaction);
		machine.start();

		return new DefaultTransactionStateMachine(machine);
	}
}
