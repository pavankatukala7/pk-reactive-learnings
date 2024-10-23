package com.pk.reactive_coding.mono;


import com.pk.reactive_coding.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * in the reactive programming we need to be lazy as much as possible
 * Some time you need to delay the execution do the work only when it is required else not required
 */
public class MonoSupplier {

    private static final Logger log = LoggerFactory.getLogger(MonoSupplier.class);

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3);

        /**
         * There is a problem with this approach
         * If I run the below it will go and call the sum method and then it will return out
         * It prints the logger, because no one subscribed to the Mono it will not print the sum value.
         * Here we do not have to call the sum method if no one is subscribed.
         * because of this we wasted some CPU cycles.
         * You need to run the computations only when it is required.
         * The Just can only be used when the value is in the memory.
         */
       Mono.just(sum(list));

        /**
         * Delaying the execution
         * The below will not print the logger because no one subscriber to it.
         */

        Mono.fromSupplier(() -> sum(list));

        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.subscriber());


    }

    private static int sum(List<Integer> list){
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt( a -> a).sum();
    }

}
