package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.DocumentBuilder;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.dal.basis.dao.method.MdArchitecturalObjDao;
import com.copower.pmcc.assess.dal.basis.entity.MdArchitecturalObj;
import com.copower.pmcc.assess.dal.basis.entity.MdCalculatingMethodEngineeringCost;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zch on 2019/7/8.
 * 建筑安装工程费
 */
@Service
public class MdArchitecturalObjService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private MdCalculatingMethodEngineeringCostService mdCalculatingMethodEngineeringCostService;
    @Autowired
    private MdArchitecturalObjDao mdArchitecturalObjDao;
    @Autowired
    private BaseService baseService;

    public boolean saveMdArchitecturalObj(MdArchitecturalObj mdArchitecturalObj) {
        if (mdArchitecturalObj == null) {
            return false;
        }
        if (mdArchitecturalObj.getId() != null && mdArchitecturalObj.getId() != 0) {
            return mdArchitecturalObjDao.updateMdArchitecturalObj(mdArchitecturalObj);
        } else {
            mdArchitecturalObj.setCreator(commonService.thisUserAccount());
            return mdArchitecturalObjDao.addMdArchitecturalObj(mdArchitecturalObj);
        }
    }


    public MdArchitecturalObj getMdArchitecturalObjById(Integer id) {
        return mdArchitecturalObjDao.getMdArchitecturalObjById(id);
    }

    public void deleteMdArchitecturalObjById(String id) {
        mdArchitecturalObjDao.deleteMdArchitecturalObjByIds(FormatUtils.transformString2Integer(id));
    }

    public void removeMdArchitecturalObj(String type, Integer pid, String databaseName, Integer planDetailsId) {
        mdArchitecturalObjDao.removeMdArchitecturalObj(type, pid, databaseName, planDetailsId);
    }

    public List<MdArchitecturalObj> getMdArchitecturalObjListByExample(MdArchitecturalObj oo) {
        return mdArchitecturalObjDao.getMdArchitecturalObjListByExample(oo);
    }

    /**
     * 组装工程费map
     *
     * @param costJSONObjectMap
     * @param projectPlanDetails
     */
    public void setMdCalculatingMethodEngineeringCostMapData(final LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap, final ProjectPlanDetails projectPlanDetails, Integer projectId) {
        if (projectPlanDetails == null) {
            return;
        }
        MdCalculatingMethodEngineeringCost select = new MdCalculatingMethodEngineeringCost();
        select.setProjectId(projectId);
        select.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
        List<MdCalculatingMethodEngineeringCost> mdCalculatingMethodEngineeringCostList = mdCalculatingMethodEngineeringCostService.getMdCalculatingMethodEngineeringCostListByExample(select);
        if (CollectionUtils.isEmpty(mdCalculatingMethodEngineeringCostList)) {
            return;
        }
        for (MdCalculatingMethodEngineeringCost oo : mdCalculatingMethodEngineeringCostList) {
            MdArchitecturalObj mdArchitecturalObj = getMdArchitecturalObjById(oo.getArchitecturalObjId());
            if (mdArchitecturalObj == null) {
                continue;
            }
            if (StringUtils.isEmpty(mdArchitecturalObj.getJsonContent())) {
                continue;
            }
            try {
                JSONArray jsonArray = JSONArray.parseArray(mdArchitecturalObj.getJsonContent());
                costJSONObjectMap.put(oo, jsonArray);
            } catch (Exception e) {
                String error = e.getMessage();
                baseService.writeExceptionInfo(e);
            }
        }
        final String DATAKEY = "dataKey";
        final String ROLE = "role";
        final String PRICE = "price";
        final String PARENT = "parent";
        final String CHILD = "child";
        if (!costJSONObjectMap.isEmpty()) {
            for (Map.Entry<MdCalculatingMethodEngineeringCost, JSONArray> entry : costJSONObjectMap.entrySet()) {
                JSONArray jsonArray = entry.getValue();
                if (jsonArray.isEmpty()){
                    continue;
                }
                Iterator<Object> objectIterator = jsonArray.iterator();
                Set<String> parents = Sets.newHashSet();
                Set<String> children = Sets.newHashSet();
                Set<String> intersections = Sets.newHashSet();
                while (objectIterator.hasNext()) {
                    Object object = objectIterator.next();
                    JSONObject jsonObject = null;
                    if (object != null && object instanceof JSONObject) {
                        jsonObject = (JSONObject) object;
                    }
                    if (jsonObject == null || jsonObject.isEmpty()) {
                        continue;
                    }
                    String role = jsonObject.getString(ROLE);
                    String dataKey = jsonObject.getString(DATAKEY);
                    String price = jsonObject.getString(PRICE);
                    if (StringUtils.isNotBlank(role)) {
                        if (PARENT.equals(role)) {
                            parents.add(dataKey);
                        }
                        //当判断价格存在即数据为有效
                        if (CHILD.equals(role) && StringUtils.isNotBlank(price)) {
                            if (NumberUtils.isNumber(price)) {
                                children.add(dataKey);
                            }
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(parents) && CollectionUtils.isNotEmpty(children)) {
                    intersections.addAll(CollectionUtils.intersection(children, parents));
                }
                if (CollectionUtils.isNotEmpty(intersections)) {
                    //重载 遍历 list
                    objectIterator = jsonArray.iterator();
                    while (objectIterator.hasNext()) {
                        Object object = objectIterator.next();
                        JSONObject jsonObject = null;
                        if (object != null && object instanceof JSONObject) {
                            jsonObject = (JSONObject) object;
                        }
                        if (jsonObject == null || jsonObject.isEmpty()) {
                            continue;
                        }
                        String dataKey = jsonObject.getString(DATAKEY);
                        if (!intersections.contains(dataKey)) {
                            objectIterator.remove();
                        }
                    }
                    entry.setValue(jsonArray);
                }
            }
        }
    }

    /**
     * 写工程费表格
     *
     * @param documentBuilder
     * @param costJSONObjectMap
     * @throws Exception
     */
    public synchronized void writeCalculatingMethodEngineeringCostSheet(DocumentBuilder documentBuilder, LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap) throws Exception {
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        LinkedList<String> linkedList = Lists.newLinkedList();
        for (Map.Entry<MdCalculatingMethodEngineeringCost, JSONArray> entry : costJSONObjectMap.entrySet()) {
            documentBuilder.writeln();
            com.aspose.words.Table table = documentBuilder.startTable();
            final AtomicInteger atomicInteger = new AtomicInteger(0);
            linkedList.addAll(Arrays.asList(String.join("", "工程名称:", entry.getKey().getName()), "", "", "", "", "", ""));
            AsposeUtils.writeWordTitle(documentBuilder, linkedList);
            mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), linkedList.size() - 1));
            linkedList.clear();
            atomicInteger.incrementAndGet();

            linkedList.addAll(Arrays.asList("列表属性", "", "估价时点完工程度", "单价(元/㎡)", "面积(㎡)", "计算值(元)", "描述"));
            AsposeUtils.writeWordTitle(documentBuilder, linkedList);
            mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), 1));
            atomicInteger.incrementAndGet();
            linkedList.clear();


            JSONArray jsonArray = entry.getValue();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String role = jsonObject.getString("role");
                String name = jsonObject.getString("name");
                //父级
                if ("parent".equals(role)) {
                    linkedList.add(String.join("", StringUtils.isNotBlank(name) ? name : "", StringUtils.repeat(" ", 1), ""));
                    linkedList.add("");
                    linkedList.add("");
                    linkedList.add("");
                    linkedList.add("");
                    linkedList.add("");
                    linkedList.add("");
                    AsposeUtils.writeWordTitle(documentBuilder, linkedList);
                    mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), linkedList.size() - 1));
                    linkedList.clear();
                    atomicInteger.incrementAndGet();
                }
                //子级
                if ("child".equals(role)) {
                    String valuationDateDegreeCompletion = jsonObject.getString("valuationDateDegreeCompletion");
                    String price = jsonObject.getString("price");
                    String reckon = jsonObject.getString("reckon");
                    String area = jsonObject.getString("area");
                    String remark = jsonObject.getString("remark");
                    if (!NumberUtils.isNumber(price)) {
                        continue;
                    }
                    linkedList.add(StringUtils.isNotEmpty(name) ? name : "");
                    linkedList.add("");
                    linkedList.add(StringUtils.isNotEmpty(valuationDateDegreeCompletion) ? valuationDateDegreeCompletion : "");
                    linkedList.add(StringUtils.isNotEmpty(price) ? ArithmeticUtils.round(price, 2) : "");
                    linkedList.add(StringUtils.isNotEmpty(area) ? ArithmeticUtils.round(area, 2) : "");
                    linkedList.add(handleDataByReckon(valuationDateDegreeCompletion, area, price, reckon));
                    linkedList.add(StringUtils.isNotEmpty(remark) ? remark : "");
                    AsposeUtils.writeWordTitle(documentBuilder, linkedList);
                    mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), 1));
                    linkedList.clear();
                    atomicInteger.incrementAndGet();
                }
            }
            if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                AsposeUtils.mergeCellTable(mergeCellModelList, table);
            }
            documentBuilder.endTable();
            mergeCellModelList.clear();
        }
    }

    private String handleDataByReckon(String valuationDateDegreeCompletion, String area, String price, String reckon) {
        if (StringUtils.isEmpty(reckon)) {
            return "";
        }
        switch (reckon) {
            case "a": {
                if (StringUtils.isNotBlank(price)) {
                    return ArithmeticUtils.round(price, 2);
                }
                return "";
            }
            case "b": {
                if (StringUtils.isNotBlank(valuationDateDegreeCompletion)) {
                    try {
                        BigDecimal temp = ArithmeticUtils.createBigDecimal(ArithmeticUtils.parseFormatString(valuationDateDegreeCompletion));
                        if (StringUtils.isNotBlank(price) && StringUtils.isNotBlank(area)) {
                            BigDecimal bigDecimal = ArithmeticUtils.multiply(ArithmeticUtils.sub("1", temp.toString()), ArithmeticUtils.mul(price, area));
                            return ArithmeticUtils.round(bigDecimal, 2);
                        }
                    } catch (ParseException e) {
                        baseService.writeExceptionInfo(e);
                    }
                }
                return "";
            }
            case "c": {
                if (StringUtils.isNotBlank(valuationDateDegreeCompletion)) {
                    try {
                        BigDecimal temp = ArithmeticUtils.createBigDecimal(ArithmeticUtils.parseFormatString(valuationDateDegreeCompletion));
                        if (StringUtils.isNotBlank(price) && StringUtils.isNotBlank(area)) {
                            BigDecimal bigDecimal = ArithmeticUtils.multiply(temp, ArithmeticUtils.mul(price, area));
                            return ArithmeticUtils.round(bigDecimal, 2);
                        }
                    } catch (ParseException e) {
                        baseService.writeExceptionInfo(e);
                    }
                }
                return "";
            }
            default:
                return "";
        }
    }

}
