package com.mindblow.transaction.statemachine.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.statemachine.annotation.OnStateChanged;

import com.mindblow.transaction.TransactionStatusType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@OnStateChanged
public @interface OnTransactionStatusChanged {

	TransactionStatusType[]source() default {};

	TransactionStatusType[]target() default {};
}
