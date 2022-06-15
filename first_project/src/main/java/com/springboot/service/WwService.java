package com.springboot.service;
import com.springboot.model.antiques;

import java.util.List;

public interface WwService {

    List<antiques> selectAll();

    int insert(antiques record);

    antiques selectByNum(String serialnum);

    antiques selectByPrimaryKey(Integer id);

    List<antiques> selectAllLike(String wwname);

    int updateByPrimaryKey(antiques antique);

    int deleteByPrimaryKey(Integer id);

    List<antiques> findLike(String wwname, String wwlocation1, String wwlocation2, String wwlocation3, String wwyear, String protectionlevel, String wwcategory);
}
