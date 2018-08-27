package com.kimzing.consumer.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义通道.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/8/26 21:54
 */
public interface ProviderInputAndOutPut {

    String INPUT = "provider";
    String OUTPUT = "consumer";
    String OUTPUT2 = "consumer2";

    /**
     * 如果不指定@Input的value属性， 默认以方法名作为value.
     *
     * @return org.springframework.messaging.SubscribableChannel
     */
    @Input(value = ProviderInputAndOutPut.INPUT)
    SubscribableChannel provider();

    @Output(value = ProviderInputAndOutPut.OUTPUT)
    MessageChannel consumer();

    @Output(value = ProviderInputAndOutPut.OUTPUT2)
    MessageChannel consumer2();

}
