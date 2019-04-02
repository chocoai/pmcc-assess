package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.DocumentBuilder;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权益分析信息
 */
@Service
public class GenerateEquityService {
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyCommonService surveyCommonService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String HOUSE = "房产";
    private final String LAND = "土地";
    private final String SCHEME_JUDGEOBJECT_NAME = "号";
    private final String errorStr = "无";

    //1.土地权益状况
    //权证权益
    //规划条件
    //土地利用状况
    //他权类别
    //他权特殊情况


    //2.房屋权益状况
    //权证权益
    //他权类别
    //他权特殊情况
    //物业服务
    //物业公司
    //房产评估价益综合评价

    public void writeText(Integer areaId, ProjectInfoVo projectInfoVo,  DocumentBuilder builder) throws Exception {
        LinkedHashMap<String, List<SchemeJudgeObject>> listLinkedHashMap = generateCommonMethod.getLinkedHashMapEstateNameSchemeJudgeObjectList(areaId);
        if (projectInfoVo.getProjectCategoryName().indexOf(LAND) != -1) {
            if (!listLinkedHashMap.isEmpty()){
                this.writeLand(areaId, projectInfoVo, listLinkedHashMap, builder);
            }
        }
        if (projectInfoVo.getProjectCategoryName().indexOf(HOUSE) != -1) {
            if (!listLinkedHashMap.isEmpty()){
                this.writeHouse(areaId, projectInfoVo, listLinkedHashMap, builder);
            }
        }
    }

    /**
     * 土地
     * @param areaId
     * @param projectInfoVo
     * @param listLinkedHashMap
     * @param documentBuilder
     * @throws Exception
     */
    private void writeLand(Integer areaId, ProjectInfoVo projectInfoVo, LinkedHashMap<String, List<SchemeJudgeObject>> listLinkedHashMap, DocumentBuilder documentBuilder)throws Exception {

    }

    /**
     * 房产
     * @param areaId
     * @param projectInfoVo
     * @param listLinkedHashMap
     * @param documentBuilder
     * @throws Exception
     */
    private void writeHouse(Integer areaId, ProjectInfoVo projectInfoVo, LinkedHashMap<String, List<SchemeJudgeObject>> listLinkedHashMap, DocumentBuilder documentBuilder)throws Exception {
        int k = 0;
        for (Map.Entry<String,List<SchemeJudgeObject>> entry:listLinkedHashMap.entrySet()){
            List<DeclareRecord> declareRecordList = this.getDeclareRecordList(entry.getValue());
            if (CollectionUtils.isEmpty(declareRecordList)){
                continue;
            }
            documentBuilder.writeln(String.format("1、房屋权益状况:%s", this.getRightsAndInterests(HOUSE,declareRecordList,entry.getValue())));
            documentBuilder.writeln();
        }
    }

    private List<DeclareRecord> getDeclareRecordList(List<SchemeJudgeObject> schemeJudgeObjectList){
        List<DeclareRecord> declareRecordList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)){
            schemeJudgeObjectList.stream().forEach(oo -> {
                if (oo.getDeclareRecordId() != null){
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(oo.getDeclareRecordId());
                    if (declareRecord != null){
                        declareRecordList.add(declareRecord);
                    }
                }
            });
        }
        return declareRecordList;
    }

    /**
     * 权益状况
     * @param type
     * @param declareRecordList
     * @param schemeJudgeObjectList
     * @return
     * @throws Exception
     */
    private String getRightsAndInterests(String type,List<DeclareRecord> declareRecordList,List<SchemeJudgeObject> schemeJudgeObjectList)throws Exception{
        StringBuilder stringBuilder = new StringBuilder(8);
        if (Objects.equal(HOUSE,type)){
            List<Integer> integerList = schemeJudgeObjectList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
            stringBuilder.append(generateCommonMethod.convertNumber(integerList)).append(SCHEME_JUDGEOBJECT_NAME);
            Set<Integer> natures = Sets.newHashSet();
        }
        if (Objects.equal(LAND,type)){

        }
        if (StringUtils.isNotBlank(stringBuilder.toString().trim())){
            stringBuilder.append(" ") ;
        }
        return stringBuilder.toString() ;
    }
}
