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
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean saveMdArchitecturalObj(MdArchitecturalObj mdArchitecturalObj){
        if (mdArchitecturalObj == null){
            return false;
        }
        if (mdArchitecturalObj.getId() != null && mdArchitecturalObj.getId() != 0){
            return mdArchitecturalObjDao.updateMdArchitecturalObj(mdArchitecturalObj);
        }else {
            mdArchitecturalObj.setCreator(commonService.thisUserAccount());
            return mdArchitecturalObjDao.addMdArchitecturalObj(mdArchitecturalObj) ;
        }
    }

    public void clear(){
        MdArchitecturalObj oo = new MdArchitecturalObj();
        oo.setCreator(commonService.thisUserAccount());
        oo.setPid(0);
        List<MdArchitecturalObj> list = getMdArchitecturalObjListByExample(oo);
        if (CollectionUtils.isNotEmpty(list)){
            for (MdArchitecturalObj architecturalObj:list){
                deleteMdArchitecturalObjById(architecturalObj.getId()) ;
            }
        }
    }

    public MdArchitecturalObj getMdArchitecturalObjById(Integer id){
        return mdArchitecturalObjDao.getMdArchitecturalObjById(id) ;
    }

    public boolean deleteMdArchitecturalObjById(Integer id){
        return mdArchitecturalObjDao.deleteMdArchitecturalObjById(id) ;
    }

    public void removeMdArchitecturalObj(String type,Integer pid,String databaseName,Integer planDetailsId){
        mdArchitecturalObjDao.removeMdArchitecturalObj(type, pid, databaseName, planDetailsId);
    }

    public List<MdArchitecturalObj> getMdArchitecturalObjListByExample(MdArchitecturalObj oo){
        return mdArchitecturalObjDao.getMdArchitecturalObjListByExample(oo);
    }

    /**
     * 组装工程费map
     * @param costJSONObjectMap
     * @param projectPlanDetails
     */
    public void setMdCalculatingMethodEngineeringCostMapData(final LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap,final ProjectPlanDetails projectPlanDetails,Integer projectId){
        if (projectPlanDetails == null){
            return;
        }
        MdCalculatingMethodEngineeringCost select = new MdCalculatingMethodEngineeringCost();
        select.setProjectId(projectId);
        select.setPlanDetailsId(projectPlanDetails.getPid());
        List<MdCalculatingMethodEngineeringCost> mdCalculatingMethodEngineeringCostList = mdCalculatingMethodEngineeringCostService.getMdCalculatingMethodEngineeringCostListByExample(select) ;
        if (CollectionUtils.isEmpty(mdCalculatingMethodEngineeringCostList)){
            return;
        }
        for (MdCalculatingMethodEngineeringCost oo :mdCalculatingMethodEngineeringCostList){
            MdArchitecturalObj mdArchitecturalObj = getMdArchitecturalObjById(oo.getArchitecturalObjId()) ;
            if (StringUtils.isEmpty(oo.getType())){
                continue;
            }
            if (mdArchitecturalObj == null){
                continue;
            }
            if (StringUtils.isEmpty(mdArchitecturalObj.getType())){
                continue;
            }
            if (StringUtils.isEmpty(mdArchitecturalObj.getJsonContent())){
                continue;
            }
            JSONArray jsonArray = JSONArray.parseArray(mdArchitecturalObj.getJsonContent());
            costJSONObjectMap.put(oo,jsonArray) ;
        }
    }

    /**
     * 写工程费表格
     * @param documentBuilder
     * @param linkedList
     * @param costJSONObjectMap
     * @param mergeCellModelList
     * @param atomicInteger
     * @throws Exception
     */
    public void writeCalculatingMethodEngineeringCostSheet(DocumentBuilder documentBuilder, LinkedList<String> linkedList, LinkedHashMap<MdCalculatingMethodEngineeringCost, JSONArray> costJSONObjectMap, Set<MergeCellModel> mergeCellModelList, final AtomicInteger atomicInteger) throws Exception {
        for (Map.Entry<MdCalculatingMethodEngineeringCost, JSONArray> entry : costJSONObjectMap.entrySet()) {
            linkedList.addAll(Arrays.asList(String.join("工程名称:", "", entry.getKey().getName()), "", "", "", ""));
            AsposeUtils.writeWordTitle(documentBuilder, linkedList);
            mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), linkedList.size() - 1));
            linkedList.clear();
            atomicInteger.incrementAndGet();

            linkedList.addAll(Arrays.asList("列表属性", "", "估价时点完工程度", "单方造价(元/㎡)", "估价时点单价(元/㎡)"));
            AsposeUtils.writeWordTitle(documentBuilder, linkedList);
            mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), 1));
            atomicInteger.incrementAndGet();
            linkedList.clear();


            JSONArray jsonArray = entry.getValue();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                //父级
                if (jsonObject.size() == 3) {
                    linkedList.add(String.join("", jsonObject.getString("name"), StringUtils.repeat(" ", 1), "(父级)"));
                    linkedList.add("");

                    linkedList.add("/");
                    linkedList.add("");
                    linkedList.add("");
                    AsposeUtils.writeWordTitle(documentBuilder, linkedList);
                    mergeCellModelList.add(new MergeCellModel(atomicInteger.get(), 0, atomicInteger.get(), linkedList.size() - 1));
                    linkedList.clear();
                }
                //子级
                if (jsonObject.size() != 3) {
                    linkedList.add("");
                    linkedList.add(jsonObject.getString("name"));

                    String valuationDateDegreeCompletion = jsonObject.getString("valuationDateDegreeCompletion");
                    String price = jsonObject.getString("price");

                    linkedList.add(StringUtils.isNotBlank(valuationDateDegreeCompletion) ? valuationDateDegreeCompletion : "");
                    linkedList.add(StringUtils.isNotBlank(price) ? price : "");
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
                }
                atomicInteger.incrementAndGet();
            }

        }
    }

}
