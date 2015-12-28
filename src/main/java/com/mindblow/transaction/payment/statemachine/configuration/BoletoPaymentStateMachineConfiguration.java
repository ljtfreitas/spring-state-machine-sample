package com.mindblow.transaction.payment.statemachine.configuration;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.mindblow.transaction.payment.BoletoPaymentStatusType;
import com.mindblow.transaction.payment.event.BoletoPaymentEventType;
import com.mindblow.transaction.payment.statemachine.BoletoPaymentStateMachine;
import com.mindblow.transaction.payment.statemachine.BoletoPaymentStateMachineFactory;
import com.mindblow.transaction.payment.statemachine.DefaultBoletoPaymentStateMachineFactory;

@Configuration
@EnableStateMachineFactory(name = BoletoPaymentStateMachine.BOLETO_PAYMENT_STATE_MACHINE_NAME)
public class BoletoPaymentStateMachineConfiguration
		extends PaymentStateMachineConfiguration<BoletoPaymentStatusType, BoletoPaymentEventType> {

	@Override
	protected void configureStates(
			StateMachineStateConfigurer<BoletoPaymentStatusType, BoletoPaymentEventType> configurer) throws Exception {

		configurer
			.withStates()
				.initial(BoletoPaymentStatusType.CREATED)
				.states(EnumSet.allOf(BoletoPaymentStatusType.class))
				.end(BoletoPaymentStatusType.PAID)
				.end(BoletoPaymentStatusType.CANCELLED);
	}

	@Override
	protected void configureTransitions(
			StateMachineTransitionConfigurer<BoletoPaymentStatusType, BoletoPaymentEventType> configurer)
					throws Exception {

		configurer
			.withExternal()
				.source(BoletoPaymentStatusType.CREATED)
					.target(BoletoPaymentStatusType.PRINTED)
						.event(BoletoPaymentEventType.PRINTED)
						.and()
			.withExternal()
				.source(BoletoPaymentStatusType.PRINTED)
					.target(BoletoPaymentStatusType.CANCELLED)
						.event(BoletoPaymentEventType.CANCELLED)
						.and()
			.withExternal()
				.source(BoletoPaymentStatusType.PRINTED)
					.target(BoletoPaymentStatusType.PAID)
					.event(BoletoPaymentEventType.PAID);
	}

	@Bean
	public BoletoPaymentStateMachineFactory boletoPaymentStateMachineFactory(
			@Qualifier(BoletoPaymentStateMachine.BOLETO_PAYMENT_STATE_MACHINE_NAME) StateMachineFactory<BoletoPaymentStatusType, BoletoPaymentEventType> factory) {

		return new DefaultBoletoPaymentStateMachineFactory(factory);
	}
}
