package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.CompanyInfo;
import com.huishu.openeyes.repository.CompanyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class CompanyInfoStrategy implements ISaveData {
    @Autowired
    private CompanyInfoRepository companyInfoRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONObject jsonArr = jsonObject.getJSONObject("result");
        if (jsonArr != null || jsonArr.size() == 0) {
            CompanyInfo parseObject = JSONObject.parseObject(jsonArr.toJSONString(), CompanyInfo.class);
            parseObject.setCreationTime(System.currentTimeMillis());
            String id = getGeneratedId(jsonArr);
            parseObject.setId(id);
            try {
                companyInfoRepository.save(parseObject);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
