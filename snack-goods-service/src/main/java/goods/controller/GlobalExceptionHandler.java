package goods.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//全局异常处理
//@ExceptionHandler的匹配原则： 从自己向Exception父类方向匹配， 匹配一个后，不再往上匹配
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public JSONObject allExceptionHandler(HttpServletRequest request,
                                          Exception exception) throws Exception  {
        JSONObject object = new JSONObject();
        object.put("exception", exception.getMessage());
        return object;

        /*forward 与 redirect的区别：
         * forward是controller内跳转，把一个请求从一个Controller转交给另一个Controller处理
         * redirect是直接respond给客户端告诉它需要重定向，然后从客户端重新发起链接到目标url
         */
    }


    @ExceptionHandler(value=RuntimeException.class)
    public String rtExceptionHandler(HttpServletRequest request,
                                     Exception exception) throws Exception  {
        System.out.println("runtime："+exception.getMessage());
        System.out.println("runtime："+exception.getClass());
        return "forward:/error";
    }
}
