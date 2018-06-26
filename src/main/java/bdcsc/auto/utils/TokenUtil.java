package bdcsc.auto.utils;

import bdcsc.auto.config.Config;

/**
 * 获取tokenid
 * Created by mawenrui on 2018/6/12.
 */
public class TokenUtil {
    private static String tokenUrl = Config.get("tokenUrl");
    private static HttpRequest request = new HttpRequest();

    public static String getToken(){
        try{
            return request.get(tokenUrl);
        } catch (Exception e) {
            System.out.println("tokenid获取失败！");
            return "";
        }
    }
}
