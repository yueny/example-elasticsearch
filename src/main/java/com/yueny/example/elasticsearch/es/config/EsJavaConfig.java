package com.yueny.example.elasticsearch.es.config;

import com.yueny.rapid.lang.thread.hook.ShutdownHookService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class EsJavaConfig {

    //es服务器地址
//    @Value("${es.server.name}")
//    private String serverName;

//    @Value("${es.cluster.name}")
    private String clusterName = "muzinuo";

    //es服务器名称
    //@Value("${es.server.address)")
    private String serverAddress = "127.0.0.1";
    //es服务器端口
    //@Value("${es.server.port}")
    private int serverPort = 9200;

    @Bean
    public TransportClient esClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                //指定集群名称
                .put("cluster.name", clusterName)
                //探测集群中机器状态
                .put("client.transport.sniff", true).build();

        /*
         * 创建客户端，所有的操作都由客户端开始，这个就好像是JDBC的Connection对象
         * 用完记得要关闭
         */
        TransportClient client = new PreBuiltTransportClient(settings) //Settings.EMPTY
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(serverAddress), serverPort));

        // 增加钩子
//        ShutdownHookService.
        // client.close();

        return client;
    }

}
