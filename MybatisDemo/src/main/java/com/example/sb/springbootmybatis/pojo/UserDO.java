package com.example.sb.springbootmybatis.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
//索引不能大写
@Document(indexName = "user_index", type = "userinfo")
public class UserDO implements Serializable {
    @Id
    private Integer id;

    private String userName;

    private String password;

    private Integer age;

    private String phone;

}