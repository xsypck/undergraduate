package com.springboot.service.impl;

import com.springboot.mapper.historyImagesMapper;
import com.springboot.model.historyImages;
import com.springboot.service.HistoryImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//可以将服务定义成一个事务！！！这样整个函数就有原子性了，否则普通事务会成功一半，失败一半
@Service//实现接口并定义为服务
public class HistoryImgServiceImpl implements HistoryImgService{
    @Autowired
    private historyImagesMapper historyImageMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){ return historyImageMapper.deleteByPrimaryKey(id); };

    @Override
    public List<historyImages> findAllByparentId(Integer parentId){ return historyImageMapper.findAllByparentId(parentId);};

    @Override
    public void save(historyImages record){
        historyImageMapper.insert(record);
    }
}
