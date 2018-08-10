package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.constant.AssessMarketCostConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostBuildingDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostConstructionDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: zch
 * @Date: 2018/8/9 10:43
 * @Description:成本法
 */
@Service
public class MdMarketCostService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MdCostBuildingDao mdCostBuildingDao;
    @Autowired
    private MdCostConstructionDao mdCostConstructionDao;
    @Autowired
    private MdCostDao mdCostDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    public BootstrapTableVo getBaseDicTree() {
        List<ZtreeDto> ztreeDtos = Lists.newArrayList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessMarketCostConstant.BUILD_SECURITY_ENGINEERING_PROJECT);
        ZtreeDto ztreeDto = new ZtreeDto();
        BeanUtils.copyProperties(baseDataDic,ztreeDto);
        ztreeDto.setNumber("0");
        ztreeDtos.add(ztreeDto);
        int i = 0;
        changeZtreeDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.BUILD_SECURITY_ENGINEERING_PROJECT), ztreeDtos,AssessMarketCostConstant.BUILD_SECURITY_ENGINEERING_PROJECT,i);

        changeZtreeDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.SOIL_ENGINEERING_PROJECT), ztreeDtos,AssessMarketCostConstant.SOIL_ENGINEERING_PROJECT,++i);
        changeZtreeDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.ERECT_ENGINEERING_PROJECT), ztreeDtos,AssessMarketCostConstant.ERECT_ENGINEERING_PROJECT,++i);
        changeZtreeDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.DECORATE_ENGINEERING_PROJECT), ztreeDtos,AssessMarketCostConstant.DECORATE_ENGINEERING_PROJECT,++i);
        changeZtreeDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.SUBSIDIARY_ENGINEERING_PROJECT), ztreeDtos,AssessMarketCostConstant.SUBSIDIARY_ENGINEERING_PROJECT,++i);
        changeZtreeDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.TWOLOADING_ENGINEERING_PROJECT), ztreeDtos,AssessMarketCostConstant.TWOLOADING_ENGINEERING_PROJECT,++i);
        BootstrapTableVo vo = new BootstrapTableVo();
        vo.setTotal(Integer.toUnsignedLong(ztreeDtos.size()));
        vo.setRows(ztreeDtos);
        return vo;
    }

    private void changeZtreeDto(List<BaseDataDic> baseDataDics, List<ZtreeDto> ztreeDtos,String key,int i) {
        Random random = new Random(System.currentTimeMillis());
        int v = 1;
        if (!ObjectUtils.isEmpty(baseDataDics)) {
            for (BaseDataDic baseDataDic : baseDataDics) {
                ZtreeDto ztreeDto = new ZtreeDto();
                BeanUtils.copyProperties(baseDataDic, ztreeDto);
                BaseDataDic dic = baseDataDicService.getCacheDataDicByFieldName(key);
                ztreeDto.set_parentId(dic.getId());
                if (i!=0){
                    ztreeDto.setNumber(String.format("%d-%d",i,v++));
                }else {
                    ztreeDto.setNumber(String.valueOf(v++));
                }
                ztreeDto.setArea(random.nextInt(100));
                ztreeDtos.add(ztreeDto);
            }
        }
    }
}
