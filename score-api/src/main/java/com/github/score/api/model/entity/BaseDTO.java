package com.github.score.api.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/24 17:49
 * @Description 基础数据传输对象
 */

@Data
public class BaseDTO {
    @ApiModelProperty(value = "业务主键")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后编辑时间")
    private Date updateTime;
}