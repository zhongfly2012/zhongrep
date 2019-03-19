package com.jolly.dao.${instant_name};

import com.jolly.common.base.dao.BaseDao;
import java.util.List;
import java.util.Map;
/**
* Created by ${author}
* on ${date?string("yyyy-MM-dd HH:mm:ss")}.
*/
public interface ${model_name}Dao{

        int countBySelective(${model_name} ${instant_name});

        ${model_name} selectOneBySelective(${model_name} ${instant_name});

        List<${model_name}> selectBySelective(${model_name} ${instant_name});

        Map<Integer,${model_name}> selectMapBySelective(${model_name} ${instant_name});

        int insertSelective(${model_name} ${instant_name});

        int updateSelective(${model_name} ${instant_name});

        int batchUpdate(List<Integer> idsList);

        int deleteByIds(List<Integer> idsList);

}
