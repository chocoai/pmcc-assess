package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEquipmentInstall;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEquipmentInstallExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareBuildEquipmentInstallMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 11:28
 * @Description:
 */
@Repository
public class DeclareBuildEquipmentInstallDao {
    @Autowired
    private DeclareBuildEquipmentInstallMapper declareBuildEquipmentInstallMapper;


    public Integer addDeclareBuildEquipmentInstall(DeclareBuildEquipmentInstall declareBuildEquipmentInstall){
        declareBuildEquipmentInstallMapper.insertSelective(declareBuildEquipmentInstall);
        return declareBuildEquipmentInstall.getId();
    }

    public DeclareBuildEquipmentInstall getDeclareBuildEquipmentInstallById(Integer id){
        return declareBuildEquipmentInstallMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareBuildEquipmentInstall(DeclareBuildEquipmentInstall declareBuildEquipmentInstall){
        return declareBuildEquipmentInstallMapper.updateByPrimaryKeySelective(declareBuildEquipmentInstall)==1;
    }

    public boolean updateDeclareBuildEquipmentInstall(DeclareBuildEquipmentInstall declareBuildEquipmentInstall,boolean updateNull){

        if (updateNull){
            DeclareBuildEquipmentInstall target = getDeclareBuildEquipmentInstallById(declareBuildEquipmentInstall.getId()) ;
            if (declareBuildEquipmentInstall.getPlanDetailsId() == null){
                declareBuildEquipmentInstall.setPlanDetailsId(target.getPlanDetailsId());
            }
            if (StringUtils.isEmpty(declareBuildEquipmentInstall.getEnable())){
                declareBuildEquipmentInstall.setEnable(target.getEnable());
            }
            if (declareBuildEquipmentInstall.getBisRecord() == null){
                declareBuildEquipmentInstall.setBisRecord(target.getBisRecord());
            }
            if (declareBuildEquipmentInstall.getPid() == null){
                declareBuildEquipmentInstall.setPid(target.getPid());
            }
            if (StringUtils.isEmpty(declareBuildEquipmentInstall.getCreator())){
                declareBuildEquipmentInstall.setCreator(target.getCreator());
            }
            if (declareBuildEquipmentInstall.getGmtCreated() == null){
                declareBuildEquipmentInstall.setGmtCreated(target.getGmtCreated());
            }
        }
        return updateNull ? declareBuildEquipmentInstallMapper.updateByPrimaryKey(declareBuildEquipmentInstall) == 1 : declareBuildEquipmentInstallMapper.updateByPrimaryKeySelective(declareBuildEquipmentInstall) == 1;
    }

    public void removeDeclareBuildEquipmentInstall(DeclareBuildEquipmentInstall declareBuildEquipmentInstall){
        DeclareBuildEquipmentInstallExample example = new DeclareBuildEquipmentInstallExample();
        MybatisUtils.convertObj2Example(declareBuildEquipmentInstall, example);
        declareBuildEquipmentInstallMapper.deleteByExample(example);
    }

    public List<DeclareBuildEquipmentInstall> getDeclareBuildEquipmentInstallList(DeclareBuildEquipmentInstall declareBuildEquipmentInstall){
        DeclareBuildEquipmentInstallExample example = new DeclareBuildEquipmentInstallExample();
        MybatisUtils.convertObj2Example(declareBuildEquipmentInstall, example);
        return declareBuildEquipmentInstallMapper.selectByExample(example);
    }
}
