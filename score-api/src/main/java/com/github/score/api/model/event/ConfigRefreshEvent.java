package com.github.score.api.model.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/24 17:51
 * @Description 配置变更消息事件
 */
@Getter
@Setter
 @ToString
 @EqualsAndHashCode(callSuper = true)
 public class ConfigRefreshEvent extends ApplicationEvent {
    private String key;
    private String val;


    public ConfigRefreshEvent(Object source, String key, String value) {
        super(source);
        // 调用父类的构造函数
        this.key = key;
        this.val = value;
    }
}
