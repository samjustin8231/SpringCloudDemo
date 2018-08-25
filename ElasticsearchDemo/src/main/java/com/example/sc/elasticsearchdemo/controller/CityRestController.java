package com.example.sc.elasticsearchdemo.controller;

import com.example.sc.elasticsearchdemo.domain.City;
import com.example.sc.elasticsearchdemo.service.CityService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/7/25 17:52
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * http://127.0.0.1:8080/api/city
     * {
     * "id":2,
     * "provinceid":"1002",
     * "cityname":"江苏",
     * "description":"江苏"
     * }
     *
     * @param city
     * @return
     */
    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public Long createCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    /**
     * http://127.0.0.1:8080/api/city/search?pageNumber=0&pageSize=10&searchContent=江苏
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    @RequestMapping(value = "/api/city/search", method = RequestMethod.GET)
    public List<City> searchCity(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return cityService.searchCity(pageNumber, pageSize, searchContent);
    }

    /**
     * AND 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    @RequestMapping(value = "/api/city/and/find", method = RequestMethod.GET)
    public List<City> findByDescriptionAndScore(@RequestParam(value = "description") String description,
                                                @RequestParam(value = "score") Integer score) {
        return cityService.findByDescriptionAndScore(description, score);
    }

    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    @RequestMapping(value = "/api/city/or/find", method = RequestMethod.GET)
    public List<City> findByDescriptionOrScore(@RequestParam(value = "description") String description,
                                               @RequestParam(value = "score") Integer score) {
        return cityService.findByDescriptionOrScore(description, score);
    }

    /**
     * 查询城市描述
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/api/city/description/find", method = RequestMethod.GET)
    public List<City> findByDescription(@RequestParam(value = "description") String description) {
        return cityService.findByDescription(description);
    }

    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/api/city/description/not/find", method = RequestMethod.GET)
    public List<City> findByDescriptionNot(@RequestParam(value = "description") String description) {
        return cityService.findByDescriptionNot(description);
    }

    /**
     * LIKE 语句查询
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/api/city/like/find", method = RequestMethod.GET)
    public List<City> findByDescriptionLike(@RequestParam(value = "description") String description) {
        return cityService.findByDescriptionLike(description);
    }

    /**
     * 查询所有
     * @throws Exception
     */
    @GetMapping("/cityall")
    public List<Map<String, Object>> searchAll() throws Exception {
        //这一步是最关键的
        Client client = elasticsearchTemplate.getClient();
        // @Document(indexName = "cityindex", type = "city")
        SearchRequestBuilder srb = client.prepareSearch("cityindex").setTypes("city");
        SearchResponse sr = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet(); // 查询所有
        SearchHits hits = sr.getHits();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSource();
            list.add(source);
            System.out.println(hit.getSourceAsString());
        }
        return list;
    }
}
