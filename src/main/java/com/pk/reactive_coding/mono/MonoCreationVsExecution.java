package com.pk.reactive_coding.mono;

import com.pk.reactive_coding.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/**
 * This class demonstrates the difference between Mono creation and execution
 * in Reactive Programming with Reactor.
 *
 * Monos are lazy by default, meaning that the supplier logic inside a Mono
 * is not executed until the Mono is subscribed to.
 */
public class MonoCreationVsExecution {

    private static final Logger log = LoggerFactory.getLogger(MonoCreationVsExecution.class);

    /**
     * The main method demonstrates three different scenarios involving Mono creation
     * and execution:
     *
     * 1. Subscribing to a Mono, which triggers the execution of the supplier.
     * 2. Creating a Mono without subscribing, meaning no execution occurs.
     * 3. Wrapping a Mono in another Mono, demonstrating that the inner Mono is not
     *    automatically subscribed to unless explicitly done so.
     *
     * @param args The input arguments to the main method.
     */
    public static void main(String[] args) {

        // 1. Subscribing to the Mono, which triggers execution and generates the first name.
        getName()
                .subscribe(Util.subscriber());

        // 2. Calling getName() without subscribing to it, so no execution of the supplier happens.
        getName();

        // 3. Wrapping getName() inside another Mono, which calls getName() but does not
        //    subscribe to the inner Mono, meaning no first name is generated.
        Mono.fromSupplier(() -> getName())
                .subscribe(Util.subscriber());
    }

    /**
     * This method creates a Mono that supplies a random first name using the Faker library.
     *
     * The method logs "Entered the getName method" when called, but the actual first name
     * generation and logging of "generating the first name" only happens when the returned Mono
     * is subscribed to.
     *
     * Monos in Reactor are lazy, meaning the supplier inside the Mono is not executed
     * until someone subscribes to it.
     *
     * @return A Mono that generates a random first name when subscribed to.
     */
    private static Mono<String> getName() {
        log.info("Entered the getName method");
        return Mono.fromSupplier(() -> {
            log.info("generating the first name");
            return Util.faker().name().firstName();
        });
    }
}
