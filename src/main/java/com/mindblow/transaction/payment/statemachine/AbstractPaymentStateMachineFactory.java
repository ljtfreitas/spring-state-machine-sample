package com.mindblow.transaction.payment.statemachine;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import com.mindblow.transaction.payment.Payment;
import com.mindblow.transaction.payment.PaymentStatus;
import com.mindblow.transaction.payment.PaymentStatusType;
import com.mindblow.transaction.payment.event.PaymentEventType;

public class AbstractPaymentStateMachineFactory<S extends PaymentStatusType, E extends PaymentEventType> {

	private final StateMachineFactory<S, E> factory;

	protected AbstractPaymentStateMachineFactory(StateMachineFactory<S, E> factory) {
		this.factory = factory;
	}

	protected StateMachine<S, E> initializeBy(Payment payment) {
		StateMachine<S, E> machine = factory.getStateMachine();

		PaymentStatus status = payment.getStatus();

		machine.getStateMachineAccessor().withAllRegions().stream().forEach((r) -> {
			r.resetStateMachine(new DefaultStateMachineContext<>(status.asType(), null, null, null));
		});

		machine.getExtendedState().getVariables().put("payment", payment);
		machine.start();

		return machine;
	}
}
