package bdcsc.auto.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mawenrui on 2018/6/16.
 */
public class MessageUtil {
    public static Map<String, String> initMessage(){
        // 常用固定参数
        Map<String, String> map = new HashMap<>();
        map.put("Product为空", "status\":\"FAIL\",\"message\":\"Unresolvable URL");
        map.put("Product空格", "status\":\"FAIL\",\"message\":\"No module");
        map.put("Product大写", "status\":\"FAIL\",\"message\":\"No module");
        map.put("Product乱码", "status\":\"FAIL\",\"message\":\"No module");
        map.put("Product错值", "status\":\"FAIL\",\"message\":\"No module");
        map.put("Module为空", "status\":\"FAIL\",\"message\":\"Unresolvable URL");
        map.put("Module空格", "status\":\"FAIL\",\"message\":\"No module");
        map.put("Module大写", "status\":\"FAIL\",\"message\":\"No module");
        map.put("Module乱码", "");
        map.put("Module错值", "status\":\"FAIL\",\"message\":\"No module");
        map.put("Method为空", "status\":\"FAIL\",\"message\":\"Unresolvable URL");
        map.put("Method空格", "status\":\"FAIL\",\"message\":\"No module");
        map.put("Method大写", "message\":\"the method should starts with a lower case");
        map.put("Method乱码", "status\":\"FAIL\",\"message\":\"No module");
        map.put("Method错值", "status\":\"FAIL\",\"message\":\"No module");
        map.put("apikey为空", "status\":\"FAIL\",\"message\":\"Unresolvable URL");
        map.put("apikey空格", "status\":\"FAIL\",\"message\":\"The apiKey should maches pattern");
        map.put("apikey大写", "status\":\"FAIL\",\"message\":\"The apiKey should maches pattern");
        map.put("apikey乱码", "status\":\"FAIL\",\"message\":\"The apiKey should maches pattern");
        map.put("apikey错值", "status\":\"FAIL\",\"message\":\"The apiKey should maches pattern");
        map.put("tokenid为空", "status\":\"FAIL\",\"message\":\"Unresolvable URL");
        map.put("tokenid空格", "status\":\"FAIL\",\"message\":\"Not specifis tokenId");
        map.put("tokenid大写", "message\":\"tokenId is not exists,tokenId");
        map.put("tokenid乱码", "status\":\"FAIL\",\"message\":\"Not specifis tokenId");
        map.put("tokenid错值", "status\":\"FAIL\",\"message\":\"Not specifis tokenId");

        return map;
    }

    public static Map<String, String> initMessage(Map<String, String> second) {
        Map<String, String> map = initMessage();
        map.putAll(second);
        return map;
    }

    public static Map<String, Integer> initCode(){
        Map<String, Integer> map = new HashMap<>();
        map.put("tokenid为空", 404);
        map.put("tokenid空格", 406);
        map.put("tokenid大写", 402);
        map.put("tokenid乱码", 406);
        map.put("tokenid错值", 406);

        return map;
    }

    public static Map<String, Integer> initCode(Map<String, Integer> second) {
        Map<String, Integer> map = initCode();
        map.putAll(second);
        return map;
    }
}
