package com.springboot.service.impl;

import com.springboot.mapper.healthExaminationsMapper;
import com.springboot.model.healthExaminations;
import com.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//可以将服务定义成一个事务！！！这样整个函数就有原子性了，否则普通事务会成功一半，失败一半
@Service//实现接口并定义为服务
public class TestServiceImpl implements TestService {
    @Autowired
    private healthExaminationsMapper testMapper;

    @Override
    public int insert(healthExaminations record){
        return testMapper.insert(record);
    };

    @Override
    public List<healthExaminations> findAllByparentId(Integer parentId){ return testMapper.findAllByparentId(parentId); };

    @Override
    public int deleteByPrimaryKey(Integer id){ return testMapper.deleteByPrimaryKey(id); };

    @Override
    public int updateByPrimaryKeySelective(healthExaminations record){ return testMapper.updateByPrimaryKeySelective(record); };

    @Override
    public int findNewestId(Integer wwid){
        Integer r = testMapper.findNewestId(wwid);
        if(r != null){
            return r;
        }else{
            return 0;
        }
    };

    @Override
    public healthExaminations selectByPrimaryKey(Integer id){
      return testMapper.selectByPrimaryKey(id);
    };

    @Override
    public List<healthExaminations> findLike(String date,Integer parentId){ return testMapper.findLike("%" + date + "%",parentId) ;};
}
