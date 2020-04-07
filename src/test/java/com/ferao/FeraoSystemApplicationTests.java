package com.ferao;

import com.ferao.mapper.MUserMapper;
import com.ferao.pojo.MUser;
import com.ferao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class FeraoSystemApplicationTests {

    @Resource
    private MUserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void test(){
        userMapper.findAll();

    }

}
