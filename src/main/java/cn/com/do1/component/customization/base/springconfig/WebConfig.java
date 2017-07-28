package cn.com.do1.component.customization.base.springconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 主配置文件
 * @author zhibinliu
 */
@Configuration
@Import(value = {SpringMvcConfig.class})
public class WebConfig {
}
