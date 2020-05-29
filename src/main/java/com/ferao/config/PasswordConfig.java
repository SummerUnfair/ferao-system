package com.ferao.config;/*
 * @author Ferao
 * @date
 * @discription
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix="pwdconfg")
public class PasswordConfig {

    private String druid_pwd;
    private String swagger_pwd;
    private String xxJob_pwd;

}
