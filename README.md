# EasyMqttAndroidClient
 使Mqtt安卓客户端变的更简单、更好用

## 简介

 基于第三方开源框架Mqtt Android客户端封装的用于接收、发布推送消息的组件。

## 功能
1.支持消息的订阅与发布<br>
2.支持服务器的连接与断开<br>
3.支持连接状态的判断<br>
4.支持自动重连、清除session、心跳间隔、超时时间等配置

## 使用方法

### 第一步：在project的build.gradle 文件中添加JitPack依赖

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

### 第二步: 在Module的build.gradle文件中添加对本库的依赖

    dependencies {
        ...
        compile 'com.github.ZS-ZhangsShun:EasyMqttAndroidClient:1.0'
    }


### 第三步：开始使用（6.0及以上版本需要在代码中动态申请Manifest.permission.READ_PHONE_STATE权限 参考示例代码） 以下方法可直接拷贝到项目中使用 参数根据实际情况修改即可 详情参考demo中 MainActivity的示例代码

#### （1）构建MqttService对象

    /**
     * 构建EasyMqttService对象
     */
    private void buildEasyMqttService() {
        mqttService = new EasyMqttService.Builder()
                //设置自动重连
                .autoReconnect(true)
                //设置不清除回话session 可收到服务器之前发出的推送消息
                .cleanSession(false)
                //唯一标示 保证每个设备都唯一就可以 建议 imei
                .clientId("your clientId")
                //mqtt服务器地址 格式例如：tcp://10.0.261.159:1883
                .serverUrl("your mqtt servier url")
                //心跳包默认的发送间隔
                .keepAliveInterval(20)
                //构建出EasyMqttService 建议用application的context
                .bulid(this.getApplicationContext());
    }

#### （2）连接Mqtt服务器

    /**
     * 连接Mqtt服务器
     */
    private void connect() {
        mqttService.connect(new IEasyMqttCallBack() {
            @Override
            public void messageArrived(String topic, String message, int qos) {
                //推送消息到达
            }

            @Override
            public void connectionLost(Throwable arg0) {
                //连接断开
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken arg0) {

            }

            @Override
            public void connectSuccess(IMqttToken arg0) {
                //连接成功
            }

            @Override
            public void connectFailed(IMqttToken arg0, Throwable arg1) {
                //连接失败
            }
        });
    }

#### （3）订阅主题

    /**
     * 订阅主题 这里订阅三个主题分别是"a", "b", "c"
     */
    private void subscribe() {
        String[] topics = new String[]{"a", "b", "c"};
        //主题对应的推送策略 分别是0, 1, 2 建议服务端和客户端配置的主题一致
        // 0 表示只会发送一次推送消息 收到不收到都不关心
        // 1 保证能收到消息，但不一定只收到一条
        // 2 保证收到切只能收到一条消息
        int[] qoss = new int[]{0, 1, 2};
        mqttService.subscribe(topics, qoss);
    }

#### （4）关闭连接

    /**
     * 关闭连接
     */
    private void close() {
        mqttService.close();
    }


#### （5）断开连接

    /**
     * 断开连接
     */
    private void disconnect() {
        mqttService.disconnect();
    }

#### （6）判断连接状态

    /**
     * 判断服务是否连接
     */
    private boolean isConnected() {
        return mqttService.isConnected();
    }

#### （7）发布消息

    /**
     * 发布消息
     */
    private void publish(String msg, String topic, int qos, boolean retained) {
        mqttService.publish(msg, topic, qos, retained);
    }
