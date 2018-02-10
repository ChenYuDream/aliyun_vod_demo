package org.jypj.dev.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

/**
 * @author yu_chen
 * @create 2017-12-20 11:13
 **/
public class VideoPlay {

    //账号AK信息请填写(必选)
    private static final String accessKeyId = "LTAIpoHj04Q5pZ4W";
    //账号AK信息请填写(必选)
    private static final String accessKeySecret = "Zp1DWewlePo7TZB5TC2nwe8QQqFVJf";

    public static void main(String[] args) {
        getVideoPlayAuth("6e0a7a5962c042fc9067c5452f4ea6e3");
    }

    public static GetVideoPlayAuthResponse getVideoPlayAuth(String videoId) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        GetVideoPlayAuthResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            throw new RuntimeException("GetVideoPlayAuthRequest Server failed");
        } catch (ClientException e) {
            throw new RuntimeException("GetVideoPlayAuthRequest Client failed");
        }
        //播放凭证
        System.out.println(response.getPlayAuth());
        //视频Meta信息
        System.out.println(response.getVideoMeta());
        return response;
    }
}
