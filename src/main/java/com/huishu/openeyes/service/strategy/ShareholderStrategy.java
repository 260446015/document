package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.ShareHolder;
import com.huishu.openeyes.repository.ShareHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class ShareholderStrategy implements ISaveData {
    @Autowired
    private ShareHolderRepository shareHolderRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("holderList");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<ShareHolder> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                ShareHolder parseObject = JSONObject.parseObject(obj.toString(), ShareHolder.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setCname(name);
                list.add(parseObject);
            });
            try {
                shareHolderRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
