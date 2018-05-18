package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.CourtAnnouncement;
import com.huishu.openeyes.repository.CourtAnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class CourtAnnouncementStrategy implements ISaveData {
    @Autowired
    private CourtAnnouncementRepository courtAnnouncementRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<CourtAnnouncement> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                CourtAnnouncement parseObject = JSONObject.parseObject(obj.toString(), CourtAnnouncement.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setCname(name);
                list.add(parseObject);
            });
            try {
                courtAnnouncementRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
