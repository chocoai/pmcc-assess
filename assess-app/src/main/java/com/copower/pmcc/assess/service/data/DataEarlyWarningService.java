package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.DataEarlyWarningDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.DataEarlyWarning;
import com.copower.pmcc.assess.dto.output.data.DataEarlyWarningVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "dataEarlyWarningService")
public class DataEarlyWarningService {

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private DataEarlyWarningDao dataEarlyWarningDao;

    @Autowired
    private BaseDataDicService baseDataDicService;


    /**
     * 修改一条预警设置信息
     * @param earlyWarning 预警设置entity
     * @return true or false
     */
    public boolean editEarlyWarning(DataEarlyWarning earlyWarning) throws Exception{
        boolean flag = false;
        earlyWarning.setCreator(processControllerComponent.getThisUser());
        earlyWarning.setGmtModified(new Date());
        dataEarlyWarningDao.editEarlyWarning(earlyWarning);
        return flag;
    }

    /**
     * 新增一条预警设置信息
     * @param earlyWarning 预警设置entity
     * @return true or false
     */
    public boolean addEarlyWarning(DataEarlyWarning earlyWarning) throws Exception{
        boolean flag = false;
        earlyWarning.setCreator(processControllerComponent.getThisUser());
        earlyWarning.setGmtCreated(new Date());
        flag = dataEarlyWarningDao.addEarlyWarning(earlyWarning);
        return flag;
    }

    /**
     * 获取预警设置信息列表
     * @param keyWord 查询关键字
     * @return 分页列表信息
     */
    public BootstrapTableVo getEarlyWarningForList(String keyWord) throws Exception{
        //Bootstrap表格对象
        BootstrapTableVo vo = new BootstrapTableVo();
        //分页参数对象
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(),requestBaseParam.getLimit());
        //查询数据
        List<DataEarlyWarning> earlyWarningList = null;
        List<DataEarlyWarningVo> earlyWarningVoList = null;
        earlyWarningList = dataEarlyWarningDao.getEarlyWarningList(keyWord);
        earlyWarningVoList = getVoList(earlyWarningList);

        vo.setTotal(page.getTotal());
        vo.setRows(earlyWarningVoList);
        return vo;
    }

    /**
     * 讲字典数据id转换成对应的文本
     * @param list 数据列表
     * @return entity派生类集合
     */
    private List<DataEarlyWarningVo> getVoList(List<DataEarlyWarning> list) {
        if(CollectionUtils.isNotEmpty(list)){
            return LangUtils.transform(list,p ->{
                DataEarlyWarningVo vo = new DataEarlyWarningVo();
                BeanUtils.copyProperties(p,vo);
                if(p.getEntrustPurpose() != null){
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getEntrustPurpose());
                    if(baseDataDic != null){
                        vo.setEntrustPurposeName(baseDataDic.getName());
                    }
                }if(p.getType() != null){
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getType());
                    if(baseDataDic != null){
                        vo.setTypeName(baseDataDic.getName());
                    }
                }if(p.getMode() != null){
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getMode());
                    if(baseDataDic != null){
                        vo.setModeName(baseDataDic.getName());
                    }
                }
                return vo;
            });

        }else{
            return null;
        }
    }

    /**
     * 删除一条预警设置信息
     * @param id id
     * @return true or false
     */
    public boolean deleteEarlyWarning(Integer id) throws Exception{
        boolean flag = false;
        flag = dataEarlyWarningDao.deleteEarlyWarning(id);
        return flag;
    }
}
