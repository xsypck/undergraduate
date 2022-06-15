package com.springboot.service.impl;

import com.springboot.mapper.patrolsMapper;
import com.springboot.model.patrols;
import com.springboot.service.PatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//可以将服务定义成一个事务！！！这样整个函数就有原子性了，否则普通事务会成功一半，失败一半
@Service//实现接口并定义为服务
public class PatrolServiceImpl implements PatrolService {
    @Autowired
    private patrolsMapper patrolMapper;

    @Override
    public int insert(patrols record){
        return patrolMapper.insert(record);
    };

    @Override
    public List<patrols> findAllByparentId(Integer parentId){ return patrolMapper.findAllByparentId(parentId); };

    @Override
    public int deleteByPrimaryKey(Integer id){ return patrolMapper.deleteByPrimaryKey(id); };

    @Override
    public int updateByPrimaryKeySelective(patrols patrol){ return patrolMapper.updateByPrimaryKeySelective(patrol);};

    @Override
    public List<patrols> findLike(String s,Integer parentId){ return patrolMapper.findLike("%" + s + "%", parentId);};
}
