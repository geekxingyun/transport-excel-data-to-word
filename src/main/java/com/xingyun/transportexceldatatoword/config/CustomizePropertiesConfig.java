package com.xingyun.transportexceldatatoword.config;

import com.xingyun.transportexceldatatoword.customize.SmartUploadProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 星云
 * @功能
 * @date 10/13/2019 12:00 PM
 */
@EnableConfigurationProperties({
        SmartUploadProperties.class
})
@Configuration
public class CustomizePropertiesConfig {
}
