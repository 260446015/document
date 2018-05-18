package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Bids;
import com.huishu.openeyes.repository.BidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class BidsStrategy implements ISaveData{
    @Autowired
    private BidsRepository bidsRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<Bids> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                JSONObject parseObject = JSONObject.parseObject(obj.toString());
                Bids bid = new Bids();
                bid.setCreationTime(System.currentTimeMillis());
                bid.setAbs(parseObject.getString("abs"));
                bid.setCname(name);
                bid.setContent(parseObject.getString("content"));
                bid.setIntro(parseObject.getString("intro"));
                bid.setLink(parseObject.getString("link"));
                bid.setProxy(parseObject.getString("proxy"));
                bid.setPublishTime(parseObject.getLong("publishTime"));
                bid.setPurchaser(parseObject.getString("purchaser"));
                bid.setTitle(parseObject.getString("title"));
                bid.setUuid(parseObject.getString("uuid"));
                list.add(bid);
            });
            try {
                bidsRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
