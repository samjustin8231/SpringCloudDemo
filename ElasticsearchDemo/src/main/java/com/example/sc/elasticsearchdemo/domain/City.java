package com.example.sc.elasticsearchdemo.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 城市实体类
 *
 * @author sunyajun 2018/7/25 17:45
 */
@Document(indexName = "cityindex", type = "city")
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceid;

    /**
     * 城市名称
     */
    private String cityname;

    /**
     * 描述
     */
    private String description;

    /**
     * 城市评分
     */
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}