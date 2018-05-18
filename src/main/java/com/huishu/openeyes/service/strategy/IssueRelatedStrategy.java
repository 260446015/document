package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.IssueRelated;
import com.huishu.openeyes.repository.IssueRelatedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class IssueRelatedStrategy implements ISaveData {
    @Autowired
    private IssueRelatedRepository issueRelatedRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONObject jsonArr = jsonObject.getJSONObject("result");
        if (jsonArr != null || jsonArr.size() == 0) {
            IssueRelated parseObject = JSONObject.parseObject(jsonArr.toJSONString(), IssueRelated.class);
            parseObject.setCreationTime(System.currentTimeMillis());
            String id = getGeneratedId(parseObject);
            parseObject.setCname(name);
            parseObject.setId(id);
            try {
                issueRelatedRepository.save(parseObject);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
