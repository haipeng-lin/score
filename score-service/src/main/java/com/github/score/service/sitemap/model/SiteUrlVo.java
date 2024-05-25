package com.github.score.service.sitemap.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/25 10:02
 * @Description 站点路径视图对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "url")
public class SiteUrlVo {

    /**
     * loc：网站url
     * @JacksonXmlProperty：将Java类的属性映射到XML文档的标签
     */
    @JacksonXmlProperty(localName = "loc")
    private String loc;

    @JacksonXmlProperty(localName = "lastmod")
    private String lastMod;

}