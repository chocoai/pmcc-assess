package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.StageWeightProportionDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.DataStageWeightProportion;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.input.data.StageWeightProportionDto;
import com.copower.pmcc.assess.dto.output.data.ProportionTempVo;
import com.copower.pmcc.assess.dto.output.data.StageWeightProportionVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.manage.ProjectWorkStageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "stageWeightProportionService")
public class StageWeightProportionService {

    @Autowired
    private StageWeightProportionDao stageWeightProportionDao;

    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;


    public BootstrapTableVo getList(Integer entrustmentPurpose) {
        BootstrapTableVo vo = new BootstrapTableVo();
        List<DataStageWeightProportion> list = stageWeightProportionDao.getList(entrustmentPurpose);
        List<StageWeightProportionVo> stageWeightProportionVos = getVoList(list);
        List<ProportionTempVo> proportionTempVos = getStageProportion(stageWeightProportionVos);    //把相同委托目的拼接到一起
        //page.setTotal();
        vo.setTotal((long) proportionTempVos.size());  //同步记录数
        vo.setRows(CollectionUtils.isEmpty(proportionTempVos) ? new ArrayList<ProportionTempVo>() : proportionTempVos);
        return vo;
    }

    private List<StageWeightProportionVo> getVoList(List<DataStageWeightProportion> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            StageWeightProportionVo stageWeightProportionVo = new StageWeightProportionVo();
            BeanUtils.copyProperties(p, stageWeightProportionVo);
            if (p.getEntrustPurpose() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getEntrustPurpose());
                if (baseDataDic != null)
                    stageWeightProportionVo.setEntrustPurposeName(baseDataDic.getName());
            }
            if(p.getStage() != null){
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getStage());
                if(baseDataDic != null){
                    stageWeightProportionVo.setStageName(baseDataDic.getName());
                }
            }
            return stageWeightProportionVo;
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(StageWeightProportionDto stageWeightProportionDto) throws BusinessException {
        if (stageWeightProportionDto == null) {
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        }
        String proportionList = stageWeightProportionDto.getProportionList();
        String[] strings = proportionList.split(",");
        if (stageWeightProportionDto.getId() != null && stageWeightProportionDto.getId() > 0) {
            Integer entrustPurpose = stageWeightProportionDto.getId();
            List<DataStageWeightProportion> stageWeightProportions = stageWeightProportionDao.getList(entrustPurpose);
            for(int i = 0;i<stageWeightProportions.size();i++){
                stageWeightProportions.get(i).setProportion(Integer.valueOf(strings[i]));
                stageWeightProportionDao.update(stageWeightProportions.get(i));
            }
        } else {
            BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getDefaultType();
            List<ProjectWorkStage> projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(baseProjectClassify.getId(), true);
            for(int i = 0;i<projectWorkStages.size();i++){
                StageWeightProportionDto stageWeightProportionDto1 = new StageWeightProportionDto();
                stageWeightProportionDto1.setEntrustPurpose(stageWeightProportionDto.getEntrustPurpose());
                stageWeightProportionDto1.setStage(projectWorkStages.get(i).getId());
                stageWeightProportionDto1.setProportion(Integer.valueOf(strings[i]));
                stageWeightProportionDto1.setCreator(processControllerComponent.getThisUser());
                stageWeightProportionDao.save(stageWeightProportionDto1);
            }
        }
    }

    public boolean delete(Integer entrustPurpose) throws BusinessException {
        if (entrustPurpose == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        return stageWeightProportionDao.delete(entrustPurpose);
    }

    //拼接委托目的显示
    private List<ProportionTempVo> getStageProportion(List<StageWeightProportionVo> stageWeightProportionVos){
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);  //委托目的
        List<ProportionTempVo> proportionTempVos = new ArrayList<ProportionTempVo>();
        for(BaseDataDic baseDataDic : baseDataDics){    //循环委托目的取name
            String name = baseDataDic.getName();    //委托目的具体名字
            List<String> str = new ArrayList<String>();
            ProportionTempVo proportionTempVo = new ProportionTempVo();
                for( StageWeightProportionVo stageWeightProportionVo : stageWeightProportionVos) {
                    if (stageWeightProportionVo.getEntrustPurposeName().equals(name)) {
                        str.add(stageWeightProportionVo.getStageName() + "：" + stageWeightProportionVo.getProportion());    //循环数据合并在一起
                        proportionTempVo.setEntrustPurpose(stageWeightProportionVo.getEntrustPurpose());
                    }
                }

            if(str.size() > 0){
                    String stageProportionName = String.join(",", str); //转字符串
                    proportionTempVo.setEntrustPurposeName(name);
                    proportionTempVo.setStageProportionName(stageProportionName);
                    proportionTempVos.add(proportionTempVo);    //为显示用的临时vo
                }
        }
        return proportionTempVos;
    }

    public List<DataStageWeightProportion> edit(DataStageWeightProportion stageWeightProportion) {
        List<DataStageWeightProportion> stageWeightProportions = stageWeightProportionDao.edit(stageWeightProportion);
        return stageWeightProportions;
    }
}
