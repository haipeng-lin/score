package com.github.score.api.model.enums.site;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/25 9:28
 * @Description 站点统计类型枚举
 */

@AllArgsConstructor
@Getter
public enum SiteVisitStatisticsEnum {
    PV(1, "浏览量"),
    UV(2, "独立访客"),
    VV(3, "访问次数"),
    ;

    private int type;
    private String desc;
}