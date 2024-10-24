package com.pk.reactive_coding.flux.helper;

import com.pk.reactive_coding.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {

    public static List<String> getNamesList(int count) {

        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> {
                    try {
                        return generateRandomNames();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

    }

    public static Flux<String> getNamesFlux(int count) {
        return Flux.range(1, count)
                .map(i -> {
                    try {
                        return generateRandomNames();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static String generateRandomNames() throws InterruptedException {
        Thread.sleep(1000);
        return Util.faker().name().firstName();
    }
}
