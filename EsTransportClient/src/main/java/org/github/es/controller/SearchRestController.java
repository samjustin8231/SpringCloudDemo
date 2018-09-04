package org.github.es.controller;

import com.alibaba.fastjson.JSONObject;
import org.github.es.ESWebStatusEnum;
import org.github.es.vo.ResponseVo;
import org.github.es.model.Person;
import org.github.es.service.ESSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 搜索服务
 * <p>
 * 备注：具体的服务在ESSearchService这个service里，请关注这个service
 *
 * @author sdc
 */
@RestController
@RequestMapping("/search")
public class SearchRestController extends BaseController {

    @Autowired
    private ESSearchService esSearchService;

    /**
     * 构建索引
     *
     * @param index
     * @return
     */
    @RequestMapping(value = "/buildIndex")
    @ResponseBody
    public ResponseVo<?> buildIndex(
            @RequestParam(value = "index", required = false) String index
    ) {
        //判空
        if (index == null) {
            return generateResponseVo(ESWebStatusEnum.FAILED, null);
        }
        esSearchService.buildIndex(index);
        return generateResponseVo(ESWebStatusEnum.SUCCESS, null);
    }

    /**
     * 查询数据
     *
     * @param index
     * @param type
     * @param id
     * @return
     */
    @RequestMapping(value = "/data")
    @ResponseBody
    public ResponseVo<?> search(
            @RequestParam(value = "index", required = false) String index,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "id", required = false) String id
    ) {
        //判空
        if (index == null || type == null || id == null) {
            return generateResponseVo(ESWebStatusEnum.FAILED, null);
        }
        //搜索具体的数据来源
        Map<String, Object> returnMap = esSearchService.searchDataByParam(index, type, id);
        return generateResponseVo(ESWebStatusEnum.SUCCESS, returnMap);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseVo<?> createCity(@RequestBody Person person, @RequestParam(value = "index", required = false) String index, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "id", required = false) String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", person.getName());
        String s = esSearchService.addTargetDataALL(jsonObject, index, type, id);
        return generateResponseVo(ESWebStatusEnum.SUCCESS, s);
    }

}
