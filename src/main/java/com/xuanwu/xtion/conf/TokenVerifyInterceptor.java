package com.xuanwu.xtion.conf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xuanwu.xtion.domain.JsonResp;
import com.xuanwu.xtion.domain.ResultCode;
import com.xuanwu.xtion.utils.JsonUtil;
import com.xuanwu.xtion.utils.TokenVerifyUtil;
/**
 * Token验证拦截
 * @author Administrator
 *
 */
public class TokenVerifyInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenVerifyInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）" + arg2.getClass());
        String requestBodyStr  = getBodyString(arg0.getReader());
		Map<String, Object> map = JsonUtil.getObject(requestBodyStr, HashMap.class);
        String token = (String)map.get("token");
        JsonResp jsonResp = TokenVerifyUtil.tokenVerification(token);
        ResultCode r = JsonUtil.getObject(jsonResp.getResultMsg(), ResultCode.class);
		if(!"200".equals(r.getResultCode())){
			logger.debug(">>>"+jsonResp.toString());
			 doReturn(arg1,400);
			return false;
		}
		logger.debug(">>>>"+jsonResp.toString());
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
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
    
    /**
     * 返回HttpRequest中的body属性值String
     * @param br
     * @return
     */
    public static String getBodyString(BufferedReader br) {
    	  String inputLine;
    	      String str = "";
    	    try {
    	      while ((inputLine = br.readLine()) != null) {
    	       str += inputLine;
    	      }
    	      br.reset();
    	      // br.close();
    	    } catch (IOException e) {
    	      System.out.println("IOException: " + e);
    	    }
    	    return str;
    	 }
    
    /**
     * Java通过反射获取属性值
     * @param model
     */
    public static void getField(Object model){
    	java.lang.reflect.Field[] fields = model.getClass().getDeclaredFields();
    	for(java.lang.reflect.Field f : fields){
    		System.out.println(f.getName());
    	}
    }

}