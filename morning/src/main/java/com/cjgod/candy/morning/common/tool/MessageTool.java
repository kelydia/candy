package com.cjgod.candy.morning.common.tool;

/**
 * Created by lichunjiang on 2016/12/16.
 */
import com.cjgod.candy.morning.common.util.LoadPropertiesUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Map;

@Component
public class MessageTool {
    private static final String MSG_CONF_PATH = "conf/error-message.properties";

    private final Map<String, String> msgMap = LoadPropertiesUtil.load(MSG_CONF_PATH);


    public String getMessage(String msgId, String params[]) {

        if (StringUtils.isEmpty(msgId)) {
            return "";
        }

        String msgTxt = msgMap.get(msgId);


        try {
            msgTxt = MessageFormat.format(msgTxt, params);
        } catch (Exception e) {
            throw new RuntimeException("error message获取失败，messageId=" + msgId);
        }


        if (msgTxt == null)
            msgTxt = "";

        return msgTxt;
    }


    public String getMessage(String msgId, Map<String, ?> params) {
        if (StringUtils.isEmpty(msgId)) {
            return "";
        }

        String msgTxt = msgMap.get(msgId);

        if (!StringUtils.isEmpty(msgTxt) && params != null && !params.isEmpty()) {
            try {
                Template template = new Template(null, new StringReader(msgTxt), null);
                template.setNumberFormat("#");
                StringWriter writer = new StringWriter();

                template.process(params, writer);
                msgTxt = writer.toString();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("error message获取失败，messageId=" + msgId);
            } catch (TemplateException e) {
                throw new RuntimeException("error message获取失败，messageId=" + msgId);
            }
        }

        if (msgTxt == null)
            msgTxt = "";

        return msgTxt;
    }

    public String getMessage(String msgId) {
        if (StringUtils.isEmpty(msgId)) {
            return "";
        }

        String msgTxt = msgMap.get(msgId);

        if (msgTxt == null)
            msgTxt = "";

        return msgTxt;
    }
}
