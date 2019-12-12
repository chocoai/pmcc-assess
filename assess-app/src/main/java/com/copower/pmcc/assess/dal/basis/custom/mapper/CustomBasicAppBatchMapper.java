package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kings on 2018-11-21.
 */
public interface CustomBasicAppBatchMapper {

    /**
     * 获取案例最新版本楼盘信息
     *
     * @param estateName

     * @return
     */
    public List<BasicApplyBatch> getCustomDraftList(@Param("estateName") String estateName,
                                                    @Param("creator") String creator);


}
