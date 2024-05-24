package com.github.score.api.model.exception;

import com.github.score.api.model.enums.StatusEnum;
import com.github.score.api.model.vo.Status;
import lombok.Getter;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/24 17:52
 * @Description 积分权益兑换系统异常
 */

public class ScoreException {
    @Getter
    private Status status;

    public ScoreException(Status status) {
        this.status = status;
    }

    public ScoreException(int code, String msg) {

        this.status = Status.newStatus(code, msg);
    }

    public ScoreException(StatusEnum statusEnum, Object... args) {
        this.status = Status.newStatus(statusEnum, args);
    }
}
