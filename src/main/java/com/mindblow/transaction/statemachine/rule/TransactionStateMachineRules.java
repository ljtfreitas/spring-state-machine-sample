package com.mindblow.transaction.statemachine.rule;

public class TransactionStateMachineRules {

	public static TransactionStateMachineRule atLeastOnePaymentIsWaitingPay() {
		return ((t) -> {
			boolean isWaitingPay = t.getPayments().stream().filter((p) -> p.isWaitingPay()).findAny().isPresent();

			return isWaitingPay;
		});
	}

	public static TransactionStateMachineRule atLeastOnePaymentIsCancelled() {
		return ((t) -> {
			boolean isNotPaid = t.getPayments().stream().filter((p) -> !p.isPaid()).findAny().isPresent();

			return !isNotPaid;
		});
	}

	public static TransactionStateMachineRule allPaymentsIsPaid() {
		return ((t) -> {
			boolean isNotPaid = t.getPayments().stream().filter((p) -> !p.isPaid()).findAny().isPresent();

			return !isNotPaid;
		});
	}

	public static TransactionStateMachineRule allPaymentsIsCancelled() {
		return ((t) -> {
			boolean isNotPaid = t.getPayments().stream().filter((p) -> !p.isCancelled()).findAny().isPresent();

			return !isNotPaid;
		});
	}

}
