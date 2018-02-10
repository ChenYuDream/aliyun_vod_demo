package org.jypj.dev.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * 视频搜索条件
 * 参数都为非必填
 *
 * @author yu_chen
 * @create 2017-12-19 17:29
 **/
@Data
@Builder
public class VideoSearchCondition {

    /**
     * 视频状态，默认获取所有视频，多个可以用逗号分隔，如：Uploading,Normal，取值包括：
     * Uploading(上传中)，
     * UploadSucc(上传完成)，
     * Transcoding(转码中)，
     * TranscodeFail(转码失败)，
     * Checking(审核中),
     * Blocked(屏蔽)，
     * Normal(正常)
     */
    private String status;

    /**
     * CreationTime（创建时间）的开始时间，为开区间(大于开始时间)。
     * 日期格式按照ISO8601标准表示，并需要使用UTC时间。
     * 格式为：YYYY-MM-DDThh:mm:ssZ 例如，2017-01-11T12:00:00Z（为北京时间2017年1月11日20点0分0秒）
     */
    private String startTime;

    /**
     * CreationTime的结束时间，为闭区间(小于等于结束时间)。
     * 日期格式按照ISO8601标准表示，并需要使用UTC时间。
     * 格式为：YYYY-MM-DDThh:mm:ssZ 例如，2017-01-11T12:00:00Z（为北京时间2017年1月11日20点0分0秒）
     */
    private String endTime;

    /**
     * 视频分类ID
     */
    private Long cateId;

    /**
     * 页号，默认1
     */
    private Long pageNo;

    /**
     * 可选，默认10，最大不超过100
     */
    private Long pageSize;
    /**
     * 结果排序，范围：CreationTime:Desc、CreationTime:Asc，默认为CreationTime:Desc（即按创建时间倒序）
     */
    private String sortBy;
}
