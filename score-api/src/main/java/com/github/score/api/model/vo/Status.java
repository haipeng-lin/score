package com.github.score.api.model.vo;

import com.github.score.api.model.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/24 18:08
 * @Description 业务状态
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    /**
     * 业务状态码
     */
    @ApiModelProperty(value = "状态码, 0表示成功返回，其他异常返回", required = true, example = "0")
    private int code;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "正确返回时为ok，异常时为描述文案", required = true, example = "ok")
    private String msg;

    /**
     * 创建Status对象的方法
     * @param code 状态码
     * @param msg 描述信息
     * @return Status对象
     */
    public static Status newStatus(int code, String msg) {
        return new Status(code, msg);
    }

    /**
     * 创建Status对象的方法
     * @param statusEnum 状态码枚举
     * @param msgs 描述信息
     * @return Status对象
     */
    public static Status newStatus(StatusEnum status, Object... msgs) {
        String msg;
        if (msgs.length > 0) {
            msg = String.format(status.getMsg(), msgs);
        } else {
            msg = status.getMsg();
        }
        return newStatus(status.getCode(), msg);
    }
}