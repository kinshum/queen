package com.queen.core.log.publisher;

import com.queen.core.log.model.LogUsual;
import com.queen.core.log.constant.EventConstant;
import com.queen.core.log.event.UsualLogEvent;
import com.queen.core.log.utils.LogAbstractUtil;
import com.queen.core.tool.utils.SpringUtil;
import com.queen.core.tool.utils.WebUtil;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * QUEEN日志信息事件发送
 *
 * @author jensen
 */
public class UsualLogPublisher {

	public static void publishEvent(String level, String id, String data) {
		HttpServletRequest request = WebUtil.getRequest();
		LogUsual logUsual = new LogUsual();
		logUsual.setLogLevel(level);
		logUsual.setLogId(id);
		logUsual.setLogData(data);

		LogAbstractUtil.addRequestInfoToLog(request, logUsual);
		Map<String, Object> event = new HashMap<>(16);
		event.put(EventConstant.EVENT_LOG, logUsual);
		event.put(EventConstant.EVENT_REQUEST, request);
		SpringUtil.publishEvent(new UsualLogEvent(event));
	}

}
