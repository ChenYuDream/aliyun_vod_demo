package org.jypj.dev.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import org.jypj.dev.utils.LiveUtil;
import org.jypj.dev.utils.VideoPlay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器
 *
 * @author yu_chen
 * @create 2017-12-20 10:49
 **/
@Controller
public class IndexController {


    /**
     * 查询出推流列表
     *
     * @param model
     * @return
     */
    @GetMapping("live/list")
    public String liveList(Model model) {
        LiveUtil liveUtil = new LiveUtil();
        liveUtil.init();
        String s = liveUtil.requestInitSample();
        model.addAttribute("result", JSON.parseObject(s));
        return "liveList";
    }

    @GetMapping("/index")
    public String toIndex() {
        VideoPlay.getVideoPlayAuth("");
        return "index";
    }

    @GetMapping("/live")
    public String toLive() {
        return "live";
    }

    /**
     * 查看直播
     * @return
     */
    @GetMapping("show/live")
    public String showLive() {


        return "live";
    }

}
