package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordExtendDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/6/14 16:41
 * @description: 申报 扩展表
 */
@Service
public class DeclareRecordExtendService {

    @Autowired
    private DeclareRecordExtendDao dao;

    public List<DeclareRecordExtend> getDeclareRecordListByIds(List<Integer> ids) {
        return dao.getDeclareRecordListByIds(ids);
    }

    public boolean updateDeclareRecord( DeclareRecordExtend declareRecord) {
        return dao.updateDeclareRecord(declareRecord);
    }

    public boolean addDeclareRecord( DeclareRecordExtend declareRecord) {
        return dao.addDeclareRecord(declareRecord);
    }

    public boolean deleteDeclareRecord(Integer id) {
        return dao.deleteDeclareRecord(id);
    }

    public  DeclareRecordExtend getDeclareRecordById(Integer id) {
        return dao.getDeclareRecordById(id);
    }

    public List<DeclareRecordExtend> getDeclareLandUsePermitList(DeclareRecordExtend oo){
        return dao.getDeclareLandUsePermitList(oo);
    }

}
