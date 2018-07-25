package com.example.sc.redisdemo.service;

import com.example.sc.redisdemo.domain.City;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/7/25 16:46
 */
public interface CityService {

    public City findCityById(Long id);

    public Long saveCity(City city);

    public Long updateCity(City city);

    public Long deleteCity(Long id);

}
