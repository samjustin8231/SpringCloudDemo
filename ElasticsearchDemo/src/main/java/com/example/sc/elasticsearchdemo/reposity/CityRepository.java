package com.example.sc.elasticsearchdemo.reposity;

import com.example.sc.elasticsearchdemo.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/7/25 17:47
 */
@Repository
public interface CityRepository extends ElasticsearchRepository<City,Long> {


}
