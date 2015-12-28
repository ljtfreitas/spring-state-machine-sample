package com.mindblow.transaction.payment.statemachine.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.statemachine.annotation.OnStateChanged;

import com.mindblow.transaction.payment.CreditCardPaymentStatusType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@OnStateChanged
public @interface OnCreditCardPaymentStatusChanged {

	CreditCardPaymentStatusType[]source() default {};

	CreditCardPaymentStatusType[]target() default {};
}
