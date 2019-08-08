package xyz.chenmt.www.chenmtrides.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @program chenmt-rides
 * @description:
 * @author: chenmet
 * @create: 2019/08/08 09:44
 */
@Slf4j
@Component
public class SystemLogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // return super.preHandle(request, response, handler);
        HttpSession session = request.getSession();
        session.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    /**
     * 重写postHandle方法，在请求完成之后执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {

            HandlerMethod myHandlerMethod = (HandlerMethod) handler;
            Object bean = myHandlerMethod.getBean();
            Method method = myHandlerMethod.getMethod();
            // Annotation classAnnotation =
            // bean.getClass().getAnnotation(SystemLog.class);//类上有该标记
            // Annotation
            // methodAnnotation=method.getAnnotation(SystemLog.class);//方法上有该标记
//            SystemLog annot = method.getAnnotation(SystemLog.class);
//            if (BeanUtil.isEmpty(annot)) {
//                return;
//            } else {
                HttpSession session = request.getSession();
//                User u = (User) session.getAttribute("user");
                String ipaddress = null;
//                String parameterData = JSON.toJSONString(request.getParameterMap());


            String uri = request.getRequestURI();

            StringBuilder stringBuilder = new StringBuilder();

            Map<String, String[]> parameterMap = request.getParameterMap();

            parameterMap.forEach((k, v) -> {
                        stringBuilder.append(k + "=");
                        for (String str : v) {
                            stringBuilder.append(str + ",");
                        }
                    }
            );

                if (request.getHeader("x-forwarded-for") == null) {
                    ipaddress = request.getRemoteAddr();
                } else {
                    ipaddress = request.getHeader("x-forwarded-for");
                }

                long startTime = (long) session.getAttribute("startTime");
                long endTime = System.currentTimeMillis();
//                try {
//                    CommitLog commitlog = new CommitLog();
//                    if (null != u) {
//                        commitlog.setName(u.getName());
//                    }
//                    commitlog.setCparameter(parameterData);
//                    commitlog.setCommit(annot.name());
//                    commitlog.setCmethod(bean.getClass().getSimpleName() + "." + method.getName());
//                    commitlog.setCreateTime(new Date(startTime));
//                    commitlog.setIpaddress(ipaddress);
//                    commitlog.setResponseTime(new Long(endTime - startTime) + "ms");
//                    commitLogService.save(commitlog);
//                } catch (Exception e) {
//                    logger.info("CommitInterceptor->save =============== 操作日志保存出错");
//                    e.printStackTrace();
//                }
                log.info("请求参数:{}",stringBuilder.toString());
                log.info("操作方法:{}",bean.getClass().getSimpleName() + "." + method.getName());
                log.info("创建时间:{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(startTime)));
                log.info("请求路径:{}",ipaddress);
                log.info("请求地址:{}",uri);
                log.info("请求时长:{}",new Long(endTime - startTime) + "ms");
                session.removeAttribute("startTime");
//            }
        }
    }
}
