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

Every platform cannot do polling to get the latest update from the remote server instead the server has to notify to the clients when ever there is an update.

The other example, What kind of movies the user is browsing, what kind of tweets now the server will monitor the stream of requests, because there are stream of requests and stream of responses happen back and forth in this case the traditional request and response model will not help.

- Feature 2
- Feature 3

