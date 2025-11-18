package com.ubb.userModule.user.kafka;

public class ReplicationMessageBuilder {
    public static <T> ReplicationMessage<T> buildMessage(T object) {
        ReplicationMessage<T> message = new ReplicationMessage<>();
        message.setMessageType(object.getClass().getSimpleName());
        message.setPayload(object);
        return message;
    }
}
