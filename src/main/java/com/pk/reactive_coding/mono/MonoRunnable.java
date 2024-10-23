package com.pk.reactive_coding.mono;

import com.pk.reactive_coding.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;


/**
 * Just mono is used when we already have the value in the memory.
 * Supplier on the other hand emmitted the value after some computaions.
 * Now when you want to send the empty signal to the subscriber, you need to do some computations and then send empty then we can use the runnable
 */
public class MonoRunnable {

    private static final Logger log = LoggerFactory.getLogger(MonoCallable.class);

    public static void main(String[] args) {
        getProductName(1)
                .subscribe(Util.subscriber());

        getProductName(2)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getProductName(int productId){
        if(productId == 1){
            return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        }
        /**
         * Instead of doing this we can do what is defined below mono.empty()
         */
//        return Mono.empty();
        return Mono.fromRunnable(() -> notifyBusiness(productId));
    };

    private static void notifyBusiness(int productId) {
        log.info("notifying business on unavailable product {}", productId);
    }
}
