package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataLocaleSurveyPictureDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurveyPicture;
import com.copower.pmcc.assess.dto.output.data.DataLocaleSurveyPictureVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 * @Description:税率配置
 */
@Service
public class DataLocaleSurveyPictureService {
    @Autowired
    private DataLocaleSurveyPictureDao dataLocaleSurveyPictureDao;
    @Autowired
    private CommonService commonService;

    @Autowired
    private BaseDataDicService baseDataDicService;

    public Integer saveAndUpdateDataLocaleSurveyPicture(DataLocaleSurveyPicture dataLocaleSurveyPicture) {
        if (dataLocaleSurveyPicture.getId() == null) {
            dataLocaleSurveyPicture.setCreator(commonService.thisUserAccount());
            return dataLocaleSurveyPictureDao.addDataLocaleSurveyPicture(dataLocaleSurveyPicture);
        } else {
            dataLocaleSurveyPictureDao.updateDataLocaleSurveyPicture(dataLocaleSurveyPicture);
            return null;
        }
    }

    public DataLocaleSurveyPicture getDataLocaleSurveyPictureById(Integer id) {
        return dataLocaleSurveyPictureDao.getDataLocaleSurveyPictureById(id);
    }

    public BootstrapTableVo getDataLocaleSurveyPictureListVos(DataLocaleSurveyPicture dataLocaleSurveyPicture) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLocaleSurveyPictureVo> vos = getDataLocaleSurveyPictureVos(dataLocaleSurveyPicture);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataLocaleSurveyPictureVo> getDataLocaleSurveyPictureVos(DataLocaleSurveyPicture dataLocaleSurveyPicture) {
        List<DataLocaleSurveyPicture> dataLocaleSurveyPictures = dataLocaleSurveyPictureDao.getDataLocaleSurveyPictureList(dataLocaleSurveyPicture);
        List<DataLocaleSurveyPictureVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataLocaleSurveyPictures)) {
            for (DataLocaleSurveyPicture landLevel : dataLocaleSurveyPictures) {
                vos.add(getDataLocaleSurveyPictureVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataLocaleSurveyPicture(DataLocaleSurveyPicture dataLocaleSurveyPicture) {
        dataLocaleSurveyPictureDao.removeDataLocaleSurveyPicture(dataLocaleSurveyPicture);
    }


    public DataLocaleSurveyPictureVo getDataLocaleSurveyPictureVo(DataLocaleSurveyPicture dataLocaleSurveyPicture) {
        DataLocaleSurveyPictureVo vo = new DataLocaleSurveyPictureVo();
        BaseDataDic baseDataDic = null;
        BeanUtils.copyProperties(dataLocaleSurveyPicture, vo);

        if (dataLocaleSurveyPicture.getType() != null && dataLocaleSurveyPicture.getType() > 0) {
            baseDataDic = baseDataDicService.getDataDicById(dataLocaleSurveyPicture.getType());
            if (baseDataDic != null) {
                vo.setTypeName(baseDataDic.getName());
            }
        }
        return vo;
    }
}
