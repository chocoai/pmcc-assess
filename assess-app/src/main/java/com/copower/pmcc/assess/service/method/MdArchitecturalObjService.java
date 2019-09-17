package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.Cell;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Table;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.dal.basis.dao.method.MdArchitecturalObjDao;
import com.copower.pmcc.assess.dal.basis.entity.MdArchitecturalObj;
import com.copower.pmcc.assess.dal.basis.entity.MdCalculatingMethodEngineeringCost;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

    public void clear(Integer planDetailsId) {
        MdArchitecturalObj oo = new MdArchitecturalObj();
        oo.setCreator(commonService.thisUserAccount());
        oo.setPrice(new BigDecimal(0));
        oo.setPlanDetailsId(planDetailsId);
        List<MdArchitecturalObj> list = getMdArchitecturalObjListByExample(oo);
        if (CollectionUtils.isNotEmpty(list)) {
            for (MdArchitecturalObj architecturalObj : list) {
                deleteMdArchitecturalObjById(architecturalObj.getId());
            }
        }
    }

    public MdArchitecturalObj getMdArchitecturalObjById(Integer id) {
        return mdArchitecturalObjDao.getMdArchitecturalObjById(id);
    }

    public boolean deleteMdArchitecturalObjById(Integer id) {
        return mdArchitecturalObjDao.deleteMdArchitecturalObjById(id);
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
        select.setPlanDetailsId(projectPlanDetails.getId());
        List<MdCalculatingMethodEngineeringCost> mdCalculatingMethodEngineeringCostList = mdCalculatingMethodEngineeringCostService.getMdCalculatingMethodEngineeringCostListByExample(select);
        if (CollectionUtils.isEmpty(mdCalculatingMethodEngineeringCostList)) {
            return;
        }
        for (MdCalculatingMethodEngineeringCost oo : mdCalculatingMethodEngineeringCostList) {
            MdArchitecturalObj mdArchitecturalObj = getMdArchitecturalObjById(oo.getArchitecturalObjId());
            if (StringUtils.isEmpty(oo.getType())) {
                continue;
            }
            if (mdArchitecturalObj == null) {
                continue;
            }
            if (StringUtils.isEmpty(mdArchitecturalObj.getType())) {
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
    }

    /**
     * 写工程费表格
     *
     * @param documentBuilder
     * @param costJSONObjectMap
     * @throws Exception
     */
    public void writeCalculatingMethodEngineeringCostSheet(DocumentBuilder documentBuilder, LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap) throws Exception {
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        LinkedList<String> linkedList = Lists.newLinkedList();
        for (Map.Entry<MdCalculatingMethodEngineeringCost, JSONArray> entry : costJSONObjectMap.entrySet()) {
            documentBuilder.writeln();
            com.aspose.words.Table table = documentBuilder.startTable();
            final AtomicInteger atomicInteger = new AtomicInteger(0);
            linkedList.addAll(Arrays.asList(String.join("", "工程名称:", entry.getKey().getName()), "", "", "", "", ""));
            AsposeUtils.writeWordTitle(documentBuilder, linkedList);
            mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), linkedList.size() - 1));
            linkedList.clear();
            atomicInteger.incrementAndGet();

            linkedList.addAll(Arrays.asList("列表属性", "", "估价时点完工程度", "单价(元/㎡)", "面积(㎡)", "估价时点单价(元/㎡)"));
            AsposeUtils.writeWordTitle(documentBuilder, linkedList);
            mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), 1));
            atomicInteger.incrementAndGet();
            linkedList.clear();


            JSONArray jsonArray = entry.getValue();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String role = jsonObject.getString("role");
                //父级
                if ("parent".equals(role)) {
                    linkedList.add(String.join("", jsonObject.getString("name"), StringUtils.repeat(" ", 1), "(父级)"));
                    linkedList.add("");
                    linkedList.add("/");
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
                    linkedList.add("");
                    linkedList.add(jsonObject.getString("name"));
                    String valuationDateDegreeCompletion = jsonObject.getString("valuationDateDegreeCompletion");
                    String price = jsonObject.getString("price");
                    String area = jsonObject.getString("area");
                    linkedList.add(StringUtils.isNotBlank(valuationDateDegreeCompletion) ? valuationDateDegreeCompletion : "");
                    linkedList.add(StringUtils.isNotBlank(price) ? price : "");
                    linkedList.add(StringUtils.isNotBlank(area) ? area : "");
                    if (StringUtils.isNotBlank(valuationDateDegreeCompletion) && StringUtils.isNotBlank(price)) {
                        try {
                            String string = ArithmeticUtils.parseFormatString(valuationDateDegreeCompletion);
                            String costPrice = ArithmeticUtils.mul(string, price, 2);
                            linkedList.add(costPrice);
                        } catch (ParseException e) {
                            linkedList.add("");
                        }
                    } else {
                        linkedList.add("");
                    }
                    AsposeUtils.writeWordTitle(documentBuilder, linkedList);
                    linkedList.clear();
                    atomicInteger.incrementAndGet();
                }
            }
            if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                mergeCellTable(mergeCellModelList, table);
            }
            documentBuilder.endTable();
            mergeCellModelList.clear();
        }
    }

    private void mergeCellTable(Set<MergeCellModel> mergeCellModelList, Table table) {
        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                try {
                    Cell cellStartRange = null;
                    Cell cellEndRange = null;
                    if (mergeCellModel.getCellEndRange() == null && mergeCellModel.getCellStartRange() == null) {
                        cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                        cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                    } else {
                        cellStartRange = mergeCellModel.getCellStartRange();
                        cellEndRange = mergeCellModel.getCellEndRange();
                    }
                    if (cellStartRange != null && cellEndRange != null) {
                        if (table != null) {
                            AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

}
