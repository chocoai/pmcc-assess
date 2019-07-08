package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentEngineeringDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentLandDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.scheme.MdDevelopmentVo;
import com.copower.pmcc.erp.common.CommonService;
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
    private MdDevelopmentLandDao mdDevelopmentLandDao;
    @Autowired
    private MdDevelopmentEngineeringDao mdDevelopmentEngineeringDao;
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

    public MdDevelopmentEngineering getByMdDevelopmentEngineeringId(Integer id) {
        return mdDevelopmentEngineeringDao.getByMdDevelopmentEngineeringId(id);
    }

    public MdDevelopmentLand getByMdDevelopmentLandId(Integer id) {
        return mdDevelopmentLandDao.getByMdDevelopmentLandId(id);
    }

    public void saveAndUpdateMdDevelopment(MdDevelopment oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            mdDevelopmentDao.addMdDevelopment(oo);
        } else {
            mdDevelopmentDao.updateMdDevelopment(oo);
        }
    }


    public List<MdDevelopmentEngineering> getMdDevelopmentEngineeringList(MdDevelopmentEngineering oo) {
        return mdDevelopmentEngineeringDao.getMdDevelopmentEngineeringList(oo);
    }

    public void saveAndUpdateMdDevelopmentEngineering(MdDevelopmentEngineering oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            mdDevelopmentEngineeringDao.addMdDevelopmentEngineering(oo);
        } else {
            mdDevelopmentEngineeringDao.updateMdDevelopmentEngineering(oo);
        }
    }

    public void saveAndUpdateMdDevelopmentLand(MdDevelopmentLand oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            mdDevelopmentLandDao.addMdDevelopmentLand(oo);
        } else {
            mdDevelopmentLandDao.updateMdDevelopmentLand(oo);
        }
    }

    public List<MdDevelopmentLand> getMdDevelopmentLandList(MdDevelopmentLand mdDevelopmentLand) {
        return mdDevelopmentLandDao.getMdDevelopmentLandList(mdDevelopmentLand);
    }

    public MdDevelopmentVo getMdDevelopmentVo(MdDevelopment mdDevelopment) {
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
                    vo.setF20(changeHundred((String) jsonObject.get("f20")));
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
                if (jsonObject.get("f24") != null) {
                    vo.setF24((String) jsonObject.get("f24"));
                }
                if (jsonObject.get("f25") != null) {
                    vo.setF25(changeHundred((String) jsonObject.get("f25")));
                }
                if (jsonObject.get("f27") != null) {
                    vo.setF27(changeHundred((String) jsonObject.get("f27")));
                }
                if (jsonObject.get("f29") != null) {
                    vo.setF29(changeHundred((String) jsonObject.get("f29")));
                }
                if (jsonObject.get("f29Explain") != null) {
                    vo.setF29Explain((String) jsonObject.get("f29Explain"));
                }
            }
        }
        return vo;
    }

    public String changeHundred(String value){
        if (NumberUtils.isNumber(value)){
            BigDecimal bigDecimal = new BigDecimal(value).multiply(new BigDecimal(100));
            return bigDecimal.setScale(2, BigDecimal.ROUND_UP).toString() ;
        }
        return value;
    }

}
