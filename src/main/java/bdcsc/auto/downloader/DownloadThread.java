package bdcsc.auto.downloader;

import java.io.File;

/**
 * 请求网络，下载文件的线程
 * Created by mawenrui on 2018/6/29.
 */
public class DownloadThread implements Runnable{

    // http请求的文件的路径
    private String remoteUrl = null;

    // 本地文件的路径
    private String product = System.getProperty("user.dir") + File.separator;
    private String fileName = null;

    // 指针偏移量
    private long offset = 0;

    // 分配给线程的需要下载的字节数
    private long length = 0;


    DownloadThread(){

    }

    @Override
    public void run() {

    }
}
