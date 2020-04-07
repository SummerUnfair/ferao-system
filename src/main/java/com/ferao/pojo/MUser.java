package com.ferao.pojo;/*
 * @author Ferao
 * @date
 * @discription
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("MUser实体类")
public class MUser implements Serializable {
    @ApiModelProperty("用户编号")
    private int id ;
    @ApiModelProperty("用户名称")
    private String username;
}
