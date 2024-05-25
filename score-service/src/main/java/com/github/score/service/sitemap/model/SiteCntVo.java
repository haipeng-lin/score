package com.github.score.service.sitemap.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/25 10:05
 * @Description 站点统计视图对象
 */

@Data
public class SiteCntVo implements Serializable {
    private static final long serialVersionUID = 8747459624770066661L;
    /**
     * 日期
     */
    private String day;
    /**
     * 路径，全站时，path为null
     */
    private String path;
    /**
     * 站点page view 点击数
     */
    private Integer pv;
    /**
     * 站点unique view 点击数
     */
    private Integer uv;
}
