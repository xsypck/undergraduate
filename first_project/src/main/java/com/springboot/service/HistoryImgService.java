package com.springboot.service;

import com.springboot.model.historyImages;

import java.util.List;

public interface HistoryImgService{
    List<historyImages> findAllByparentId(Integer parentId);

    int deleteByPrimaryKey(Integer id);

    void save(historyImages record);
}
