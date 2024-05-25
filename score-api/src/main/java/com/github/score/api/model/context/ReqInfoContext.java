package com.github.score.api.model.context;

import com.github.score.api.model.vo.user.dto.BaseUserInfoDTO;
import lombok.Data;

import java.security.Principal;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/24 17:45
 * @Description 请求上下文，携带用户信息
 */


public class ReqInfoContext {

    private static ThreadLocal<ReqInfo> contexts = new InheritableThreadLocal<>();

    public static void addReqInfo(ReqInfo reqInfo) {
        contexts.set(reqInfo);
    }

    public static void clear() {
        contexts.remove();
    }

    public static ReqInfo getReqInfo() {
        return contexts.get();
    }

    /**
     * 域名、访问路径、客户端ip、referer、post
     */
    @Data
    public static class ReqInfo implements Principal {
        /**
         * appKey
         */
        private String appKey;
        /**
         * 访问的域名
         */
        private String host;
        /**
         * 访问路径
         */
        private String path;
        /**
         * 客户端ip
         */
        private String clientIp;
        /**
         * referer
         */
        private String referer;
        /**
         * post 表单参数
         */
        private String payload;
        /**
         * 设备信息
         */
        private String userAgent;

        /**
         * 登录的会话
         */
        private String session;

        /**
         * 用户id
         */
        private Long userId;

        /**
         * 用户信息
         */
        private BaseUserInfoDTO user;

        /**
         * 消息数量
         */
        private Integer msgNum;

        //
        //private Seo seo;

        private String deviceId;

        @Override
        public String getName() {
            return session;
        }
    }
}
