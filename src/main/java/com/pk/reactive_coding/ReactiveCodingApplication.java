package com.pk.reactive_coding;


import com.pk.reactive_coding.pubsub.publisher.PublisherImpl;
import com.pk.reactive_coding.pubsub.subscriber.SubscriberImpl;

public class ReactiveCodingApplication {

	public static void main(String[] args) {
		execute();
	}

	public static void execute() {
		PublisherImpl publisher = new PublisherImpl();
		SubscriberImpl subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);

		subscriber.getSubscription().request(3);
	}

}
