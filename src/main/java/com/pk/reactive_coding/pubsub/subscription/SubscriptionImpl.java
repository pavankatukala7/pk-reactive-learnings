package com.pk.reactive_coding.pubsub.subscription;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);

    private Subscriber<? super String> subscriber;

    public SubscriptionImpl(Subscriber<? super String> subscriber){
        this.subscriber = subscriber;
    }


    @Override
    public void request(long n) {
        log.info("request method has been invoked in subscription implementation");
        this.subscriber.onNext("Hello World");
    }

    @Override
    public void cancel() {
        log.info("subscriber has cancelled the subscription");
    }
}
