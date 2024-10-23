package com.pk.reactive_coding.streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class LazyStream {

    public static final Logger log = LoggerFactory.getLogger(LazyStream.class);

    public static void main(String[] args) {
//        Stream.of(10)
//                .peek((i) -> log.info("Data: {}", i));
                Stream.of(10)
                .peek((i) -> {
                    for (int j = 0; j <= i; j++) {
                        log.info("Data: {}", j);
                    }
                }).toList();
    }
}
