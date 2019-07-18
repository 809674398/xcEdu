package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.manage_cms.dao.SysDicthinaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDicthinaryService {

    @Autowired
    SysDicthinaryRepository sysDicthinaryRepository;

    /**
     * 根据类型查询字典
     */
    public SysDictionary findByDType(String type){
        return sysDicthinaryRepository.findByDType(type);
    }
}
