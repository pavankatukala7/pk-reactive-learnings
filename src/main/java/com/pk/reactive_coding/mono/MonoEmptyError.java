package com.pk.reactive_coding.mono;

import com.pk.reactive_coding.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoEmptyError {

    private static final Logger log = LoggerFactory.getLogger(MonoEmptyError.class);

    public static void main(String[] args) {
        getUsername(1)
                .subscribe(Util.subscriber());
        getUsername(2)
                .subscribe(Util.subscriber());
        getUsername(3)
                .subscribe(Util.subscriber());

        /**
         *  this will give the Operator called default onErrorDropped
         *  In this case you only requesting the onNext to get the value but the Mono publisher thrown an error
         *  But you have provided data handler not the error handler
         */
        getUsername(3)
                .subscribe(
                        i -> System.out.println(i),
                        err -> System.out.println(err)
                );
    }

    private static Mono<String> getUsername(int userId){
        return switch (userId) {
            case 1 -> Mono.just("Hello User 1");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid Input"));
        };
    }
}
