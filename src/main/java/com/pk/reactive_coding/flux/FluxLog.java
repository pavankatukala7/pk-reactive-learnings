package com.pk.reactive_coding.flux;

import com.pk.reactive_coding.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;



public class FluxLog {

    private static final Logger log = LoggerFactory.getLogger(FluxLog.class);

    public static void main(String[] args) {

        Flux.range(1, 5)
                .log("range")
                .subscribe(Util.subscriber());

    }
}
