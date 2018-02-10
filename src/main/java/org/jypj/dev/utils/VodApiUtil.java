package org.jypj.dev.utils;

import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.pojo.VideoInfo;
import org.jypj.dev.pojo.VideoSearchCondition;

import java.util.HashMap;
import java.util.Map;

/**
 * 视频点播相关的工具类
 *
 * @author yu_chen
 * @create 2017-12-19 17:01
 **/
public class VodApiUtil {

    public static void main(String[] args) {
        VideoInfo videoInfo = VideoInfo.builder()
                .description("testDesc")
                .videoId("281e9ae2b01a4ded9ff6b93d42823296")
                .tags("测试1,测试2")
                .coverUrl("http://vod.jypj.org/snapshot/281e9ae2b01a4ded9ff6b93d4282329600006.jpg")
                .build();
        System.out.println(getVideoList(null));
    }

    /**
     * 通过视频ID获取视频的基本信息，包括：视频标题、描述、时长、封面URL、状态、创建时间、大小、截图、分类和标签等信息。
     *
     * @param videoId 视频ID
     * @return RequestId    String	请求ID
     * Video	Video	视频信息
     */
    public static String getVideoInfo(String videoId) {
        Map<String, String> privateParams = new HashMap<>();
        // 视频ID
        privateParams.put("VideoId", videoId);
        // API名称
        privateParams.put("Action", "GetVideoInfo");

        return SignatureUtil.sendHttpGet(privateParams);
    }

    /**
     * 修改视频信息。注意：传入参数则更新相应字段，否则该字段不会被覆盖或更新。
     *
     * @param videoInfo 视频的信息 只更新参数不为空的数据
     * @return
     */
    public static String updateVideoInfo(VideoInfo videoInfo) {
        Map<String, String> privateParams = new HashMap<>();
        // API名称
        privateParams.put("Action", "UpdateVideoInfo");
        privateParams.put("VideoId", videoInfo.getVideoId());
        privateParams.put("Title", videoInfo.getTitle());
        privateParams.put("Description", videoInfo.getDescription());
        privateParams.put("CoverURL", videoInfo.getCoverUrl());
        privateParams.put("CateId", videoInfo.getCateId());
        privateParams.put("Tags", videoInfo.getTags());
        return SignatureUtil.sendHttpGet(privateParams);
    }

    /**
     * 删除视频，并且支持批量删除。
     *
     * @param videoIds
     * @return
     */
    public static String deleteVideo(String videoIds) {
        Map<String, String> privateParams = new HashMap<>();
        // 视频ID
        privateParams.put("VideoIds", videoIds);
        // API名称
        privateParams.put("Action", "DeleteVideo");

        return SignatureUtil.sendHttpGet(privateParams);
    }

    /**
     * 获取视频信息列表。最大支持获取前5000条。
     *
     * @param videoSearchCondition
     * @return
     */
    public static String getVideoList(VideoSearchCondition videoSearchCondition) {
        Map<String, String> privateParams = new HashMap<>();
        // API名称
        privateParams.put("Action", "GetVideoList");
        if (videoSearchCondition != null) {
            privateParams.put("Status", videoSearchCondition.getStatus());
            privateParams.put("StartTime", videoSearchCondition.getStartTime());
            privateParams.put("EndTime", videoSearchCondition.getEndTime());
            privateParams.put("CateId", StringUtils.stripToNull(String.valueOf(videoSearchCondition.getCateId())));
            privateParams.put("PageNo", StringUtils.stripToNull(String.valueOf(videoSearchCondition.getPageNo())));
            privateParams.put("PageSize", StringUtils.stripToNull(String.valueOf(videoSearchCondition.getPageSize())));
            privateParams.put("SortBy", videoSearchCondition.getSortBy());
        }
        return SignatureUtil.sendHttpGet(privateParams);
    }

    /**
     * 删除媒体流(视频流，音频流)信息及存储文件，并且支持批量删除。
     *
     * @param videoId 视频ID
     * @param jobIds  媒体流转码的作业ID列表，多个用逗号分隔，最多支持同一个视频下的20个作业ID。JobId通过获取播放地址接口(GetPlayInfo)返回的PlayInfo结构体中获取，每个媒体流对应的JobId不同
     * @return RequestId    String	请求ID
     */
    public static String deleteStream(String videoId, String jobIds) {
        Map<String, String> privateParams = new HashMap<>();
        // 视频ID
        privateParams.put("VideoId", videoId);
        // API名称
        privateParams.put("JobIds", jobIds);
        privateParams.put("action", "DeleteStream");

        return SignatureUtil.sendHttpGet(privateParams);
    }

    /**
     * 获取视频源文件地址。
     * <p>
     * 注：当一路流转码完成后才可以获取到完整的源文件信息
     *
     * @param videoId     视频ID 必须
     * @param authTimeout FileURL签名过期时间，默认为3600秒，支持设置最小值为1 非必须
     * @return
     */
    public static String getMezzanineInfo(String videoId, Long authTimeout) {
        Map<String, String> privateParams = new HashMap<>();
        // 视频ID
        privateParams.put("VideoId", videoId);
        // API名称
        privateParams.put("AuthTimeout", authTimeout == null ? null : authTimeout.toString());
        privateParams.put("action", "GetMezzanineInfo");

        return SignatureUtil.sendHttpGet(privateParams);
    }

}
