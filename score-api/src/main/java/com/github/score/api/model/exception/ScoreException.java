package com.github.score.api.model.exception;

import com.github.score.api.model.enums.StatusEnum;
import com.github.score.api.model.vo.Status;
import lombok.Getter;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/24 17:52
 * @Description 积分权益兑换系统异常——自定义异常
 */

public class ScoreException extends Exception{

    /**
     * 状态
     */
    @Getter
    private Status status;

    public ScoreException(Status status) {
        this.status = status;
    }

    //构造方法一
    public ScoreException(int code, String msg) {
        //调用Status类的newStatus方法，创建一个新的Status对象
        this.status = Status.newStatus(code, msg);
    }

    //构造方法二
    public ScoreException(StatusEnum statusEnum, Object... args) {
        //调用Status类的newStatus方法，创建一个新的Status对象
        this.status = Status.newStatus(statusEnum, args);
    }
}
