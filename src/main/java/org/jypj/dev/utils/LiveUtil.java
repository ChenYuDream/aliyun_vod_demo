package org.jypj.dev.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.live.model.v20161101.DescribeLiveSnapshotConfigRequest;
import com.aliyuncs.live.model.v20161101.DescribeLiveStreamsOnlineListRequest;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 直播工具类
 *
 * @author yu_chen
 * @create 2018-02-09 13:59
 **/
public class LiveUtil {

    /**
     * 主key
     */
    private static final String MAIN_KEY = "Dq6WIO7Kdt";

    private IAcsClient client;

    public static void main(String[] args) throws ClientException {
        LiveUtil liveUtil = new LiveUtil();
        liveUtil.getAuthKey("test", "stream01", "rtmp");
    }


    /**
     * 得到播放器的认证key
     *
     * @return
     */
    public String getAuthKey(String appName, String streamName, String type) {
        ///{AppName}/{StreamName}-{timestamp}-{rand}-{uid}-{privatekey}
        String baseDomain = "rtmp://live.jypj.org";
        String rand = "0";
        String uid = "0";
        String privateKey = "Dq6WIO7Kdt";
        Date date = new Date();
        String timeStamp = (date.getTime() + 60 * 60 * 1000) / 1000 + "";
        String baseUrl = String.format("/%s/%s-%s-%s-%s-%s", appName, streamName, timeStamp, rand, uid, privateKey);
        System.out.println(baseUrl);
        String md5Url = MD5Utils.md5Encrypt(baseUrl);
        String obsUrl = String.format("rtmp://video-center.alivecdn.com/%s/%s?vhost=live.jypj.org&auth_key=%s-%s-%s-%s", appName, streamName, timeStamp, rand, uid, md5Url);
        System.out.println("OBS中直播地址:" + obsUrl);
        String liveFormat = "";
        switch (type) {
            case "rtmp":
                liveFormat = "/%s/%s-%s-%s-%s-%s";
                break;
            case "flv":
                liveFormat = "/%s/%s.flv-%s-%s-%s-%s";
                break;
            case "m3u8":
                liveFormat = "/%s/%s.m3u8-%s-%s-%s-%s";
                break;
            default:
        }
        String liveUrl = String.format(liveFormat, appName, streamName, timeStamp, rand, uid, privateKey);
        System.out.println("liveUrl：" + liveUrl);
        String liveRealFormat = "/%s/%s?auth_key=%s-%s-%s-%s";
        String liveRealUrl = baseDomain + String.format(liveRealFormat, appName, streamName, timeStamp, rand, uid, MD5Utils.md5Encrypt(liveUrl));
        System.out.println(type + "直播地址：" + liveRealUrl);
        return "";
    }

    /**
     * 2. 初始化Client
     * SDK通过IAcsClient的instance来完成openAPI的调用，因此在您发起调用前，请先初始化IAcsClient实例。示例代码如下：
     *
     * @throws ClientException
     */
    public void init() {
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAIpoHj04Q5pZ4W", "Zp1DWewlePo7TZB5TC2nwe8QQqFVJf");
        //DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "live", "live.aliyuncs.com"); //添加自定义endpoint。
        client = new DefaultAcsClient(profile);
        //System.setProperty("http.proxyHost", "127.0.0.1"); //此设置用于设置代理，可用fiddler拦截查看http请求，便于调试。
        //System.setProperty("http.proxyPort", "8888");
    }

    /**
     * 3. 初始化请求
     * 发起API调用前，先初始化对应的请求的request实例，DescribeLiveSnapshotConfig（查询截图配置）接口为例，示例代码如下：
     */
    public String requestInitSample() {
        DescribeLiveStreamsOnlineListRequest describeLiveStreamsOnlineListRequest = new DescribeLiveStreamsOnlineListRequest();
        describeLiveStreamsOnlineListRequest.setDomainName("live.jypj.org");
        //describeLiveSnapshotConfigRequest.setProtocol(ProtocolType.HTTPS); //指定访问协议
        //指定api返回格式
        describeLiveStreamsOnlineListRequest.setAcceptFormat(FormatType.JSON);
        //describeLiveSnapshotConfigRequest.setMethod(MethodType.POST); //指定请求方法
        //describeLiveSnapshotConfigRequest.setRegionId("cn-shanghai");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。
        try {
            HttpResponse httpResponse = client.doAction(describeLiveStreamsOnlineListRequest);
            System.out.println(httpResponse.getUrl());
            String result = new String(httpResponse.getContent());
            System.out.println(result);
            return result;
            //todo something.
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


