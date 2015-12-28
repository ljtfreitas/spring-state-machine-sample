package com.mindblow.transaction.statemachine.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

import com.mindblow.transaction.statemachine.TransactionStateMachine;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
@WithStateMachine(name = TransactionStateMachine.TRANSACTION_STATE_MACHINE_NAME)
public @interface OnTransactionStateMachine {

}
