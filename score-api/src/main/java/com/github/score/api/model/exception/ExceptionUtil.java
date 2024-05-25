package com.github.score.api.model.exception;

import com.github.score.api.model.enums.StatusEnum;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/24 21:51
 * @Description 异常工具类，用于生成自定义异常对象
 */

public class ExceptionUtil {

    /**
     * 创建一个ForumException实例，并设置状态码和参数
     * @param status 状态码
     * @param args 参数
     * @return ForumException实例
     */
    public static ScoreException of(StatusEnum status, Object... args) {
        return new ScoreException(status, args);
    }

}