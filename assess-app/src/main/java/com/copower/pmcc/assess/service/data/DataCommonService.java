package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kings on 2018-8-1.
 */
@Service
public class DataCommonService {
    @Autowired
    private BaseDataDicService baseDataDicService;
    /**
     * 获取集合中对应名称
     * @param dataDicList
     * @param idString
     * @return
     */
    public String getDataDicName(List<BaseDataDic> dataDicList,String idString){
        String result = new String();
        if (StringUtils.isNotBlank(idString)) {
            String s = StringUtils.replacePattern(idString, "^,+|,+$", "");
            List<Integer> integerList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(s));
            for (Integer integer : integerList) {
                for (BaseDataDic baseDataDic : dataDicList) {
                    if (integer.equals(baseDataDic.getId()))
                        result += baseDataDic.getName() + ",";
                }
            }
        }
        return result.replaceAll(",$","");
    }



    public String getBaseDataDicName(Integer id){
        BaseDataDic baseDataDic = null;
        if (id != null){
            baseDataDic = baseDataDicService.getDataDicById(id);
            if (baseDataDic != null){
                return baseDataDic.getName();
            }
        }
        return null;
    }
}
