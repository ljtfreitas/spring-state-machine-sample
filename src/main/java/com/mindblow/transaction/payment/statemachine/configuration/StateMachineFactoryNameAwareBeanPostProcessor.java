package com.mindblow.transaction.payment.statemachine.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

@Component
public class StateMachineFactoryNameAwareBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof StateMachineFactory<?, ?> && bean instanceof BeanNameAware) {
			((BeanNameAware) bean).setBeanName(beanName);
		}

		return bean;
	}

}
