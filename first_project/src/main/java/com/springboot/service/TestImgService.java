package com.springboot.service;

import com.springboot.model.healthExaminationImages;
import java.util.List;

public interface TestImgService {
    int deleteByPrimaryKey(Integer id);

    List<healthExaminationImages> findAllImags(Integer parentId);

    void save(healthExaminationImages testImage);
}
