package org.jypj.dev.controller;

import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.jypj.dev.result.Result;
import org.jypj.dev.result.ResultUtil;
import org.jypj.dev.utils.OssUtil;
import org.jypj.dev.utils.VideoPlay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传页面
 *
 * @author yu_chen
 * @create 2017-12-20 10:49
 **/
@Controller
public class FileController {


    /**
     * 跳转到文件上传页面
     *
     * @return
     */
    @RequestMapping(value = "/toFileUpload", method = RequestMethod.GET)
    public String toFileUpload() {
        return "fileUpload";
    }


    /**
     * @param file 文件对象
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadImg(@RequestParam("file") MultipartFile file) {
        Map<String, Object> dataMap = new HashMap<>();
        String filePath = OssUtil.putObject(file);
        dataMap.put("src", filePath);
        //返回json
        return ResultUtil.success(dataMap);
    }
}
