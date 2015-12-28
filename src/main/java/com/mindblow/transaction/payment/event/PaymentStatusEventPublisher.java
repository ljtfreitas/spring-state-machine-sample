package com.mindblow.transaction.payment.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PaymentStatusEventPublisher {

	@Autowired
	private ApplicationEventPublisher context;

	public void publish(PaymentStatusEvent<?> event) {
		context.publishEvent(event);
	}
}
