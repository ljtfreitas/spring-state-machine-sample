package com.mindblow.transaction.statemachine.configuration;

import static com.mindblow.transaction.statemachine.rule.TransactionStateMachineRules.allPaymentsIsCancelled;
import static com.mindblow.transaction.statemachine.rule.TransactionStateMachineRules.allPaymentsIsPaid;
import static com.mindblow.transaction.statemachine.rule.TransactionStateMachineRules.atLeastOnePaymentIsCancelled;
import static com.mindblow.transaction.statemachine.rule.TransactionStateMachineRules.atLeastOnePaymentIsWaitingPay;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import com.mindblow.transaction.Transaction;
import com.mindblow.transaction.TransactionStatusType;
import com.mindblow.transaction.event.TransactionEventType;
import com.mindblow.transaction.statemachine.DefaultTransactionStateMachineFactory;
import com.mindblow.transaction.statemachine.TransactionStateMachine;
import com.mindblow.transaction.statemachine.TransactionStateMachineFactory;
import com.mindblow.transaction.statemachine.rule.TransactionStateMachineRule;

@Configuration
@EnableStateMachineFactory(name = TransactionStateMachine.TRANSACTION_STATE_MACHINE_NAME)
public class TransactionStateMachineConfiguration
		extends EnumStateMachineConfigurerAdapter<TransactionStatusType, TransactionEventType> {

	@Override
	public void configure(StateMachineStateConfigurer<TransactionStatusType, TransactionEventType> configurer)
			throws Exception {

		configurer
			.withStates()
				.initial(TransactionStatusType.CREATED)
				.states(EnumSet.allOf(TransactionStatusType.class))
				.choice(TransactionStatusType.PAYMENT_ON_ANALYZIS)
				.end(TransactionStatusType.PAID)
				.end(TransactionStatusType.CANCELLED);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<TransactionStatusType, TransactionEventType> configurer)
			throws Exception {

		configurer
			.withExternal()
				.source(TransactionStatusType.CREATED)
					.target(TransactionStatusType.PAYMENT_ON_ANALYZIS)
						.event(TransactionEventType.WAITING_PAY)
						.and()
			.withExternal()
				.source(TransactionStatusType.CREATED)
					.target(TransactionStatusType.PAYMENT_ON_ANALYZIS)
						.event(TransactionEventType.PAID)
						.and()
			.withExternal()
				.source(TransactionStatusType.CREATED)
					.target(TransactionStatusType.PAYMENT_ON_ANALYZIS)
						.event(TransactionEventType.CANCELLED)
						.and()
			.withChoice()
				.source(TransactionStatusType.PAYMENT_ON_ANALYZIS)
					.first(TransactionStatusType.PAID, asGuard(allPaymentsIsPaid()))
					.then(TransactionStatusType.CANCELLED, asGuard(allPaymentsIsCancelled()))
					.then(TransactionStatusType.WAITING_PAY, asGuard(atLeastOnePaymentIsWaitingPay().or(atLeastOnePaymentIsCancelled())))
					.last(TransactionStatusType.MANUAL_ANALYSIS);

	}

	private Guard<TransactionStatusType, TransactionEventType> asGuard(final TransactionStateMachineRule rule) {
		return new Guard<TransactionStatusType, TransactionEventType>() {
			@Override
			public boolean evaluate(StateContext<TransactionStatusType, TransactionEventType> context) {
				Transaction transaction = context.getExtendedState().get("transaction", Transaction.class);
				return rule.evaluate(transaction);
			}
		};
	}

	@Bean
	public TransactionStateMachineFactory transactionStateMachineFactory(
			@Qualifier(TransactionStateMachine.TRANSACTION_STATE_MACHINE_NAME) StateMachineFactory<TransactionStatusType, TransactionEventType> factory) {

		return new DefaultTransactionStateMachineFactory(factory);
	}
}
