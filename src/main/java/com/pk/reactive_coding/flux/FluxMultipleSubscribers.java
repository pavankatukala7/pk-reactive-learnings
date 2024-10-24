package com.pk.reactive_coding.flux;

import com.pk.reactive_coding.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class FluxMultipleSubscribers {

    private static final Logger log = LoggerFactory.getLogger(FluxMultipleSubscribers.class);

    public static void main(String[] args) {

        var flux = Flux.just(1, 2, 3, 4, 5);

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

            flux
                .filter(i -> i % 2 == 0)
                    .map(i -> i + "a")
                .subscribe(Util.subscriber("sub2"));

    }
}
