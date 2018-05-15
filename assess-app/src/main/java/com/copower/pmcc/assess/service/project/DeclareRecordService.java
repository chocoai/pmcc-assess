package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.DeclareRecordItems;
import com.copower.pmcc.assess.dal.dao.DeclareRecordDao;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/5/15.
 */
@Service
public class DeclareRecordService {

    @Autowired
    private DeclareRecordDao dao;

    /*list*/
    public DeclareRecordItems items(){
        DeclareRecordItems declareRecordItems = new DeclareRecordItems();
        List<List<DeclareRecord>> items = new ArrayList<>();
        items.add(queryProvinceCityAll());
        items.add(queryProvinceCityDistrictAll());
        declareRecordItems.setItems(items);
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


}
