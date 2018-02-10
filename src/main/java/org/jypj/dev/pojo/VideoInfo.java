package org.jypj.dev.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * 视频信息
 *
 * @author yu_chen
 * @create 2017-12-19 17:09
 **/
@Data
@Builder
public class VideoInfo {

    /**
     * 视频ID
     */
    private String videoId;
    /**
     * 视频标题，长度不超过128个字节，UTF8编码
     */
    private String title;

    /**
     * 视频描述，长度不超过1024个字节，UTF8编码
     */
    private String description;

    /**
     * 视频封面URL地址
     */
    private String coverUrl;

    /**
     * 视频分类ID
     */
    private String cateId;

    /**
     * 视频标签，单个标签不超过32字节，最多不超过16个标签。多个用逗号分隔，UTF8编码
     */
    private String tags;
}
