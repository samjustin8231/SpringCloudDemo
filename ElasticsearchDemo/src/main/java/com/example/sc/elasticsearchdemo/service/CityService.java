package com.example.sc.elasticsearchdemo.service;

import com.example.sc.elasticsearchdemo.domain.City;

import java.util.List;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/7/25 17:49
 */
public interface CityService {

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    Long saveCity(City city);

    /**
     * 根据关键词，function score query 权重分分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}
