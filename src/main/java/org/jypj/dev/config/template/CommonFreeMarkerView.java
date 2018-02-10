package org.jypj.dev.config.template;

import freemarker.template.SimpleHash;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 公共freeMaker解析器
 *
 * @author ChenYu
 */
public class CommonFreeMarkerView extends FreeMarkerView {

    @Override
    protected SimpleHash buildTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        SimpleHash fmModel = super.buildTemplateModel(model, request, response);
        //设置ctx文档路径
        fmModel.put("ctx", request.getContextPath());
        return fmModel;
    }

}
