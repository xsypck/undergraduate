package com.springboot.service.impl;

import com.springboot.mapper.healthExaminationImagesMapper;
import com.springboot.model.healthExaminationImages;
import com.springboot.service.TestImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//可以将服务定义成一个事务！！！这样整个函数就有原子性了，否则普通事务会成功一半，失败一半
@Service//实现接口并定义为服务
public class TestImgServiceImpl implements TestImgService {
    @Autowired
    private healthExaminationImagesMapper healthExaminationImageMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){ return healthExaminationImageMapper.deleteByPrimaryKey(id); };

    @Override
    public List<healthExaminationImages> findAllImags(Integer parentId){ return healthExaminationImageMapper.findAllImages(parentId);};

    @Override
    public void save(healthExaminationImages record){
        healthExaminationImageMapper.insert(record);
    }
}
