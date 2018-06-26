package bdcsc.auto.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * Created by mawenrui on 2018/6/12.
 */
public class HttpRequest {
    private HttpClient httpClient = HttpClients.createDefault();

    public String get(String url){
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(get);
            InputStream content = response.getEntity().getContent();
            return IOUtils.toString(content, "utf-8");
        } catch (IOException e) {
            System.out.println("http get 请求失败");
            e.printStackTrace();
            return "-";
        }
    }
}
