package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationThinkingDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationThinking;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationThinkingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.method.MdCommonService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@Service(value = "evaluationThinkingService")
public class EvaluationThinkingService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationThinkingDao evaluationThinkingDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private MdCommonService mdCommonService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;

    /**
     * 保存数据
     *
     * @param evaluationThinking
     */
    public void saveAndUpdate(DataEvaluationThinking evaluationThinking) {
        if (evaluationThinking.getId() != null && evaluationThinking.getId() > 0) {
            evaluationThinkingDao.updateThinking(evaluationThinking);
        } else {
            evaluationThinking.setCreator(commonService.thisUserAccount());
            evaluationThinkingDao.addThinking(evaluationThinking);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeThinking(Integer id) {
        return evaluationThinkingDao.removeThinking(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public DataEvaluationThinking getThinking(Integer id) {
        return evaluationThinkingDao.getThinking(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getThinkingList(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataEvaluationThinking> hypothesisList = evaluationThinkingDao.getThinkingList(name);
        List<DataEvaluationThinkingVo> vos = LangUtils.transform(hypothesisList, p -> getThinkingVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationThinkingVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 根据委估目的及评估方法获取数据列表
     *
     * @param method
     * @return
     */
    public List<DataEvaluationThinking> getThinkingList(Integer method) {
        String methodStr = new String();
        String purposeStr = new String();
        if (method != null && method > 0) {
            methodStr = String.format(",%s,", method);
        }
        return evaluationThinkingDao.getThinkingList(methodStr);
    }


    public DataEvaluationThinkingVo getThinkingVo(DataEvaluationThinking thinking) {
        if (thinking == null) return null;
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD);
        DataEvaluationThinkingVo vo = new DataEvaluationThinkingVo();
        BeanUtils.copyProperties(thinking, vo);
        try {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(thinking.getMethod())) {
                vo.setMethodStr(baseDataDicService.getDataDicName(methodDicList, thinking.getMethod()));
            }
        } catch (Exception e1) {
            logger.error("exception:%s", e1.getMessage());
        }
        vo.setTypeName(baseProjectClassifyService.getTypeAndCategoryName(thinking.getType(), thinking.getCategory()));
        return vo;
    }

    /**
     * 将所有模板以评估方法作为分类
     *
     * @return
     */
    public Map<Integer, List<DataEvaluationThinking>> getEvaluationThinkingMap() {
        Map<Integer, List<DataEvaluationThinking>> map = Maps.newConcurrentMap();
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD);
        if (CollectionUtils.isNotEmpty(baseDataDics)) {
            for (BaseDataDic baseDataDic : baseDataDics) {
                List<DataEvaluationThinking> thinkingList = evaluationThinkingDao.getThinkingListByMethod(String.valueOf(baseDataDic.getId()));
                if (CollectionUtils.isNotEmpty(thinkingList)) {
                    map.put(baseDataDic.getId(), thinkingList);
                }
            }
        }
        return map;
    }

    /**
     * 获取上报告的评估思路
     *
     * @param map
     * @param projectInfo
     * @return
     */
    public String getReportThinking(HashMap<Integer, String> map, ProjectInfo projectInfo, List<Integer> baseJudgeNumber, List<Integer> otherJudgeNumber) {
        /*
        ①	先设立估价对象$(估价基准对象号)的市场价格为标准价，《以区域内类似房地产近期市场交易价格（比较法）》和《房地产未来预期收益（收益法）为导向综合》求取估价对象$(估价基准对象号)的市场价值。
        ②	再通$(评估其他方法)对估价对象$(评价对象号)进行特定因素调整，得到其市场价值。
        ③ $(委托目的特定评估思路)最后将估价对象的市场价值扣除估价师知悉的法定优先受偿款得到估价对象的抵押价值。
         */
        String compareExplain = "以区域内类似房地产近期市场交易价格";
        String incomeExplain = "房地产未来预期收益";
        String costExplain = "以房地产的重新开发建设成本";
        String developmentExplain = "以房地产房地产预期未来收益，即开发后的价值减去后续开发的必要支出及应得的利润额";
        StringBuilder stringBuilder = new StringBuilder();
        //第一段内容： 当只有一个方法时，根据方法取得模板，当有多个方法时，如果有多个基础方法则合并描述
        //第二段内容： 当方法中有其它方法，则用其它方法描述第二段
        //第三段内容： 当只有项目委托目的为抵押的时候才有第三段内容

        //需判断有没有设定标准估价对象
        if (map == null) return null;
        if (projectInfo == null) return null;
        StringBuilder firstDesc = new StringBuilder("(1)");//第一段描述
        StringBuilder secondDesc = new StringBuilder();//第二段描述
        StringBuilder thirdDesc = new StringBuilder();//第三段描述
        List<Integer> baseMethodList = Lists.newArrayList();
        List<Integer> baseOtherList = Lists.newArrayList();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (mdCommonService.isBaseMethod(projectInfo.getProjectCategoryId(), entry.getKey()))
                baseMethodList.add(entry.getKey());
            if (mdCommonService.isOtherMethod(projectInfo.getProjectCategoryId(), entry.getKey()))
                baseOtherList.add(entry.getKey());
        }
        String baseJudgeNumberString = generateCommonMethod.convertNumber(baseJudgeNumber);
        if (CollectionUtils.isNotEmpty(baseJudgeNumber)) {
            firstDesc.append(String.format("先设立估价对象%s的市场价格为标准价，", baseJudgeNumberString));
        }

        if (baseMethodList.size() == 1) {
            List<DataEvaluationThinking> thinkingList = evaluationThinkingDao.getThinkingListByMethod(String.valueOf(baseMethodList.get(0)));
            if (CollectionUtils.isNotEmpty(thinkingList)) {
                String stringJudgeNumber = CollectionUtils.isNotEmpty(baseJudgeNumber) ? baseJudgeNumberString : this.getJudgeNumber(map.get(baseMethodList.get(0)));
                String thinkingTemp = thinkingList.get(0).getTemplateContent().replaceAll("#\\{估价对象号\\}", stringJudgeNumber);
                firstDesc.append(thinkingTemp);
            }
        } else {
            String firstString = new String();
            StringBuilder builder = new StringBuilder();
            for (Integer methodTyp : baseMethodList) {
                if (mdCommonService.isCompareMethod(methodTyp))
                    firstString += compareExplain + "和";
                if (mdCommonService.isIncomeMethod(methodTyp))
                    firstString += incomeExplain + "和";
                if (mdCommonService.isCostMethod(methodTyp))
                    firstString += costExplain + "和";
                if (mdCommonService.isDevelopmentMethod(methodTyp))
                    firstString += developmentExplain + "和";
                builder.append(map.get(baseMethodList.get(0))).append(",");
            }
            firstDesc.append(StringUtils.strip(firstString, "和")).append(String.format("为导向综合求取估价对象%s的市场价值。", StringUtils.strip(builder.toString(), ",")));
        }

        if (CollectionUtils.isNotEmpty(baseJudgeNumber)) {
            String otherJudgeNumberString = generateCommonMethod.convertNumber(otherJudgeNumber);
            secondDesc.append(String.format("(2)再通过标准价格调整法对估价对象%s进行特定因素调整，得到其市场价值。", otherJudgeNumberString));
        }

        BaseDataDic dataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE);
        if (dataDic != null && projectInfo.getEntrustPurpose().equals(dataDic.getId())) {
            if (secondDesc.length() > 0)
                thirdDesc.append("(3)");
            else
                thirdDesc.append("(2)");
            thirdDesc.append("最后将估价对象的市场价值扣除估价师知悉的法定优先受偿款得到估价对象的抵押价值。");
        }
        return stringBuilder.append(generateCommonMethod.getIndentHtml(firstDesc.toString()))
                .append(generateCommonMethod.getIndentHtml(secondDesc.toString()))
                .append(generateCommonMethod.getIndentHtml(thirdDesc.toString())).toString();
    }

    private String getJudgeNumber(String number) {
        if (StringUtils.isBlank(number)) return "";
        List<Integer> integerList = null;
        try {
            integerList = generateCommonMethod.splitIntegerListJudgeNumber(number);
        } catch (Exception e) {
            integerList = Arrays.asList(generateCommonMethod.parseIntJudgeNumber(number));
        }
        return generateCommonMethod.convertNumber(integerList);
    }
}
