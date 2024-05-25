package com.github.score.web.global;

import cn.hutool.extra.spring.SpringUtil;
import com.github.hui.quick.plugin.qrcode.util.json.JsonUtil;
import com.github.score.api.model.context.ReqInfoContext;
import com.github.score.api.model.enums.StatusEnum;
import com.github.score.api.model.exception.ScoreException;
import com.github.score.api.model.vo.ResVo;
import com.github.score.api.model.vo.Status;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/24 21:21
 * @Description 全局异常处理器
 */

public class ScoreExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        Status errStatus = buildToastMsg(ex);

        if (restResponse(request, response)) {
            // 表示返回json数据格式的异常提示信息
            if (response.isCommitted()) {
                // 如果返回已经提交过，直接退出即可
                return new ModelAndView();
            }

            try {
                response.reset();
                // 若是rest接口请求异常时，返回json格式的异常数据；而不是专门的500页面
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.setHeader("Cache-Control", "no-cache, must-revalidate");
                response.getWriter().println(JsonUtil.toStr(ResVo.fail(errStatus)));
                response.getWriter().flush();
                response.getWriter().close();
                return new ModelAndView();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        String view = getErrorPage(errStatus, response);
        ModelAndView mv = new ModelAndView(view);
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        mv.getModel().put("global", SpringUtil.getBean(GlobalInitService.class).globalAttr());
        mv.getModel().put("res", ResVo.fail(errStatus));
        mv.getModel().put("toast", JsonUtil.toStr(ResVo.fail(errStatus)));
        return mv;
    }

    /**
     * 根据异常类型获得异常状态对象（含状态码、信息）
     *
     * @param ex：异常
     * @return 异常状态对象
     */
    private Status buildToastMsg(Exception ex) {
        if (ex instanceof ScoreException) {

            return ((ScoreException) ex).getStatus();
        } else if (ex instanceof AsyncRequestTimeoutException) {
            return Status.newStatus(StatusEnum.UNEXPECT_ERROR, "超时未登录");
        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            return Status.newStatus(StatusEnum.RECORDS_NOT_EXISTS, ExceptionUtils.getStackTrace(ex));
        } else if (ex instanceof HttpRequestMethodNotSupportedException || ex instanceof MethodArgumentTypeMismatchException || ex instanceof IOException) {
            // 请求方法不匹配
            return Status.newStatus(StatusEnum.ILLEGAL_ARGUMENTS, ExceptionUtils.getStackTrace(ex));
        } else if (ex instanceof NestedRuntimeException) {
            log.error("unexpect NestedRuntimeException error! {}", ReqInfoContext.getReqInfo(), ex);
            return Status.newStatus(StatusEnum.UNEXPECT_ERROR, ex.getMessage());
        } else {
            log.error("unexpect error! {}", ReqInfoContext.getReqInfo(), ex);
            return Status.newStatus(StatusEnum.UNEXPECT_ERROR, ExceptionUtils.getStackTrace(ex));
        }
    }

    /**
     * 根据异常状态对象解析需要返回的错误页面
     *
     * @param status
     * @param response
     * @return
     */
    private String getErrorPage(Status status, HttpServletResponse response) {
        //
        if (StatusEnum.is5xx(status.getCode())) {
            response.setStatus(500);
            return "error/500";
        } else if (StatusEnum.is403(status.getCode())) {
            response.setStatus(403);
            return "error/403";
        } else {
            response.setStatus(404);
            return "error/404";
        }
    }

    /**
     * 后台请求、api数据请求、上传图片等接口，返回json格式的异常提示信息
     * 其他异常，返回500的页面
     *
     * @param request
     * @param response
     * @return
     */
    private boolean restResponse(HttpServletRequest request, HttpServletResponse response) {
        if (request.getRequestURI().startsWith("/api/admin/") || request.getRequestURI().startsWith("/admin/")) {
            return true;
        }

        if (request.getRequestURI().startsWith("/image/upload")) {
            return true;
        }

        if (response.getContentType() != null && response.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            return true;
        }

        if (isAjaxRequest(request)) {
            return true;
        }

        // 数据接口请求
        AntPathMatcher pathMatcher = new AntPathMatcher();
        if (pathMatcher.match("/**/api/**", request.getRequestURI())) {
            return true;
        }
        return false;
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestedWith);
    }
}
