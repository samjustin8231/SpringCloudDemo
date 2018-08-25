package com.example.sb.springbootmybatis.util;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/8/26 0:03
 */
public class ElasticSearchUtils {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public GetResponse query(String index, String type, String documentId) {
        Client client = elasticsearchTemplate.getClient();
        GetResponse getResponse = client.prepareGet(index, type, documentId).get();
        return getResponse;
    }

    public GetResponse queryForList(String index, String type, String documentId) {
        Client client = elasticsearchTemplate.getClient();
        GetResponse getResponse = client.prepareGet(index, type, documentId).get();
        return getResponse;
    }
}
