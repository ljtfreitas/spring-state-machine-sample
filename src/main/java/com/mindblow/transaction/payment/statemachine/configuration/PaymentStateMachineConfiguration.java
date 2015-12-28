package com.mindblow.transaction.payment.statemachine.configuration;

import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.mindblow.transaction.payment.PaymentStatusType;
import com.mindblow.transaction.payment.event.PaymentEventType;

public abstract class PaymentStateMachineConfiguration<S extends PaymentStatusType, E extends PaymentEventType>
		extends StateMachineConfigurerAdapter<S, E> {

	@Override
	public void configure(StateMachineStateConfigurer<S, E> states) throws Exception {
		configureStates(states);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<S, E> transitions) throws Exception {
		configureTransitions(transitions);
	}

	protected abstract void configureStates(StateMachineStateConfigurer<S, E> configurer) throws Exception;

	protected abstract void configureTransitions(StateMachineTransitionConfigurer<S, E> configurer) throws Exception;
}
