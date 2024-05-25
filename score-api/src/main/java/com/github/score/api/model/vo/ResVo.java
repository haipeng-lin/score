package com.github.score.api.model.vo;

import com.github.score.api.model.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/24 22:38
 * @Description 响应视图对象
 */

@Data
public class ResVo<T> implements Serializable {
    // 序列化版本号
    private static final long serialVersionUID = -510306209659393854L;
    // 返回结果说明
    @ApiModelProperty(value = "返回结果说明", required = true)
    private Status status;

    // 返回的实体结果
    @ApiModelProperty(value = "返回的实体结果", required = true)
    private T result;


    // 构造方法不写参数默认构造方法
    public ResVo() {
    }

    // 只写status参数的构造方法
    public ResVo(Status status) {
        this.status = status;
    }

    // result参数的构造方法
    public ResVo(T t) {
        status = Status.newStatus(StatusEnum.SUCCESS);
        this.result = t;
    }

    // 成功的构造方法，传入参数t
    public static <T> ResVo<T> ok(T t) {
        return new ResVo<T>(t);
    }

    // 失败的构造方法，传入参数status和args
    @SuppressWarnings("unchecked")
    public static <T> ResVo<T> fail(StatusEnum status, Object... args) {
        return new ResVo<>(Status.newStatus(status, args));
    }

    // 失败的构造方法，传入参数status
    public static <T> ResVo<T> fail(Status status) {
        return new ResVo<>(status);
    }
}

