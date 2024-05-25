package com.github.score.service.sitemap.constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/25 9:51
 * @Description 站点地图常量
 */

public class SiteMapConstants {
    public static final String SITE_VISIT_KEY = "visit_info";

    public static String day(LocalDate day) {

        return DateTimeFormatter.ofPattern("yyyyMMdd").format(day);
    }
}

