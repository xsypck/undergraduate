package com.springboot.service.impl;

import com.springboot.model.antiques;
import com.springboot.service.WwService;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.mapper.antiquesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//可以将服务定义成一个事务！！！这样整个函数就有原子性了，否则普通事务会成功一半，失败一半
@Service//实现接口并定义为服务
public class WwServiceImpl implements WwService {
    @Autowired
    private antiquesMapper antiqueMapper;

    @Override
    public List<antiques> selectAll(){ return antiqueMapper.selectAll(); };
    @Override
    public int insert(antiques record){
        return antiqueMapper.insert(record);
    };
    @Override
    public antiques selectByNum(String serialnum){ return antiqueMapper.selectByNum(serialnum); };
    @Override
    public antiques selectByPrimaryKey(Integer id){ return antiqueMapper.selectByPrimaryKey(id);};
    @Override
    public int updateByPrimaryKey(antiques antique){ return antiqueMapper.updateByPrimaryKey(antique); };
    @Override
    public int deleteByPrimaryKey(Integer id){ return antiqueMapper.deleteByPrimaryKey(id); };
    @Override
    public List<antiques> selectAllLike(String wwname){ return antiqueMapper.selectAllLike("%" + wwname + "%"); }
    @Override
    public List<antiques> findLike(String wwname, String wwlocation1, String wwlocation2, String wwlocation3, String wwyear, String protectionlevel, String wwcategory) {
        return antiqueMapper.findLike("%" + wwname + "%","%" + wwlocation1+"%" ,"%" + wwlocation2+"%", "%" + wwlocation3+"%", "%" +wwyear+"%","%" +protectionlevel+"%", "%" + wwcategory+"%");
    }
}
