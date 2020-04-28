package com.ferao.service;/*
 * @author Ferao
 * @date
 * @discription
 */

import com.alibaba.fastjson.JSON;
import com.ferao.mapper.MUserMapper;
import com.ferao.pojo.AddressTerm;
import com.ferao.pojo.MUser;
import com.ferao.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Resource
    private MUserMapper mUserMapper;
    @Value("${spring.date.elasticsearch.properties.index_name}")
    private String esIndexName;
    @Value("${spring.date.elasticsearch.hanlp_synonym}")
    public String HANLP_SYNONYM;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    Map<Object,Object> redisMap = null;

    @PostConstruct
    public void initRedis(){
        HashSet<Object> hashSet =new HashSet<>();
        try {
            redisMap = redisTemplate.opsForHash().entries("myhash");
            System.out.println("reids success ..");
        }catch (Exception e){
            System.out.println("redis false ..");
            e.printStackTrace();
        }
        for (Object key : redisMap.keySet()) {
            hashSet.add(redisMap.get(key));
            System.out.println(key+":"+redisMap.get(key));
        }
        System.out.println(hashSet);
    }
    /**
     * elasticsearch分词
     * @param addr_clean
     */
    public List<AddressTerm> addrAnalyze(String addr_clean){

        AnalyzeRequestBuilder analyze = new AnalyzeRequestBuilder(elasticsearchTemplate.getClient(), AnalyzeAction.INSTANCE, esIndexName, addr_clean);

        analyze.setAnalyzer(HANLP_SYNONYM);

        List<AnalyzeResponse.AnalyzeToken> tokens = analyze.execute().actionGet().getTokens();

        List<AddressTerm> addressTerms = new ArrayList<>(tokens.size());

        for (AnalyzeResponse.AnalyzeToken token : tokens)
        {
            addressTerms.add(new AddressTerm(token));
        }
        return addressTerms;
    }

    /**
     * MUser分页
     * @param pageNum
     * @param pageSize
     */
    public List<MUser> MUserPage(int pageNum,int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<MUser> mUsers = mUserMapper.findAll();
        PageInfo<MUser> page = new PageInfo<MUser>(mUsers);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
        for (MUser mUser : mUsers) {
            String jsonString = JSON.toJSONString(mUser);
            System.out.println(jsonString);
        }
        return mUsers;
    }

}
