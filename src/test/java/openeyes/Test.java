package openeyes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpUtils;

import org.apache.http.client.utils.HttpClientUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import antlr.collections.List;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<>();
		map.put("1", 1);
		map.put("2", 2);
		map.put("3",3);
		map.put("4", 4);
		System.out.println(map.toString());
	}
}
