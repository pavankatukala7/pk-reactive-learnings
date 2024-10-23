package com.pk.reactive_coding.mono;

import com.pk.reactive_coding.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoCallable {

    private static final Logger log = LoggerFactory.getLogger(MonoCallable.class);

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3);

        /**
         * Supplier and Callable both are functional interfaces in Java they are 2 different interfaces
         *
         * Supplier will not throw the exception or doesn't have the exception as method signature
         * Callable have the throws exception as a part of the method signature.
         */

        Mono.fromCallable(() -> sum(list))
                .subscribe(Util.subscriber());

        /**
         * The below will show the error that multiplication throws the exception and supplier is not capable to catch
         */
//        Mono.fromSupplier(() -> multiplication(list))
//                .subscribe(Util.subscriber());

        Mono.fromCallable(() -> multiplication(list))
                .subscribe(Util.subscriber());

    }

    private static int sum(List<Integer> list){
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt( a -> a).sum();
    }

    private static int multiplication(List<Integer> list) throws Exception {
        log.info("Multiplication {}", list);
        return list.stream()
                .reduce(1, (a, b) -> a * b); // Multiply all elements
    }
}
