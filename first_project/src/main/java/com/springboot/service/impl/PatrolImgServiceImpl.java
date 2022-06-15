package com.springboot.service.impl;

import com.springboot.mapper.patrolImagesMapper;
import com.springboot.model.patrolImages;
import com.springboot.service.PatrolImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//可以将服务定义成一个事务！！！这样整个函数就有原子性了，否则普通事务会成功一半，失败一半
@Service//实现接口并定义为服务
public class PatrolImgServiceImpl implements PatrolImgService {
    @Autowired
    private patrolImagesMapper patrolImageMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){ return patrolImageMapper.deleteByPrimaryKey(id); };

    @Override
    public List<patrolImages> findAllImags(Integer parentId){ return patrolImageMapper.findAllImages(parentId);};

    @Override
    public void save(patrolImages record){
        patrolImageMapper.insert(record);
    }
}
