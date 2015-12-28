package com.mindblow.transaction.payment.statemachine.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

import com.mindblow.transaction.payment.statemachine.CreditCardPaymentStateMachine;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
@WithStateMachine(name = CreditCardPaymentStateMachine.CREDIT_CARD_PAYMENT_STATE_MACHINE_NAME)
public @interface OnCreditCardPaymentStateMachine {

}
