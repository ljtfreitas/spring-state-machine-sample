package com.mindblow.transaction.payment.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventPublisher {

	@Autowired
	private ApplicationEventPublisher context;

	public void publish(PaymentEvent<?, ?> event) {
		context.publishEvent(event);
	}
}
