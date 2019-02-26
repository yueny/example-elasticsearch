package com.yueny.example.elasticsearch.es.service.impl;

import com.yueny.example.elasticsearch.es.service.ICrud;
import com.yueny.rapid.lang.json.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class EsJavaCrud implements ICrud, DisposableBean {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Client esClient;

    /**
     * closeConnection:(关闭连接)
     */
    @Override
    public void destroy() throws Exception {
        if (esClient != null) {
            esClient.close();
        }
    }

    @Override
    public <T> void put(String index, String type, T object) {
        try {
            // 转 json
            String jsonData = JsonUtil.toJson(object);

            ActionFuture<IndexResponse> future = esClient.index(new IndexRequest(index, type).source(jsonData));

            //此处会阻塞
            IndexResponse response = future.get();

            logger.info(response.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public <T>  IndexResponse indexCreate(String index, String type, T object) throws IOException {
        String jsonData = JsonUtil.toJson(object);

        IndexResponse response = esClient.prepareIndex(index, type).setSource(jsonData, XContentType.JSON).get();

        System.out.println("索引名称：" + response.getIndex());
        System.out.println("类型：" + response.getType());
        System.out.println("文档ID：" + response.getId()); // 第一次使用是1
        System.out.println("当前isCreated：" + response.isCreated());

        return response;
    }

    @Override
    public String query(String index, String type, String id) {
        try {
            GetResponse response = esClient.prepareGet(index, type, id).execute().actionGet();

            return response.getSourceAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> T query(String index, String type, String id, Class<T> valueType) {
        String jsonData = query(index, type, id);

        if(StringUtils.isEmpty(jsonData)){
            return null;
        }

        return JsonUtil.fromJson(jsonData, valueType);
    }

    @Override
    public <T> UpdateResponse update(String index, String type, String id, T object) {
        // 转 json
        String jsonData = JsonUtil.toJson(object);

        UpdateResponse response = esClient.prepareUpdate(index, type, id)
                .setDoc(jsonData, XContentType.JSON)
                .get();

        System.out.println("索引名称：" + response.getIndex());
        System.out.println("类型：" + response.getType());
        System.out.println("文档ID：" + response.getId());
        System.out.println("getGetResult：" + response.getGetResult());

        return response;
    }

    @Override
    public DeleteResponse delete(String index, String type, String id) {
        DeleteResponse response = esClient.prepareDelete(index, type, id).get();

        System.out.println("索引名称：" + response.getIndex());
        System.out.println("类型：" + response.getType());
        System.out.println("文档ID：" + response.getId());
        System.out.println("isFound：" + response.isFound());

        return response;
    }

}
