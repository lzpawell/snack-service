package awell.xin.service_provider;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ssx on 2018/1/18.
 */
@FeignClient(name = "snack-user-service")
public interface UserService {
    @RequestMapping(value = "/getUserInfo/{userId}",method = RequestMethod.GET)
    String getUserInfo(@PathVariable("userId") String userId);
}
