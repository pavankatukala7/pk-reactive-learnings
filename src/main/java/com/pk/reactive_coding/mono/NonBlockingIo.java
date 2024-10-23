package com.pk.reactive_coding.mono;

import com.pk.reactive_coding.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonBlockingIo {

    private static final Logger log = LoggerFactory.getLogger(NonBlockingIo.class);

    public static void main(String[] args) throws InterruptedException {

        var client = new ExternalServiceClient();

        for (int i = 1; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Thread.sleep(2000);

    }
}
