package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataCsrFieldRelation;
import com.copower.pmcc.assess.dal.basis.entity.DataCsrFieldRelationExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataCsrFieldRelationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * 债权独立模块 字段关联关系
 */
@Repository
public class DataCsrFieldRelationDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataCsrFieldRelationMapper mapper;

    public boolean add(DataCsrFieldRelation csrFieldRelation){
        return  mapper.insertSelective(csrFieldRelation)==1;
    }

    public DataCsrFieldRelation get(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public boolean update(DataCsrFieldRelation csrFieldRelation){
        if (ObjectUtils.isEmpty(csrFieldRelation)){
            try {
                throw new SQLException();
            }catch (Exception e){
                logger.error("error:"+e.getMessage());
            }
        }else {
            return mapper.updateByPrimaryKey(csrFieldRelation)==1;
        }
        return false;
    }

    public DataCsrFieldRelation getByAnotherName(String anotherName){
        DataCsrFieldRelationExample example = new DataCsrFieldRelationExample();
        example.createCriteria().andIdIsNotNull().andAnotherNameEqualTo(anotherName);
        List<DataCsrFieldRelation> csrFieldRelations = mapper.selectByExample(example);
        if (csrFieldRelations.size()<=0){
            return null;
        }
        return csrFieldRelations.get(0);
    }

    public List<DataCsrFieldRelation> dataCsrFieldRelationList(String displayName){
        DataCsrFieldRelationExample example = new DataCsrFieldRelationExample();
        if (StringUtils.isEmpty(displayName)){
            example.createCriteria().andIdIsNotNull();
            return mapper.selectByExample(example);
        }else {
            example.createCriteria().andIdIsNotNull().andDisplayNameLike("%"+displayName+"%");
            return mapper.selectByExample(example);
        }
    }

    public boolean remove(Integer id){
        try {
            if (!ObjectUtils.isEmpty(id)){
                return mapper.deleteByPrimaryKey(id)==1;
            }else {
                throw  new SQLException();
            }
        }catch (SQLException e){
            logger.error("error:"+e.getMessage() +" "+e.getSQLState());
        }
        return false;
    }
}
