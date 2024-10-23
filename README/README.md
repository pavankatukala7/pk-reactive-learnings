# Reactive Programming

## Features

- Inbound and Outbound
![I/O](.././assets/io.png)

1. Sync + Blocking: When you call the insurance company the automated caller will make you wait until the agent comes online until then you will be waiting for the agent. This is called sync + blocking communication
2. Async: Instead of I am calling the insurance company I requested my friend or family member to call the insurance company on my behalf. In this case I am not blocked by the person whom I delegated the work they are blocked.
3. Non Blocking: I have called the insurance company now the automated teller will ask for the call back phone number so that we are not blocked on a call. Once we hang up the call we will get the call from the agent later in the future.
4. Non Blocking + Async : I will call the insurance company they will ask the phone number. Now instead of giving my number I will give my friends or family members number so that I am not blocked at any point of time.

All in all the reactive programming is to simplify the nonblocking async operations.

![I/O](.././assets/communication.png)

## Do I really need reactive programming? Why can't I use the virtual threads?

1. Request Response Model: If it is a simple request response model then using the virtual threads is a great idea to move forward.
   ![request response](.././assets/requestResponse.png)
2. But Reactive programming will open the doors for the additional communication patterns.
   ![All Communication Patterns](.././assets/allCommunication.png)

# What is a streaming response?
For a request you will receive multiple responses.

For Example: Lets say you ordered a pizza using an order app. Now you will get an ack that your order has been placed.
Now the restaurant will be sending the stream of responses to your order app that 
1. Your Pizza is in oven for backing.
2. Your Pizza is packaged and ready for delivery waiting for the delivery boy.
3. Delivery boy has your pizza and 10 miles away
4. After 5 mins your pizza is 1 mile away
5. In the last your pizza delivered at your door step.

# What is streaming request?

Let's consider that you are editing the google doc on a remote server. As you keep typing you are making a stream of request to update the doc.
In other words you will open a single connection through which we will send the multiple stream of messages.

# What is the bidirectional streaming?

This is like your what's app chat, Teams messenger where 2 people or 2 entities will be talking to each other through a communication channel.

All in all, All these communication patterns are possible in reactive programming.

## What is reactive programming / reactive streams specifications? 

![request response](.././assets/microservice.png)


All the tech gaints companies has realized that the applications are getting more complex. where they want the update or notifications more in real time to all platforms.

Every platform cannot do typical request and response model to get the latest update from the remote server instead the server has to notify to the clients when ever there is an update.

## Examples

1. What kind of movies the user is browsing
2. what kind of tweets now the server will monitor the stream of requests, because there are stream of requests and stream of responses happen back and forth in this case the traditional request and response model will not help.

3. [Reactive Streams org](https://www.reactive-streams.org/)
![request response](.././assets/reactive-streams.png)

# Concepts of Reactive Programming.

A programming paradigm designed to process stream of messages in a non blocking and async manner, while handling back pressure.

It is based on observer design pattern.

1. Async and Stream Processing
2. Non Blocking Back Pressure


# Publisher / Subscriber Pattern

1. The **Publisher** needs an instance of the **Subscriber** to work. It will pass the **Subscriber** instance to the **Subscription**.
2. The **Subscription** holds information about the **Subscriber**.
3. The **Publisher**, using the **Subscription** instance, invokes `onSubscribe()` to inform the **Subscriber** about the subscription.
4. In simpler terms, the **Publisher** hands over the **Subscriber's** details to the **Subscription** and notifies the **Subscriber** through the **Subscription** instance.

## PublisherImpl.java:

```
public class PublisherImpl implements Publisher<String> {
    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        SubscriptionImpl subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }
}

```

## SubscriberImpl.java:

```
public class SubscriberImpl implements Subscriber<String> {
    private Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String randomString) {
        log.info("Received: {}", randomString);
    }

    @Override
    public void onError(Throwable t) {
        log.error("Error encountered", t);
    }

    @Override
    public void onComplete() {
        log.info("Completed");
    }
}

```

## SubscriptionImpl.java:

```
public class SubscriptionImpl implements Subscription {
    private Subscriber<? super String> subscriber;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long n) {
        subscriber.onNext("Hello World");
    }

    @Override
    public void cancel() {
        log.info("Subscription cancelled");
    }
}

```

## ReactiveCodingApplication
```
public class ReactiveCodingApplication {

    public static void main(String[] args) {
        execute();
    }

    public static void execute() {
        PublisherImpl publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

        subscriber.getSubscription().request(3); // Request 3 items of data
    }
}

```

# Reactor Publisher

There are two types Reactor publishers 
1. Mono<T> : The mono can emmit 0 or 1 item followed with onComplete / onError
  
  If you are completely sure that only 1 item will be published from the publisher then it is recommended to use MONO

2. Flux<T> : The flux can emit 0, 1... or n items followed with onComplete / onError

## Why Mono and Flux?

1. Flux:
   <ol>
      <li>Stream of messages</li>
      <li>Back Pressure</li>
      <li>Many additional method to handle the stream processing</li>
   </ol>

2. Mono

   <ol>
      <li>No Stream</li>
      <li>No Back Pressure</li>
      <li>A lightweight publisher</li>
      <li>Request Response Model</li>
   </ol>


Here are some examples to better illustrate **laziness in reactive streams**:

# Why are Reactive Streams Lazy?

### Example: **Ordering Food in a Restaurant**
Imagine you go to a restaurant:
- You look at the menu (which is like subscribing).
- You don’t want all the food on the menu immediately. You only order a dish when you’re ready (requesting data).
- The chef won’t cook until you place an order. The chef waits for you to request food before starting (publisher being lazy).
- If you decide to stop ordering (cancel subscription), the chef won’t prepare anything more, avoiding waste.

**Reactive streams work the same way: the publisher (chef) only produces data (food) when the subscriber (you) asks for it.**


### **Streams Example**

```
public class LazyStream {

    public static final Logger log = LoggerFactory.getLogger(LazyStream.class);

    public static void main(String[] args) {
                Stream.of(10)
                .peek((i) -> {
                    for (int j = 0; j <= i; j++) {
                        log.info("Data: {}", j);
                    }
                }).toList();
    }
}

```

### Key points:
1. **Stream creation (`Stream.of(10)`)**:  
   This creates a stream of one element (`10`). However, just creating a stream does nothing by itself—it's lazy. No data is processed at this stage.

2. **Intermediate Operation (`peek`)**:  
   The `peek` operation is used to inspect elements in a stream. This is an intermediate operation, meaning it **doesn’t trigger the stream to run**. Instead, it just sets up a transformation that will happen later when a terminal operation is called.

   In your case, `peek` is logging a series of numbers from 0 to `i` (in this case, from 0 to 10). However, even this logging code is not executed immediately because the stream is still lazy.

3. **Terminal Operation (`toList()`)**:  
   The stream remains lazy until a terminal operation is invoked. Here, `toList()` is a terminal operation, and it triggers the stream to actually run. Only when `toList()` is called does the `peek` operation finally execute, and the `for` loop inside it starts logging the data.

### How laziness works in this example:
- **Before `toList()`**: No data processing happens, the `for` loop in `peek` does not run. The stream is just a pipeline of potential operations that could be executed, but they aren't yet.
- **After `toList()`**: The terminal operation forces the stream to process its data. Now the `peek` operation starts logging numbers from 0 to 10, and only then does the stream finish.

### Why is this lazy?
- The intermediate operation (`peek`) is only configured when the stream is set up, but nothing happens until the terminal operation (`toList()`) is called.
- Without `toList()` or another terminal operation like `forEach()` or `collect()`, **nothing would be logged**. The code inside `peek` would never run because the stream remains untriggered.

In short, the stream is lazy because it delays any actual work (processing the data and logging) until a terminal operation is invoked (`toList()`).


