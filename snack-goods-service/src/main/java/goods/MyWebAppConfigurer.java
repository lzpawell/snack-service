package goods;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Value("${app.external.goods-res-pos}")
    private String goodsResPos;

    @Value("${app.external.goods-kinds-res-pos}")
    private String goodsKindsResPos;



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //映射外部目录，要带前缀file:
        registry.addResourceHandler("/goods-res/**").addResourceLocations(goodsResPos);
        registry.addResourceHandler("/goods-kinds-res/**").addResourceLocations(goodsKindsResPos);

        //可以这样自定义springboot下resources下的资源映射
        //registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");

        super.addResourceHandlers(registry);
    }

}
