package com.jolly.service.${instant_name}.impl;


import com.jolly.common.base.dao.BaseDao;
import com.jolly.common.base.service.impl.BaseServiceImpl;
import com.jolly.dao.${instant_name}.${model_name}Dao;
import com.jolly.service.${instant_name}.${model_name}Service;
import com.jolly.model.${instant_name}.${model_name};
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by ${author}
* on ${date?string("yyyy-MM-dd HH:mm:ss")}.
*/

@Service
public class ${model_name}ServiceImpl implements ${model_name}Service {

        final static Logger LOG = LogManager.getLogger(${model_name}ServiceImpl.class);

        @Autowired
        private ${model_name}Dao ${instant_name}Dao;


}
