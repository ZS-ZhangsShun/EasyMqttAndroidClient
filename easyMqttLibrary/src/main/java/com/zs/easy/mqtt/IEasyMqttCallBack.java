package com.zs.easy.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * Created by zhangshun on 2017/10/16.
 */

public interface IEasyMqttCallBack {

    /**
     * 收到消息
     *
     * @param topic   主题
     * @param message 消息内容
     * @param qos     消息策略
     */
    void messageArrived(String topic, String message, int qos);

    /**
     * 连接断开
     *
     * @param arg0 抛出的异常信息
     */
    void connectionLost(Throwable arg0);

    /**
     * 传送完成
     *
     * @param arg0
     */
    void deliveryComplete(IMqttDeliveryToken arg0);

    /**
     * 连接成功
     *
     * @param arg0
     */
    void connectSuccess(IMqttToken arg0);

    /**
     * 连接失败
     *
     * @param arg0
     */
    void connectFailed(IMqttToken arg0, Throwable arg1);

}
