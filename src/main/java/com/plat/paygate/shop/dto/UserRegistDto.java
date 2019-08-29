package com.plat.paygate.shop.dto;

import com.plat.paygate.shop.common.utils.RegexUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @author Sunny
 * @version 1.0
 * @date 2019-08-28 17:02
 * @package com.plat.paygate.shop.dto
 */
@Data
public class UserRegistDto {
    /**用户名*/
    @NotBlank
    @Pattern(regexp = RegexUtils.MATCH_PTONE, message = "手机号码不正确")
    private String userName;
    /**密码*/
    @NotBlank
    @Length(min = 6, max = 12, message = "密码长度为6到12位")
    private String password;
    /**确认密码*/
    @NotBlank
    private String confirmPassword;
    /**用户角色*/
    @NotBlank
    @Pattern(regexp = RegexUtils.MATCH_ROLE, message = "不存在此用户角色")
    private Integer role;
}
