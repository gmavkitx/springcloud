package com.kimzing.consumer.stream.user;

import com.kimzing.consumer.client.provider.dto.user.UserDTO;
import com.kimzing.consumer.stream.channel.ProviderInputAndOutPut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.annotation.Resource;

/**
 * 接受用户创建的消息.
 *
 * @author kim - kimzing@163.com
 * @since 2018/8/26 21:15
 */
@Slf4j
@EnableBinding({Sink.class, ProviderInputAndOutPut.class})
public class UserRecieverAndPublish {

    @Resource
    ProviderInputAndOutPut providerInputAndOutPut;

    //使用Stream提供的Sink
    @StreamListener(Sink.INPUT)
    public void reciever(UserDTO userDTO) {
        log.info("reciever user is {}", userDTO);
    }

    //使用自定义的接收通道并发送消息
    @StreamListener(ProviderInputAndOutPut.INPUT)
    @SendTo(ProviderInputAndOutPut.OUTPUT)
    public String myReciever(UserDTO userDTO) {
        log.info("reciever user is {}", userDTO);
        return userDTO.toString();
    }

    //接收上个方法的发送信息
    @StreamListener(ProviderInputAndOutPut.OUTPUT)
    public void myReciever(String message) {
        log.info("reciever my own pushlish message {}", message);
    }

}
