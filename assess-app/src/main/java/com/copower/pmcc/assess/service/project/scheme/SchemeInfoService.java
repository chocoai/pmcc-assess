package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeInfo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Service
public class SchemeInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeInfoDao schemeInfoDao;

    /**
     * 保存信息
     *
     * @param schemeInfo
     * @throws BusinessException
     */
    public void saveSchemeInfo(SchemeInfo schemeInfo) throws BusinessException {
        if (schemeInfo == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (schemeInfo.getId() != null && schemeInfo.getId() > 0) {
            schemeInfoDao.updateInfo(schemeInfo);
        } else {
            schemeInfo.setCreator(commonService.thisUserAccount());
            schemeInfoDao.addInfo(schemeInfo);
        }
    }

    public SchemeInfo getSchemeInfo(Integer judgeObjectId, Integer methodType) {
        SchemeInfo examle = new SchemeInfo();
        examle.setJudgeObjectId(judgeObjectId);
        examle.setMethodType(methodType);
        SchemeInfo schemeInfo = schemeInfoDao.getSchemeInfo(examle);
        return schemeInfo;
    }

    public List<SchemeInfo> getSchemeInfoList(Integer projectId) {
        SchemeInfo examle = new SchemeInfo();
        examle.setProjectId(projectId);
        return schemeInfoDao.getInfoList(examle);
    }

    public List<SchemeInfo> getInfoList(SchemeInfo oo){
        return schemeInfoDao.getInfoList(oo) ;
    }

    public void deleteSchemeInfoByProjectId(Integer projectId) {
        List<SchemeInfo> schemeInfoList = getSchemeInfoList(projectId);
        if (!CollectionUtils.isEmpty(schemeInfoList)) {
            schemeInfoList.forEach(o -> schemeInfoDao.deleteInfo(o.getId()));
        }
    }

    public SchemeInfo getSchemeInfo(Integer planDetailsId) {
        SchemeInfo examle = new SchemeInfo();
        examle.setPlanDetailsId(planDetailsId);
        SchemeInfo schemeInfo = schemeInfoDao.getSchemeInfo(examle);
        return schemeInfo;
    }
}