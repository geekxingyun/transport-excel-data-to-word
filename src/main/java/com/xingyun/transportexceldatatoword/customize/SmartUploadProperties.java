package com.xingyun.transportexceldatatoword.customize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 星云
 * @功能
 * @date 10/13/2019 12:01 PM
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix="com.xingyun.customize")
public class SmartUploadProperties {
    /**
     * 注意"这里的变量名称不可以有下划线 否则会出错
     */
    private String uploadFolder;
}
