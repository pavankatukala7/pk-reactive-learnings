package com.pk.reactive_coding.mono;

import com.pk.reactive_coding.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * If you already have a CompletableFuture that we can convert to Mono
 */

public class MonoFuture {

    private static final Logger log = LoggerFactory.getLogger(MonoFuture.class);

    public static void main(String[] args) throws InterruptedException{

        /**
         * Here it printed the log but didn't returned the value
         *
         * Mono.fromFuture(getName()), the program continues its execution immediately without waiting for the future to complete.
         * Since getName() generates the name asynchronously using CompletableFuture.supplyAsync(),
         * the main thread may exit before the future completes and emits the result.
         */
        Mono.fromFuture(getName())
                .subscribe(Util.subscriber());


        /**
         * because we are using CompletableFuture.supplyAsync the main thread will exit before this thread exists
         * Here we need to block the main thread for the CompletableFuture.supplyAsync to return the value.
         */

        Thread.sleep(2000);  // Sleep for a couple of seconds to give enough time for the asynchronous task to complete

        /**
         * CompletableFuture is not lazy, we can also user the supplier to perform the lazy operation
         */

        Mono.fromFuture(() -> getName());

        Mono.fromFuture(() -> getName())
                .subscribe(Util.subscriber());

    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("generating a name");
            return Util.faker().name().firstName();
        });
    }
}
