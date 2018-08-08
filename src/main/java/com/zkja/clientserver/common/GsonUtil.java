package com.zkja.clientserver.common;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zkja.clientserver.anno.SkipJSon;
import com.zkja.clientserver.domain.RespMessage;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @authon zzr
 */
public class GsonUtil {

    private static Logger logger = Logger.getLogger(GsonUtil.class);
    protected static GsonBuilder gsonBuilder;
    protected static Gson gson;
    static {
        //把值为null的字段也实体化输出
        gsonBuilder = new GsonBuilder().serializeNulls();
        ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getAnnotation(SkipJSon.class) != null;
            }
        };
        gsonBuilder
                .setPrettyPrinting()
                //.serializeNulls()
                .setExclusionStrategies(
                        new ExclusionStrategy[] { exclusionStrategy })
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setFieldNamingStrategy(new LowerCaseFieldNamingStrategy());
        gson = gsonBuilder.create();
    }

    public static String getJson(Object o,String bwlsh) {
        if(o == null){
            RespMessage a1RespError = new RespMessage();
            a1RespError.setRet("-1");
            a1RespError.setRetInfo("PID未注册");
            a1RespError.setResult(bwlsh);
            o = a1RespError;
        }
//		Gson gson = gsonBuilder.create();
        //.replace("null", "\"\""); //统一null转""
        String json = gson.toJson(o);
        return json;
    }

    public static String getJson(Object o) {
        if(o == null){
            RespMessage a1RespError = new RespMessage();
            a1RespError.setRet("-1");
            a1RespError.setRetInfo("PID未注册");
            o = a1RespError;
        }
//		Gson gson = gsonBuilder.create();
        //.replace("null", "\"\""); //统一null转""
        String json = gson.toJson(o);
        return json;
    }


    public static String toJson(Object o) {
        //.replace("null", "\"\""); //统一null转"";
        String json = gson.toJson(o);
        return json;
    }

    public static <T> T getEntityFromRequest(String json, Class<T> clazz)
            throws Exception {
        logger.debug("request content:\t{}" + json);
        if (StringUtils.isEmpty(json.trim())) {
            throw new DalException("request content is empty!!!");
        }

        T t = gson.fromJson(json, clazz);
//		if (t != null) {
//			vaildObject(t, null);
//		}
        return t;
    }

    public static void vaildObject(Object t, String profile) throws Exception {
        Validator validator = new Validator();
        if (!StringUtils.isEmpty(profile.trim())) {
            validator.disableAllProfiles();
            validator.enableProfile("default");
            validator.enableProfile(profile);
        }
        // TODO 添加具体的校验文件
        List<ConstraintViolation> validates = validator.validate(t);
        if ((validates != null) && (validates.size() > 0)) {
            throw new DalException("校验没有通过！！！！");
        }
    }
    
    public static Map<String, String> jsonToMap(String s){
    	Map<String, String> map = new HashMap<String, String>();
        return gson.fromJson(s, map.getClass());
    }
}
