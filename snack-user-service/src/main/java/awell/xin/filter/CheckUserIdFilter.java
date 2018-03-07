package awell.xin.filter;

import awell.xin.entities.UserEntity;
import awell.xin.repositories.UserRepository;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@Order(1)
//@WebFilter(filterName = "checkUserIdFilter",urlPatterns = "/*")
public class CheckUserIdFilter implements Filter{
    @Autowired
    private UserRepository userRepository;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CheckUserIdFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request.getServerName());
        JSONObject result = new JSONObject();
        String userId = request.getParameter("userId");
        if (userId == null || userId.equals("")){
            result.put("exception","Wrong userId!");
            response.getWriter().write(result.toJSONString());
            return;
        }
        UserEntity user = userRepository.findOne(userId);
        if (user == null){
            result.put("exception","userId Not Found");
            response.getWriter().write(result.toJSONString());
            return;
        }
        request.setAttribute("user",user);
        doFilter(request,response,chain);
    }

    @Override
    public void destroy() {
        System.out.println("CheckUserIdFilter destroy");
    }
}
