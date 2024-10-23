package com.pk.reactive_coding.mono;

import com.pk.reactive_coding.pubsub.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class MonoJust {

    public static void main(String[] args) {

        /**
         * data no need to be string it can be any data type
         */
        Mono<String> mono = Mono.just("Hello Mono Publisher");
        /**
         * The below will print MonoJust it doesn't print the actual value because there is no subscriber.
         */
        System.out.println(mono);

        SubscriberImpl subscriber = new SubscriberImpl();

        /**
         * The below will print MonoJust it doesn't print the actual value because the subscriber doesn't requested.
         */
        mono.subscribe(subscriber);

        /**
         * The below will print actual value.
         */
        subscriber.getSubscription().request(1);

    }
}
