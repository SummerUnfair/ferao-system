package com.ferao.pojo;/*
 * @author Ferao
 * @date
 * @discription
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("user")
@ApiModel("User实体类")
public class User implements Serializable ,Comparable<User> {

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("年龄")
    private int age;
//    @ApiModelProperty("日期")
//    private Date date;

    //重写排序的规则
    @Override
    public int compareTo(User o) {
        //return 0;  //认为元素都是相同的
        //自定义比较的规则，比较两个人的年龄（this,参数User）
        //return o.getId() - this.getId();   //年龄降序
        return this.getAge() - o.getAge();     //年龄升序
    }
}
