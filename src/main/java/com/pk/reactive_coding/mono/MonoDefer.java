package com.pk.reactive_coding.mono;

import com.pk.reactive_coding.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoDefer {

    private static final Logger log = LoggerFactory.getLogger(MonoDefer.class);

    public static void main(String[] args) throws InterruptedException {

        /**
         * Here we havent subscribed to the publisher but the log is still printing and waiting for a sec then exiting
         * Now I want to exit without waiting if no subscriber exisits.
         */
//        createPublisher();

        /**
         * this will not wait for the thread sleep to complete because there is no subscriber
         */
        Mono.defer(() -> {
            try {
                return createPublisher();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        /**
         * this will  wait for the thread sleep to complete because there is a subscriber
         */
        Mono.defer(() -> {
            try {
                return createPublisher();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).subscribe(Util.subscriber());

    }

    private static Mono<Integer> createPublisher() throws InterruptedException {
        log.info("creating the publisher");
        Thread.sleep(1000);
        List<Integer> list = List.of(1, 2, 3);
        return Mono.fromSupplier(() -> {
            try {
                return sum(list);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static int sum(List<Integer> list) throws InterruptedException {
        log.info("finding the sum of {}", list);
        Thread.sleep(1000);
        return list.stream().mapToInt( a -> a).sum();
    }

}
