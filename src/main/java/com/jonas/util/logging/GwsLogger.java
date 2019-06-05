package com.jonas.util.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 【 日志工具 】
 *
 * @author shenjy 2019/04/30
 */
public final class GwsLogger {
    private static ConcurrentHashMap<LoggerEnum, Logger> logMaps = new ConcurrentHashMap<>();

    /**
     * 默认logger
     **/
    private static Logger GWS_LOGGER = LogManager.getLogger(LoggerEnum.GWS.getLogerName());

    /**
     * 除此logger, 基于日志需要添加上下文
     **/
    private static Logger ACCESS_LOGGER = LogManager.getLogger(LoggerEnum.ACCESSTRACE.getLogerName());

    static {
        logMaps.put(LoggerEnum.GWS, GWS_LOGGER);
        logMaps.put(LoggerEnum.ACCESSTRACE, ACCESS_LOGGER);
        logMaps.put(LoggerEnum.SQLTRACE, LogManager.getLogger(LoggerEnum.SQLTRACE.getLogerName()));
    }

    /**
     * 全局日志logger调用, 默认为GwsLogger
     *
     * @param loggerType
     * @return
     * @author liuyi 2016年4月19日
     */
    public static Logger getLogger(LoggerEnum loggerType) {
        Logger logger = logMaps.get(loggerType);
        return (null != logger) ? logger : GWS_LOGGER;
    }

    /**
     * 全局日志默认GWS枚举
     *
     * @return
     * @author liuyi 2016年4月19日
     */
    public static Logger getLogger() {
        return GWS_LOGGER;
    }

    /**
     * 输出已定义日志类别的info日志
     *
     * @param loggerType
     * @param msg
     * @param args
     * @author liuyi 2016年4月20日
     */
    public static void info(LoggerEnum loggerType, String msg, Object... args) {
        Logger logger = getLogger(loggerType);
        logger.info(msg, args);
    }

    /**
     * 输出默认的GWS类别info日志
     *
     * @param msg
     * @param args
     * @author liuyi 2016年4月20日
     */
    public static void info(String msg, Object... args) {
        Logger logger = getLogger();
        logger.info(msg, args);
    }


    /**
     * 输出默认的GWS类别debug日志
     *
     * @param msg
     * @param args
     * @author liuyi 2016年4月20日
     */
    public static void debug(String msg, Object... args) {
        Logger logger = getLogger();
        logger.debug(msg, args);
    }

    /**
     * 输出默认GWS日志类别的debug日志
     *
     * @param
     * @param msg
     * @param args
     * @author liuyi 2016年4月20日
     */
    public static void debug(LoggerEnum loggerType, String msg, Object... args) {
        Logger logger = getLogger(loggerType);
        logger.debug(msg);
    }

    /**
     * 输出默认GWS日志类别的错误日志
     *
     * @param msg
     * @param args
     * @author liuyi 2016年4月20日
     */
    public static void error(String msg, Object... args) {
        Logger logger = getLogger();
        logger.error(msg, args);
    }

    /**
     * 输出默认GWS日志类别的错误日志
     *
     * @param throwable
     * @param msg
     * @param args
     * @author liuyi 2016年4月20日
     */
    public static void error(Throwable throwable, String msg, Object... args) {
        Logger logger = getLogger();
        logger.error(msg, throwable);
    }

    /**
     * 格式化文本
     *
     * @param format
     * @param args
     * @return
     * @author liuyi 2016年4月20日
     */
    private static String format(String format, Object... args) {
        if (args != null && args.length > 0) {
            return String.format(format, args);
        } else {
            return format;
        }
    }
}
