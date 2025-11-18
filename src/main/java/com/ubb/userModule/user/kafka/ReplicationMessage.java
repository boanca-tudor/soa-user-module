package com.ubb.userModule.user.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplicationMessage<T> {
    protected String messageType;
    protected T payload;
}
