package com.jolly.dao.${instant_name}.impl;

import com.jollychic.h5.server.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
/**
* Created by ${author}
* on ${date?string("yyyy-MM-dd HH:mm:ss")}.
*/

@Repository
public class ${model_name}DaoImpl extends BaseDaoImpl implements ${model_name}Dao {

        private final static String SPACE="mapper.${instant_name}Mapper.";


        @Override
        public int countBySelective(${model_name} ${instant_name}){
            return ${sqlSessionTemplateName}.selectOne(SPACE+"countBySelective",${instant_name});
        }

        @Override
        public ${model_name} selectOneBySelective(${model_name} ${instant_name}){
            return ${sqlSessionTemplateName}.selectOne(SPACE+"selectOneBySelective",${instant_name});
        }

        @Override
        public List<${model_name}> selectBySelective(${model_name} ${instant_name}){
            return ${sqlSessionTemplateName}.selectList(SPACE+"selectBySelective",${instant_name});
        }

        @Override
        public Map<Integer,${model_name}> selectMapBySelective(${model_name} ${instant_name}){
            return ${sqlSessionTemplateName}.selectMap(SPACE+"selectBySelective",${instant_name},"id");
        }

        @Override
        public int insertSelective(${model_name}  ${instant_name}) {
            return ${sqlSessionTemplateName}.insert(SPACE+"insertSelective",${instant_name});
        }

        @Override
        public int updateSelective(${model_name}  ${instant_name}) {
            return ${sqlSessionTemplateName}.update(SPACE+"updateSelective",${instant_name});
        }

        @Override
        public int batchUpdate(List<Integer> idsList) {
            return ${sqlSessionTemplateName}.update(SPACE+"batchUpdate",idsList);
        }

       @Override
       public int deleteByIds(List<Integer> idsList) {
            return ${sqlSessionTemplateName}.update(SPACE+"deleteByIds",idsList);
        }


}
