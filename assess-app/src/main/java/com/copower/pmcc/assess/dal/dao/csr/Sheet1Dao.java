package com.copower.pmcc.assess.dal.dao.csr;

import com.copower.pmcc.assess.dal.entity.Sheet1;
import com.copower.pmcc.assess.dal.entity.Sheet1Example;
import com.copower.pmcc.assess.dal.mapper.Sheet1Mapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/7
 * @time: 14:55
 */
@Repository
public class Sheet1Dao {
    @Autowired
    private Sheet1Mapper sheet1Mapper;

    public List<Sheet1> getSheet1List(String search) {
        Sheet1Example example = new Sheet1Example();
        if (StringUtils.isNotBlank(search)) {
            example.or().andPoJkrLike(search);
            example.or().andPoDyhLike(search);
        }
        return sheet1Mapper.selectByExample(example);
    }
}
