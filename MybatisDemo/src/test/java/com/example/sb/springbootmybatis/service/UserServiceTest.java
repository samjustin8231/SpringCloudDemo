package com.example.sb.springbootmybatis.service;

import com.example.sb.springbootmybatis.pojo.UserDO;
import com.example.sb.springbootmybatis.pojo.UserDOExample;
import com.example.sb.springbootmybatis.util.RedisUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/8/25 16:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    public static final String PRE_USER_INFO = "userinfo_";
    private final static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testInsert1000UsersToMysql() {
        for (int i = 0; i < 1000; i++) {
            UserDO user = new UserDO();
            user.setId(++i);
            user.setUserName("sam" + ++i);
            user.setPassword("123" + ++i);
            user.setPhone("1771737" + ++i);
            user.setAge(5 + i++);
            boolean count = userService.addUser(user);

        }
    }

    @Test
    public void testQueryOneMysql() {
        UserDO user = userService.getUserById(1);
        logger.info("user:{}", user.toString());

    }

    @Test
    public void testQueryAllMysql() {
        UserDOExample userDOExample = new UserDOExample();

        List<UserDO> userDOList = userService.selectByExample(userDOExample);
        userDOList.stream().forEach((UserDO userDO) -> {
            logger.info("user:{}", userDO.toString());
        });

    }

    //------------------------------------redis----------------------------------

    @Test
    public void testRedisQuery() {
        Object test = redisUtil.get("test");
        logger.info("test:{}", test);
    }

    @Test
    public void testRedisDel() {
        UserDOExample userDOExample = new UserDOExample();
        List<UserDO> userDOList = userService.selectByExample(userDOExample);
        userDOList.stream().forEach((UserDO userDO) -> {
            logger.info("user:{}", userDO.toString());
            redisUtil.del(PRE_USER_INFO + userDO.getId().toString());

        });
    }

    @Test
    public void testRedisAdd() {
        redisUtil.set("test", "www");
    }

    @Test
    public void testRedisImportData() {
        UserDOExample userDOExample = new UserDOExample();
        List<UserDO> userDOList = userService.selectByExample(userDOExample);
        userDOList.stream().forEach((UserDO userDO) -> {
            logger.info("user:{}", userDO.toString());
            redisUtil.set(PRE_USER_INFO + userDO.getId().toString(), userDO.toString());
        });
    }

    //------------------------------------------------------------------
    @Test
    public void testEsAdd() {
        UserDO userDO = new UserDO();
        userDO.setId(1);
        IndexQuery indexQuery = new IndexQueryBuilder().withId(userDO.getId().toString()).withObject(userDO).build();
        elasticsearchTemplate.index(indexQuery);
    }

    @Test
    public void testEsImportData() {
        UserDOExample userDOExample = new UserDOExample();
        List<UserDO> userDOList = userService.selectByExample(userDOExample);
        userDOList.stream().forEach((UserDO userDO) -> {
            logger.info("user:{}", userDO.toString());
            IndexQuery indexQuery = new IndexQueryBuilder().withId(userDO.getId().toString()).withObject(userDO).build();
            elasticsearchTemplate.index(indexQuery);
        });


    }

    @Test
    public void testEsQuery() {
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
            logger.info(hit.getSourceAsString());
        }
    }
}