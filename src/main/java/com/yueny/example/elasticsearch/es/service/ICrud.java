package com.yueny.example.elasticsearch.es.service;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.io.IOException;

public interface ICrud {

    /**
     * @param index index
     * @param type type
     * @param object 实体数据, 存储前会做 JsonUtil.toJson(object)
     *
     */
    <T> void put(String index, String type, T object);

    /**
     * 创建索引
     *
     * @param index index
     * @param type type
     * @param object 实体数据, 存储前会做 JsonUtil.toJson(object)
     *
     * @return
     */
    <T>  IndexResponse indexCreate(String index, String type, T object) throws IOException;

    /**
     * 查询
     *
     * @param index index
     * @param type type
     * @param id id
     *
     * @return json串
     */
    String query(String index, String type, String id);

    /**
     * 查询
     *
     * @param index index
     * @param type type
     * @param id id
     * @param valueType valueType
     *
     * @return T class bean
     */
    <T> T query(String index, String type, String id, Class<T> valueType);

    /**
     * 更新
     * @param index index
     * @param type type
     * @param id id
     * @param object 实体数据, 存储前会做 JsonUtil.toJson(object)
     *
     * @return UpdateResponse
     */
    <T> UpdateResponse update(String index, String type, String id, T object);

    /**
     * 删除
     *
     * @param index index
     * @param type type
     * @param id id
     *
     * @return DeleteResponse
     */
    DeleteResponse delete(String index, String type, String id);

}
