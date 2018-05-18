package com.huishu.openeyes.common;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/17
 */
@Component
public class DataCheckUtil {


    public boolean checkLocalDataIsExpire(List<Object> list) {
        if(list.size() == 0){
            return false;
        }
        try {
            Method getCreationTime = list.get(0).getClass().getDeclaredMethod("getCreationTime");
            Long dataTime = (Long) getCreationTime.invoke(list.get(0), null);
            long timeLeft = System.currentTimeMillis() - dataTime;
            if (timeLeft >= KeyConstan.FAILURE_TIME) {
                return true;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }
}
