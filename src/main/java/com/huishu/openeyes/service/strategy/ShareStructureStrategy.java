package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.ShareStructure;
import com.huishu.openeyes.repository.ShareStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class ShareStructureStrategy implements ISaveData {
    @Autowired
    private ShareStructureRepository shareStructureRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("dataList");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<ShareStructure> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                ShareStructure parseObject = JSONObject.parseObject(obj.toString(), ShareStructure.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setCname(name);
                String id = getGeneratedId(obj);
                parseObject.setId(id);
                list.add(parseObject);
            });
            try {
                shareStructureRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
