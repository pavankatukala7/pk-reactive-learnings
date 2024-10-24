package com.pk.reactive_coding.flux;

import com.pk.reactive_coding.common.Util;
import reactor.core.publisher.Flux;

public class FluxRange {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());
    }
}
