package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.DeclareRecordItems;
import com.copower.pmcc.assess.common.DeclareRecordList;
import com.copower.pmcc.assess.dal.dao.DeclareRecordDao;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/5/15.
 */
@Service
public class DeclareRecordService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRecordDao dao;
    @Autowired
    private ErpAreaService areaService;

    @Autowired
    private ProjectInfoService projectInfoService;

    public DeclareRecordItems foreachDeclareRecord(){
        /**
         * 思路:所有非省会城市必然有一个中心城市即省会，有可能没有下级城市但是也可能有
         * 需求市 把相同城市下的房产信息放在一个列表中,然后在添加到一个列表中
         * 可能会有散乱的 把全部列表减去查询出来的就是散乱的
         */
        DeclareRecordItems declareRecordItems = new DeclareRecordItems();
        List<DeclareRecord> declareRecords = dao.queryAll();
        List<SysAreaDto> sysAreaDtos = areaService.getProvinceList();
        for (int i = 0; i < sysAreaDtos.size(); i++) {//省
            SysAreaDto sysAreaDto = sysAreaDtos.get(i);
            Integer id = sysAreaDto.getId();//省标识符
            List<SysAreaDto> citys = areaService.getAreaList(id+"");
            for (int j = 0; j < citys.size(); j++) {//市
                SysAreaDto sysAreaDto1 = citys.get(j);
                String parentId = sysAreaDto1.getParentId();//父标识符 也就是省会标识符
                String areaId = sysAreaDto1.getAreaId();//市标识符
                //县级或者县级市
                List<SysAreaDto> districts = areaService.getAreaList(areaId);
                //size==0那么可能是直辖市
                if (districts.size()!=0){
                    //非直辖市判断 并且添加到列表中
                    for (int k = 0; k < districts.size(); k++) {
                        try {
                            SysAreaDto sysAreaDto2 = districts.get(k);
                            String districtID = sysAreaDto2.getId()+"";//县或者县级市标识符
                            List<DeclareRecord> d1s = new ArrayList<>();
                            for (DeclareRecord d:declareRecords){
                                if (!StringUtils.isEmpty(d) && !StringUtils.isEmpty(d.getProvince()) && !StringUtils.isEmpty(d.getDistrict()) && !StringUtils.isEmpty(d.getCity())){
                                    if (d.getProvince().equals(parentId) && d.getCity().equals(areaId) && d.getDistrict().equals(districtID)){
                                        d1s.add(d);
                                    }
                                }
                            }
                            if (d1s.size()>=1){
                                DeclareRecordList declareRecordList = new DeclareRecordList();
                                if (id!=null){
                                    declareRecordList.setProvinceName(projectInfoService.getProvinceName(id));
                                }
                                if (!StringUtils.isEmpty(areaId)){
                                    declareRecordList.setCityName(projectInfoService.getSysArea(Integer.parseInt(areaId)));
                                }
                                if (!StringUtils.isEmpty(districtID)){
                                    declareRecordList.setDistrictName(projectInfoService.getSysArea(Integer.parseInt(districtID)));
                                }
                                declareRecordList.setDeclareRecords(d1s);
                                declareRecordItems.getItems().add(declareRecordList);
                            }
                        }catch (Exception e){
                            logger.error(e.getMessage());
                            logger.error("有可能个别数据有异常会抛出数组越界!");
                        }
                    }
                }else {
                    //直辖市判断 并且添加大盘列表中
                    List<DeclareRecord> d2s = new ArrayList<>();
                    for (DeclareRecord d:declareRecords){
                        if (!StringUtils.isEmpty(d) && !StringUtils.isEmpty(d.getProvince()) && !StringUtils.isEmpty(d.getDistrict()) && !StringUtils.isEmpty(d.getCity())){
                            if (d.getProvince().equals(parentId) && d.getCity().equals(areaId) ){
                                d2s.add(d);
                            }
                        }
                    }
                    if (d2s.size()>=1){
                        DeclareRecordList declareRecordList = new DeclareRecordList();
                        if (id!=null){
                            declareRecordList.setProvinceName(projectInfoService.getProvinceName(id));
                        }
                        if (!StringUtils.isEmpty(areaId)){
                            declareRecordList.setCityName(projectInfoService.getSysArea(Integer.parseInt(areaId)));
                        }
                        declareRecordList.setDeclareRecords(d2s);
                        declareRecordItems.getItems().add(declareRecordList);
                    }
                }
            }
        }
        logger.info(declareRecordItems.getItems().size()+"");
        return declareRecordItems;
    }



    /*相同省 市 ====>注意必须重写equals*/
    @Transactional(readOnly = true)
    public List<DeclareRecord> queryProvinceCityAll(){
        List<DeclareRecord> declareRecords = dao.queryProvinceCityAll();
        List<DeclareRecord> declareRecords2 = dao.queryProvinceCityDistrictAll();
        declareRecords.removeAll(declareRecords2);
        return declareRecords;
    }

    /*相同省 市 县*/
    @Transactional(readOnly = true)
    public List<DeclareRecord> queryProvinceCityDistrictAll(){
        List<DeclareRecord> declareRecords = dao.queryProvinceCityDistrictAll();
        return declareRecords;
    }

    public List<DeclareRecord> queryNotSelect(){
        List<DeclareRecord> declareRecords = dao.queryDeclareRecords();
        List<DeclareRecord> declareRecords2 = dao.queryProvinceAll();
        declareRecords.removeAll(declareRecords2);
        int size = declareRecords.size();
        if (declareRecords.size() == size){//说明没有删除掉,那么暴力删除
            declareRecords2.parallelStream().forEach(declareRecord -> {
                declareRecords.remove(declareRecord);//remove
            });
        }

        List<DeclareRecord> declareRecordk = new ArrayList<>();
        if (declareRecords.size() == size){//继续删除
            for (DeclareRecord record:declareRecords) {
                for (DeclareRecord r:declareRecords2) {
                    if (!record.equals(r)){
                        declareRecordk.add(record);
                    }
                }
            }
            return declareRecordk;
        }
        return declareRecords;
    }


    public List<DeclareRecord> getDeclareRecordByProjectId(Integer projectId) {
        List<DeclareRecord> declareRecords = dao.getDeclareRecordByProjectId(projectId);
        return declareRecords;
    }
}
