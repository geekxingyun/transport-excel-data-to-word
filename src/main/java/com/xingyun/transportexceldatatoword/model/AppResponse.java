package com.xingyun.transportexceldatatoword.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author 星云
 * @功能
 * @date 10/13/2019 9:43 AM
 */
@Component
@Getter
@Setter
@ToString
public class AppResponse {
    private Integer responseCode;
    private String responseMessage;
    private Object responseData;
}
