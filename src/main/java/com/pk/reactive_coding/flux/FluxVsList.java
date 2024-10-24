package com.pk.reactive_coding.flux;

import com.pk.reactive_coding.common.Util;
import com.pk.reactive_coding.flux.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The list will wait for all the count to get completed, and it will give the result
 * Where on the other hand the Flux will publish the messages immediately
 */

public class FluxVsList {

    private static final Logger log = LoggerFactory.getLogger(FluxVsList.class);

    public static void main(String[] args) {

//        var list = NameGenerator.getNamesList(10);
//
//        log.info("List {}", list);

        /**
         * This will give the response immediately
         */

        NameGenerator.getNamesFlux(10)
                .subscribe(Util.subscriber());


    }
}
