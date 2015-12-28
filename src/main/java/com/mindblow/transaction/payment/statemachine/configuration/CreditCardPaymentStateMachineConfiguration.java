package com.mindblow.transaction.payment.statemachine.configuration;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.mindblow.transaction.payment.CreditCardPaymentStatusType;
import com.mindblow.transaction.payment.event.CreditCardPaymentEventType;
import com.mindblow.transaction.payment.statemachine.CreditCardPaymentStateMachine;
import com.mindblow.transaction.payment.statemachine.CreditCardPaymentStateMachineFactory;
import com.mindblow.transaction.payment.statemachine.DefaultCreditCardPaymentStateMachineFactory;

@Configuration
@EnableStateMachineFactory(name = CreditCardPaymentStateMachine.CREDIT_CARD_PAYMENT_STATE_MACHINE_NAME)
public class CreditCardPaymentStateMachineConfiguration
		extends PaymentStateMachineConfiguration<CreditCardPaymentStatusType, CreditCardPaymentEventType> {

	@Override
	protected void configureStates(
			StateMachineStateConfigurer<CreditCardPaymentStatusType, CreditCardPaymentEventType> configurer)
					throws Exception {

		configurer
			.withStates()
				.initial(CreditCardPaymentStatusType.CREATED)
				.states(EnumSet.allOf(CreditCardPaymentStatusType.class))
				.end(CreditCardPaymentStatusType.PAID)
				.end(CreditCardPaymentStatusType.CANCELLED);
	}

	@Override
	protected void configureTransitions(
			StateMachineTransitionConfigurer<CreditCardPaymentStatusType, CreditCardPaymentEventType> configurer)
					throws Exception {

		configurer
			.withExternal()
				.source(CreditCardPaymentStatusType.CREATED)
					.target(CreditCardPaymentStatusType.AUTHORIZING)
					.event(CreditCardPaymentEventType.AUTHORIZING)
					.and()
			.withExternal()
				.source(CreditCardPaymentStatusType.AUTHORIZING)
					.target(CreditCardPaymentStatusType.AUTHORIZED)
						.event(CreditCardPaymentEventType.AUTHORIZED)
						.and()
			.withExternal()
				.source(CreditCardPaymentStatusType.AUTHORIZING)
					.target(CreditCardPaymentStatusType.CANCELLED)
						.event(CreditCardPaymentEventType.CANCELLED)
						.and()
			.withExternal()
				.source(CreditCardPaymentStatusType.AUTHORIZED)
					.target(CreditCardPaymentStatusType.PAID)
						.event(CreditCardPaymentEventType.PAID);
	}

	@Bean
	public CreditCardPaymentStateMachineFactory creditCardPaymentStateMachineFactory(
			@Qualifier(CreditCardPaymentStateMachine.CREDIT_CARD_PAYMENT_STATE_MACHINE_NAME) StateMachineFactory<CreditCardPaymentStatusType, CreditCardPaymentEventType> factory) {

		return new DefaultCreditCardPaymentStateMachineFactory(factory);
	}
}
