package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.News;
import com.huishu.openeyes.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class NewsStrategy implements ISaveData {
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONArray("result");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<News> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                News parseObject = JSONObject.parseObject(obj.toString(), News.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                String id = getGeneratedId(parseObject);
                parseObject.setId(id);
                parseObject.setCompanyName(name);
                list.add(parseObject);
            });
            try {
                newsRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
