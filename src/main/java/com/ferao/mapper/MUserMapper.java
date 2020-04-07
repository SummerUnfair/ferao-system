package com.ferao.mapper;/*
 * @author Ferao
 * @date
 * @discription
 */

import com.ferao.pojo.MUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MUserMapper {

    /**
     * 查找所有用户(分页)
     * @return
     */
    List<MUser> findAll();

    /**
     * 保存用户
     */
    void saveUser(MUser user);

    /**
     * 更新用户
     */
    void updateUser(MUser user);

    /**
     * 根据id删除用户
     */
    void deleteUser(Integer userId);
    /**
     * 根据id查询用户信息
     */
    MUser findById(Integer userId);
}
