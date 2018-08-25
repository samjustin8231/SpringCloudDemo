package com.example.sb.springbootmybatis.reposity;

import com.example.sb.springbootmybatis.pojo.UserDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/8/26 0:27
 */

@Service("UserRepository")
public interface UserRepository extends ElasticsearchRepository<UserDO, Long> {

}
