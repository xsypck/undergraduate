package com.springboot.service.impl;

import com.springboot.mapper.fourProfilesMapper;
import com.springboot.model.fourProfiles;
import com.springboot.service.FourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//可以将服务定义成一个事务！！！这样整个函数就有原子性了，否则普通事务会成功一半，失败一半
@Service//实现接口并定义为服务
public class FourServiceImpl implements FourService {
    @Autowired
    private fourProfilesMapper fourProfileMapper;

    @Override
    public void save(fourProfiles fourprofile){
        fourProfileMapper.insert(fourprofile);
    }

    @Override
    public List<fourProfiles> selectAll(){ return fourProfileMapper.selectAll();};

    @Override
    public void deleteByPrimaryKey(Integer id){ fourProfileMapper.deleteByPrimaryKey(id);};

    @Override
    public List<fourProfiles> findModel(String model){ return fourProfileMapper.findModel(model); };

    @Override
    public int getFour(Integer wwid){
        Integer r = fourProfileMapper.getFour(wwid);
        if(r == null){
            return 0;
        }else {
            return r;
        }
    };

    @Override
    public List<fourProfiles> findAllByModel(Integer parentId, String s){
      return fourProfileMapper.findAllByModel(parentId,s);
    };
}
