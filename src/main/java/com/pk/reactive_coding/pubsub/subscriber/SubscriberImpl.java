package com.pk.reactive_coding.pubsub.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberImpl implements Subscriber<String> {

    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);

    private Subscription subscription;

    public Subscription getSubscription(){
        return subscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
    }

    @Override
    public void onNext(String randomString) {
        log.info("Received a string on onNext method {}", randomString);
    }

    @Override
    public void onError(Throwable t) {
        log.error("Received a string on onNext method", t);
    }

    @Override
    public void onComplete() {
        log.info("onComplete method has been invoked");
    }
}
