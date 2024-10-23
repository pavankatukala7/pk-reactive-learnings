package com.pk.reactive_coding.mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoConsumer {

    private static final Logger log = LoggerFactory.getLogger(MonoConsumer.class);

    public static void main(String[] args) {

        Mono<Integer> mono = Mono.just(1)
                .map( i -> i / 0)
                .onErrorComplete();


        /**
         * We only subscribed but not provided the subscriber
         * There is no onComplete
         * This is because we are using
         *
         * Instead of using the custom subscriber here we are using the consumer interface to get the value from the publisher and we also not requested
         * subscribe(Consumer<? super Integer>)
         * because we are using the consmer it is automatically it will make a request with the subscriber
         */
        mono.subscribe(
                (i) -> log.info("Received Data from Publisher {}", i),
                err -> log.error("Received an Error", err),
                () -> log.info("Completed"));

        mono.subscribe(
                (i) -> log.info("Received Data from Publisher {}", i),
                err -> log.error("Received an Error", err),
                () -> log.info("Completed"),
                subscription -> subscription.request(10)
                );
    }
}
