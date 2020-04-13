package com.queen.core.log.utils;


import com.queen.core.launch.props.QueenProperties;
import com.queen.core.launch.server.ServerInfo;
import com.queen.core.log.model.LogAbstract;
import com.queen.core.secure.utils.SecureUtil;
import com.queen.core.tool.utils.DateUtil;
import com.queen.core.tool.utils.StringPool;
import com.queen.core.tool.utils.UrlUtil;
import com.queen.core.tool.utils.WebUtil;


import javax.servlet.http.HttpServletRequest;

/**
 * Log 相关工具
 *
 * @author jensen
 */
public class LogAbstractUtil {

	/**
	 * 向log中添加补齐request的信息
	 *
	 * @param request     请求
	 * @param logAbstract 日志基础类
	 */
	public static void addRequestInfoToLog(HttpServletRequest request, LogAbstract logAbstract) {
		logAbstract.setRemoteIp(WebUtil.getIP(request));
		logAbstract.setUserAgent(request.getHeader(WebUtil.USER_AGENT_HEADER));
		logAbstract.setRequestUri(UrlUtil.getPath(request.getRequestURI()));
		logAbstract.setMethod(request.getMethod());
		logAbstract.setParams(WebUtil.getRequestParamString(request));
		logAbstract.setCreateBy(SecureUtil.getUserAccount(request));
	}

	/**
	 * 向log中添加补齐其他的信息
	 *
	 * @param logAbstract     日志基础类
	 * @param queenProperties 配置信息
	 * @param serverInfo      服务信息
	 */
	public static void addOtherInfoToLog(LogAbstract logAbstract, QueenProperties queenProperties, ServerInfo serverInfo) {
		logAbstract.setServiceId(queenProperties.getName());
		logAbstract.setServerHost(serverInfo.getHostName());
		logAbstract.setServerIp(serverInfo.getIpWithPort());
		logAbstract.setEnv(queenProperties.getEnv());
		logAbstract.setCreateTime(DateUtil.now());

		//这里判断一下params为null的情况，否则queen-log服务在解析该字段的时候，可能会报出NPE
		if (logAbstract.getParams() == null) {
			logAbstract.setParams(StringPool.EMPTY);
		}
	}
}
