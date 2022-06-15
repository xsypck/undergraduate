package com.springboot.service.impl;

import com.springboot.mapper.usersMapper;
import com.springboot.model.users;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//可以将服务定义成一个事务！！！这样整个函数就有原子性了，否则普通事务会成功一半，失败一半
@Service//实现接口并定义为服务
public class UserServiceImpl implements UserService {
    @Autowired
    private usersMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String username){
        return userMapper.deleteByPrimaryKey(username);
    };
    @Override
    public int insert(users record){
        return userMapper.insert(record);
    };
    @Override
    public int insertSelective(users record){
        return userMapper.insertSelective(record);
    };
    @Override
    public users selectByPrimaryKey(String username){
        return userMapper.selectByPrimaryKey(username);
    };
    @Override
    public List<users> selectAll(){
        return userMapper.selectAll();
    };
    @Override
    public int updateByPrimaryKeySelective(users record){
        return userMapper.updateByPrimaryKeySelective(record);
    };
    @Override
    public int updateByPrimaryKey(users record){
        return userMapper.updateByPrimaryKey(record);
    };
    @Override
    public List<users> selectAllLike(String username){ return userMapper.selectAllLike("%" + username + "%"); }
}
