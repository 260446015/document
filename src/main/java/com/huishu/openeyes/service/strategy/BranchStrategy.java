package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Branch;
import com.huishu.openeyes.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class BranchStrategy implements ISaveData {

    @Autowired
    private BranchRepository branchRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null) {
            List<Branch> list = new ArrayList<>();
            jsonArray.forEach(obj -> {
                Branch parseObject = JSONObject.parseObject(obj.toString(), Branch.class);
                parseObject.setCompanyName(name);
                parseObject.setCreationTime(System.currentTimeMillis());
                list.add(parseObject);
            });
            try {
                branchRepository.save(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
