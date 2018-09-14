package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataBlockDao;
import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.dto.output.data.DataBlockVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 10:01
 * @Description:基础版块维护
 */
@Service
public class DataBlockService {
    @Autowired
    private DataBlockDao dataBlockDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDataBlock(DataBlock dataBlock) {
        if (dataBlock == null) {
            try {
                logger.error("null point");
                throw new Exception("exception error!");
            } catch (Exception e1) {

            }
        }
        if (dataBlock.getId() == null || dataBlock.getId().intValue() == 0) {
            dataBlock.setCreator(commonService.thisUserAccount());
            try {
                return dataBlockDao.addDataBlock(dataBlock);
            } catch (Exception e1) {
                logger.error(e1.getMessage(),e1);
                return null;
            }
        } else {
            dataBlockDao.updateDataBlock(dataBlock);
            return null;
        }
    }

    public DataBlock getDataBlockById(Integer id) {
        if (id == null) {
            try {
                logger.error("null point");
                throw new Exception("exception error!");
            } catch (Exception e1) {

            }
        }
        return dataBlockDao.getDataBlockById(id);
    }

    public BootstrapTableVo getDataBlockListVos(DataBlock dataBlock) {
        if (dataBlock == null) {
            try {
                logger.error("null point");
                throw new Exception("exception error!");
            } catch (Exception e1) {

            }
        }
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBlockVo> vos = dataBlockVos(dataBlock);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataBlockVo> dataBlockVos(DataBlock dataBlock) {
        if (dataBlock == null) {
            try {
                logger.error("null point");
                throw new Exception("exception error!");
            } catch (Exception e1) {

            }
        }
        List<DataBlock> dataBlocks = dataBlockDao.getDataBlockList(dataBlock);
        List<DataBlockVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataBlocks)) {
            for (DataBlock landLevel : dataBlocks) {
                vos.add(getDataBlockVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataBlock(DataBlock dataBlock) {
        if (dataBlock == null) {
            try {
                logger.error("null point");
                throw new Exception("exception error!");
            } catch (Exception e1) {

            }
        }
        try {
            dataBlockDao.removeDataBlock(dataBlock);
        } catch (Exception e1) {
            try {
                throw new Exception();
            } catch (Exception e11) {

            }
        }
    }

    public DataBlockVo getDataBlockVo(DataBlock dataBlock) {
        if (dataBlock == null) {
            try {
                logger.error("null point");
                throw new Exception("exception error!");
            } catch (Exception e1) {

            }
        }
        DataBlockVo vo = new DataBlockVo();
        BeanUtils.copyProperties(dataBlock, vo);
        if (StringUtils.isNotBlank(dataBlock.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataBlock.getProvince()));//省
        }
        if (StringUtils.isNotBlank(dataBlock.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataBlock.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(dataBlock.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataBlock.getDistrict()));//县
        }
        return vo;
    }
}
