package com.pk.reactive_coding.flux;

import com.pk.reactive_coding.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxJust {

    public static void main(String[] args) {

        /**
         *  List.of(1, 2, 3, 4);
         *  The list has the factory method of to create arbitrary elements similar to this
         *  The flux also has just method to create a publisher
         */

        Flux.just(1)
                .subscribe(Util.subscriber());

        Flux.just(1, 2, 3, 4, "sam")
                .subscribe(Util.subscriber());

    }

}
