package com.xuanwu.xtion.conf;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xuanwu.xtion.domain.BaseEntity;
import com.xuanwu.xtion.domain.JsonResp;
import com.xuanwu.xtion.domain.ResultCode;
import com.xuanwu.xtion.utils.JsonUtil;
import com.xuanwu.xtion.utils.StringUtil;
import com.xuanwu.xtion.utils.TokenVerifyUtil;

@Aspect
@Component
public class TokenVerifyInterceptorAop {
	
	private Logger logger = LoggerFactory.getLogger(TokenVerifyInterceptorAop.class);

	@Pointcut("execution(* com.xuanwu.xtion.api.rest..*(..))")
	public void execute(){}
	
	@Around("execute()")
	public Object invoke(ProceedingJoinPoint joinPoint) {
		logger.debug(">>> aop is start.");
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletResponse response = sra.getResponse();
        
		Object[] args = joinPoint.getArgs();
		String token = null;
		for (Object object : args) {
			if (object instanceof BaseEntity) {
				BaseEntity base = (BaseEntity) object;
				token = base.getToken();
				break;
			}
		}
		if (StringUtil.isNotBlank(token)) {
			JsonResp jsonResp = TokenVerifyUtil.tokenVerification(token);
	        ResultCode r = JsonUtil.getObject(jsonResp.getResultMsg(), ResultCode.class);
			if(!"200".equals(r.getResultCode())){
				doReturn(response, 400);
				return null;
			}
		}
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			logger.error("invoke occur error! cause by {}", e.getMessage());
			doReturn(response, 400);
		}
		return null;
	}
	
	/**
     * 错误返回
     * @param response
     * @param code
     */
    private void doReturn(HttpServletResponse response, int code) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(code);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append("{\"error\":{\"errorcode\":\"400\",\"errormsg\":\"无效的token!\"},\"status\":\"0\"}");
            out.flush();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
