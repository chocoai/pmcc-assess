package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.scheme.MdDevelopmentVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/24 18:48
 * @Description:假设开发法
 */
@Service
public class MdDevelopmentService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private MdDevelopmentDao mdDevelopmentDao;
    @Autowired
    private MdArchitecturalObjService mdArchitecturalObjService;
    @Autowired
    private MdDevelopmentInfrastructureChildrenService mdDevelopmentInfrastructureChildrenService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public MdDevelopment initExplore(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject == null) return null;
        MdDevelopment mdDevelopment = new MdDevelopment();
        mdDevelopment.setName(schemeJudgeObject.getName());
        mdDevelopment.setCreator(commonService.thisUserAccount());
        saveAndUpdateMdDevelopment(mdDevelopment);
        return mdDevelopment;
    }

    public MdDevelopment getMdDevelopmentById(Integer id) {
        return mdDevelopmentDao.getMdDevelopmentById(id);
    }

    public void saveAndUpdateMdDevelopment(MdDevelopment oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            mdDevelopmentDao.addMdDevelopment(oo);
        } else {
            mdDevelopmentDao.updateMdDevelopment(oo);
        }
        MdArchitecturalObj mdArchitecturalObj = new MdArchitecturalObj();
        mdArchitecturalObj.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdDevelopment.class));
        mdArchitecturalObj.setPid(0);
        mdArchitecturalObj.setPlanDetailsId(oo.getPlanDetailsId());
        List<MdArchitecturalObj> mdArchitecturalObjList = mdArchitecturalObjService.getMdArchitecturalObjListByExample(mdArchitecturalObj) ;
        if (CollectionUtils.isNotEmpty(mdArchitecturalObjList)){
            for (MdArchitecturalObj po:mdArchitecturalObjList){
                po.setPid(oo.getId());
                mdArchitecturalObjService.saveMdArchitecturalObj(po) ;
            }
        }
        MdDevelopmentInfrastructureChildren infrastructureChildren = new MdDevelopmentInfrastructureChildren();
        infrastructureChildren.setPlanDetailsId(oo.getPlanDetailsId());
        infrastructureChildren.setPid(0);
        List<MdDevelopmentInfrastructureChildren> childrenList = mdDevelopmentInfrastructureChildrenService.getMdDevelopmentInfrastructureChildrenListByExample(infrastructureChildren) ;
        if (CollectionUtils.isNotEmpty(childrenList)){
            for (MdDevelopmentInfrastructureChildren po:childrenList){
                po.setPid(oo.getId());
                mdDevelopmentInfrastructureChildrenService.saveMdDevelopmentInfrastructureChildren(po) ;
            }
        }
    }

    public MdDevelopmentVo getMdDevelopmentVo(MdDevelopment mdDevelopment, boolean format) {
        if (mdDevelopment == null) {
            return null;
        }
        MdDevelopmentVo vo = new MdDevelopmentVo();
        BeanUtils.copyProperties(mdDevelopment, vo);
        if (StringUtils.isNotEmpty(vo.getContent())) {
            JSONObject jsonObject = null;
            try {
                jsonObject = JSON.parseObject(vo.getContent());
            } catch (Exception e) {
                logger.error("解析错误", e);
            }
            if (jsonObject != null) {
                if (jsonObject.get("f20") != null) {
                    vo.setF20(changeHundred((String) jsonObject.get("f20"), format));
                }
                if (jsonObject.get("f21") != null) {
                    vo.setF21((String) jsonObject.get("f21"));
                }
                if (jsonObject.get("f22") != null) {
                    vo.setF22((String) jsonObject.get("f22"));
                }
                if (jsonObject.get("f23") != null) {
                    vo.setF23((String) jsonObject.get("f23"));
                }
                if (jsonObject.get("f23Explain") != null) {
                    vo.setF23Explain((String) jsonObject.get("f23Explain"));
                }
                if (jsonObject.get("f24") != null) {
                    vo.setF24((String) jsonObject.get("f24"));
                }
                if (jsonObject.get("f25") != null) {
                    vo.setF25(changeHundred((String) jsonObject.get("f25"), format));
                }
                if (jsonObject.get("f27") != null) {
                    vo.setF27(changeHundred((String) jsonObject.get("f27"), format));
                }
                if (jsonObject.get("f29") != null) {
                    vo.setF29(changeHundred((String) jsonObject.get("f29"), format));
                }
                if (jsonObject.get("f29Explain") != null) {
                    vo.setF29Explain((String) jsonObject.get("f29Explain"));
                }

                if (jsonObject.get("f30") != null) {
                    vo.setF30(changeHundred((String) jsonObject.get("f30"), format));
                }
                if (jsonObject.get("f30Explain") != null) {
                    vo.setF30Explain((String) jsonObject.get("f30Explain"));
                }
                if (jsonObject.get("g32") != null) {
                    vo.setG32(changeHundred((String) jsonObject.get("g32"), format));
                }
                if (jsonObject.get("g32Explain") != null) {
                    vo.setG32Explain((String) jsonObject.get("g32Explain"));
                }
                if (jsonObject.get("f31") != null) {
                    vo.setF31((String) jsonObject.get("f31"));
                }
                if (jsonObject.get("f31Explain") != null) {
                    vo.setF31Explain((String) jsonObject.get("f31Explain"));
                }
                if (jsonObject.get("g33") != null) {
                    vo.setG33(changeHundred((String) jsonObject.get("g33"), format));
                }
                if (jsonObject.get("g33Explain") != null) {
                    vo.setG33Explain((String) jsonObject.get("g33Explain"));
                }
                if (jsonObject.get("g34") != null) {
                    vo.setG34(changeHundred((String) jsonObject.get("g34"), format));
                }
                if (jsonObject.get("g34Explain") != null) {
                    vo.setG34Explain((String) jsonObject.get("g34Explain"));
                }
                if (jsonObject.get("g35") != null) {
                    vo.setG35(changeHundred((String) jsonObject.get("g35"), format));
                }
                if (jsonObject.get("g35Explain") != null) {
                    vo.setG35Explain((String) jsonObject.get("g35Explain"));
                }
                if (jsonObject.get("f37") != null) {
                    vo.setF37(changeHundred((String) jsonObject.get("f37"), format));
                }
                if (jsonObject.get("f37Explain") != null) {
                    vo.setF37Explain((String) jsonObject.get("f37Explain"));
                }
                if (jsonObject.get("f38") != null) {
                    vo.setF38(changeHundred((String) jsonObject.get("f38"), format));
                }
                if (jsonObject.get("f38Explain") != null) {
                    vo.setF38Explain((String) jsonObject.get("f38Explain"));
                }
                if (jsonObject.get("f39") != null) {
                    vo.setF39(changeHundred((String) jsonObject.get("f39"), format));
                }
                if (jsonObject.get("f39Explain") != null) {
                    vo.setF39Explain((String) jsonObject.get("f39Explain"));
                }
                if (jsonObject.get("e43") != null) {
                    vo.setE43(changeHundred((String) jsonObject.get("e43"), format));
                }
                if (jsonObject.get("e43Explain") != null) {
                    vo.setE43Explain((String) jsonObject.get("e43Explain"));
                }

                if (jsonObject.get("f43") != null) {
                    vo.setF43((String) jsonObject.get("f43"));
                }
                if (jsonObject.get("g43") != null) {
                    vo.setG43((String) jsonObject.get("g43"));
                }
                if (jsonObject.get("d44") != null) {
                    vo.setD44((String) jsonObject.get("d44"));
                }
                if (jsonObject.get("d44Explain") != null) {
                    vo.setD44Explain(changeHundred((String) jsonObject.get("d44Explain"), format));
                }
                if (jsonObject.get("d45") != null) {
                    vo.setD45((String) jsonObject.get("d45"));
                }
                if (jsonObject.get("d45Explain") != null) {
                    vo.setD45Explain(changeHundred((String) jsonObject.get("d45Explain"), format));
                }
                if (jsonObject.get("d46") != null) {
                    vo.setD46((String) jsonObject.get("d46"));
                }
                if (jsonObject.get("d46Explain") != null) {
                    vo.setD46Explain(changeHundred((String) jsonObject.get("d46Explain"), format));
                }
                if (jsonObject.get("unsaleableBuildingArea") != null) {
                    vo.setUnsaleableBuildingArea((String) jsonObject.get("unsaleableBuildingArea"));
                }
            }
        }
        return vo;
    }

    private String changeHundred(String value, boolean format) {
        if (format) {
            if (NumberUtils.isNumber(value)) {
                BigDecimal bigDecimal = new BigDecimal(value).multiply(new BigDecimal(100));
                return String.format("%s%s", bigDecimal.setScale(2, BigDecimal.ROUND_UP).toString(), "%");
            }
        }
        return value;
    }

}
