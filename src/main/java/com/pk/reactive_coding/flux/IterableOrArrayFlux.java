package com.pk.reactive_coding.flux;


import com.pk.reactive_coding.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * When you have an iterable like list or set how to convert that to Flux
 * Similarly Array how to convert that to Flux
 * Java Stream can also be converted into a Flux
 */
public class IterableOrArrayFlux {

    public static void main(String[] args) {

        List<String> list = List.of("a", "b", "c");

//        Flux.fromIterable(list)
//                .subscribe(Util.subscriber());
//
//        Integer[] arr = {1, 2, 3, 4, 5, 6};
//
//        Flux.fromArray(arr)
//                .subscribe(Util.subscriber());

        var stream = list.stream();

        Flux.fromStream(stream)
                .subscribe(Util.subscriber("sub1"));

        /**
         * stream has already been operated upon or closed
         * in Java streams can only be consumed only once how can we avoid this
         */
//        Flux.fromStream(stream)
//                .subscribe(Util.subscriber("sub2"));

        /**
         * If multiple subsribers want the streams then we need to provide the supplier of the stream inside the flux
         */

        Flux.fromStream(() -> list.stream())
                .subscribe(Util.subscriber("Sub2"));

    }


}
