package openeyes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpUtils;

import org.apache.http.client.utils.HttpClientUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import antlr.collections.List;

public class Test {

	public static void main(String[] args) {

		String s = "{\"result\":{\"total\":26,\"items\":[{\"webSite\":[\"www.suningbank.com\"],\"ym\":\"suningbank.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2017-06-22\",\"webName\":\"苏宁银行-中国O2O银行的领导者\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suning.cn;click.suning.cn\"],\"ym\":\"suning.cn\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁-中国商业的领先者（www.suning.cn）\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.uedwp.com\"],\"ym\":\"uedwp.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁易付宝\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.laox.cn\"],\"ym\":\"laox.cn\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"www.laox.cn\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suningcdn.com;www.suningcdn.net;www.suningcdn.cn;www.suningdns.com;www.suningdns.net;www.suningdns.cn\"],\"ym\":\"suningcdn.cn\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suninggslb.net\"],\"ym\":\"suninggslb.net\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁GSLB\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.cloudytrace.com;www.cloudytrace.cn;www.cloudytrace.org\"],\"ym\":\"cloudytrace.cn\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"云迹（cloudytrace）\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.cloudytrace.com;www.cloudytrace.cn;www.cloudytrace.org\"],\"ym\":\"cloudytrace.org\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"云迹（cloudytrace）\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suninggslb.cn\"],\"ym\":\"suninggslb.cn\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁云商城-为您提供专业的云服务\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.yifubao.com\"],\"ym\":\"yifubao.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"易付宝\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.redbaby.com.cn\"],\"ym\":\"redbaby.com.cn\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"www.redbaby.com.cn\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suningcdn.com;www.suningcdn.net;www.suningcdn.cn;www.suningdns.com;www.suningdns.net;www.suningdns.cn\"],\"ym\":\"suningcdn.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suningcdn.com;www.suningcdn.net;www.suningcdn.cn;www.suningdns.com;www.suningdns.net;www.suningdns.cn\"],\"ym\":\"suningcdn.net\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suningcdn.com;www.suningcdn.net;www.suningcdn.cn;www.suningdns.com;www.suningdns.net;www.suningdns.cn\"],\"ym\":\"suningdns.net\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suningcdn.com;www.suningcdn.net;www.suningcdn.cn;www.suningdns.com;www.suningdns.net;www.suningdns.cn\"],\"ym\":\"suningdns.cn\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suning.com;yunxin.suning.com\"],\"ym\":\"suning.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁易购\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.wuliuyun.com\"],\"ym\":\"wuliuyun.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"物流云平台\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.10035.com.cn\"],\"ym\":\"10035.com.cn\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁互联\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.chinadzfp.com\"],\"ym\":\"chinadzfp.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"南京市电子发票服务平台\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suningcdn.com;www.suningcdn.net;www.suningcdn.cn;www.suningdns.com;www.suningdns.net;www.suningdns.cn\"],\"ym\":\"suningdns.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.redmama.com.cn;www.redmama.cn\"],\"ym\":\"redmama.com.cn\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"redmama.com.cn\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.redmama.com.cn;www.redmama.cn\"],\"ym\":\"redmama.cn\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"redmama.com.cn\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.cnsuning.com;svp.cnsuning.com\"],\"ym\":\"cnsuning.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁中国商业的领导者\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suningcloud.com\"],\"ym\":\"suningcloud.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁云商城-为您提供专业的云服务\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.cloudytrace.com;www.cloudytrace.cn;www.cloudytrace.org\"],\"ym\":\"cloudytrace.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"云迹（cloudytrace）\",\"companyName\":\"苏宁云商集团股份有限公司\"},{\"webSite\":[\"www.suninghotel.com\"],\"ym\":\"suninghotel.com\",\"companyType\":\"企业\",\"liscense\":\"苏ICP备10207551号\",\"examineDate\":\"2018-01-05\",\"webName\":\"苏宁酒店及度假村官网\",\"companyName\":\"苏宁云商集团股份有限公司\"}]},\"reason\":\"ok\",\"error_code\":0}";
		JSONObject jsonObject = JSONObject.parseObject(s);
		System.out.println(jsonObject);
	}
	public static double getReturn(int i,double total){
		i --;
		if(i == 1){
			System.out.println("最后剩余金额:"+total);
			return total;
		}
		double subMoney = new Random().nextFloat() * total;
		total = total - subMoney;
		System.out.println(subMoney);
		return getReturn(i,total);
	}
}
