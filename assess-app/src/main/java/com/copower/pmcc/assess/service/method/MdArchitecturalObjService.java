package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdArchitecturalObjDao;
import com.copower.pmcc.assess.dal.basis.entity.MdArchitecturalObj;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zch on 2019/7/8.
 * 建筑安装工程费
 */
@Service
public class MdArchitecturalObjService {

    @Autowired
    private CommonService commonService;

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
}
