package com.pk.reactive_coding.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

public class Util {

    private static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<T>("Default");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<T>(name);
    }

    public static Faker faker() {
        return faker;
    }

//    public static void main(String[] args) {
//        Mono<String> mono = Mono.just("1");
//
//        mono.subscribe(subscriber());
//
//        mono.subscribe(subscriber("example"));
//    }
}
