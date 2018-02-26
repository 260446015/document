package com.huishu.openeyes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.openeyes.repository.BaseInfoRepository;
import com.huishu.openeyes.repository.BranchRepository;
import com.huishu.openeyes.repository.StaffRepository;

@Service
public class SearchLocalServiceImpl implements SearchLocalService{

	
	@Autowired
	private BaseInfoRepository baseInfoRepository;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private BranchRepository branchRepository;
	
	@Override
	public Object searchFromLocal(String name) {
		return null;
		
	}

}
