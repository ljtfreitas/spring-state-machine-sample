package com.mindblow.transaction.payment.statemachine;

import com.mindblow.transaction.payment.PaymentStatusType;
import com.mindblow.transaction.payment.event.PaymentEventType;

public interface PaymentStateMachine<S extends PaymentStatusType, E extends PaymentEventType> {

	public S getCurrentState();

}
