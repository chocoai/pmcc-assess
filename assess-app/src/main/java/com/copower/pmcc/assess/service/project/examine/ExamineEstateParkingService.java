package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineEstateParkingDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateParking;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineEstateParkingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/19 18:07
 * @Description:车位信息
 */
@Service
public class ExamineEstateParkingService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ExamineEstateParkingDao examineEstateParkingDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineEstateParking getEstateParkingById(Integer id) {
        return examineEstateParkingDao.getEstateParkingById(id);
    }

    /**
     * 获取数据列表
     * @param examineEstateParking
     * @return
     */
    public List<ExamineEstateParking> getEstateParkingList(ExamineEstateParking examineEstateParking) {
        return examineEstateParkingDao.getEstateParkingList(examineEstateParking);
    }

    public BootstrapTableVo getExamineEstateNetworkList(ExamineEstateParking examineEstateNetwork){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineEstateParkingVo> vos = Lists.newArrayList();
        getEstateParkingList(examineEstateNetwork).stream().forEach(oo -> vos.add(getExamineEstateParkingVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineEstateParkingVo>() : vos);
        return vo;
    }

    public ExamineEstateParkingVo getExamineEstateParkingVo(ExamineEstateParking examineEstateParking){
        ExamineEstateParkingVo vo = new ExamineEstateParkingVo();
        BeanUtils.copyProperties(examineEstateParking,vo);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_CAR_TYPE);
        if (examineEstateParking.getParkingType()!=null){
            for (BaseDataDic base:baseDataDic){
                if (base.getId().equals(examineEstateParking.getParkingType())){
                    vo.setParkingTypeName(base.getName());
                }
            }
        }
        return vo;
    }

    /**
     * 新增
     * @param examineEstateParking
     * @return
     */
    public boolean addEstateParking(ExamineEstateParking examineEstateParking) {
        examineEstateParking.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineEstateParking.getDeclareId())){
            examineEstateParking.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineEstateParking.getExamineType())){
            examineEstateParking.setExamineType(0);
        }
        return examineEstateParkingDao.addEstateParking(examineEstateParking);
    }

    /**
     * 编辑
     * @param examineEstateParking
     * @return
     */
    public boolean updateEstateParking(ExamineEstateParking examineEstateParking) {
        return examineEstateParkingDao.updateEstateParking(examineEstateParking);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateParking(Integer id){
        return examineEstateParkingDao.deleteEstateParking(id);
    }
}
