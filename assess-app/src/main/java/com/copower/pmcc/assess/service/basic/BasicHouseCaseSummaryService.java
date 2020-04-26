package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseCaseSummaryDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseCaseSummaryVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2018-12-3.
 * 楼盘
 */
@Service
public class BasicHouseCaseSummaryService {
    @Autowired
    private BasicHouseCaseSummaryDao basicHouseCaseSummaryDao;
    @Lazy
    @Autowired
    private CustomCaseMapper customCaseMapper;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ErpAreaService erpAreaService;

    public BasicHouseCaseSummary getBasicHouseCaseSummaryById(Integer id) {
        return basicHouseCaseSummaryDao.getBasicHouseCaseSummaryById(id);
    }

    public BootstrapTableVo getBootstrapTableVo(BigDecimal areaStart, BigDecimal areaEnd, Date tradingTimeStart, Date tradingTimeEnd, BasicHouseCaseSummary BasicHouseCaseSummary) throws Exception{
        return getBootstrapTableVo(null,null,areaStart, areaEnd, tradingTimeStart, tradingTimeEnd, BasicHouseCaseSummary) ;
    }

    public BootstrapTableVo getBootstrapTableVo( Date endDate, Date startDate,BigDecimal areaStart, BigDecimal areaEnd, Date tradingTimeStart, Date tradingTimeEnd, BasicHouseCaseSummary BasicHouseCaseSummary)  {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseCaseSummary> list = customCaseMapper.findCaseBaseHouseList( endDate,startDate,areaStart, areaEnd, tradingTimeStart, tradingTimeEnd, BasicHouseCaseSummary);
        List<BasicHouseCaseSummaryVo> vos = LangUtils.transform(list, o -> getBasicHouseCaseSummaryVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public BasicHouseCaseSummaryVo getBasicHouseCaseSummaryVo(BasicHouseCaseSummary BasicHouseCaseSummary) {
        if (BasicHouseCaseSummary == null) return null;
        BasicHouseCaseSummaryVo BasicHouseCaseSummaryVo = new BasicHouseCaseSummaryVo();
        BeanUtils.copyProperties(BasicHouseCaseSummary, BasicHouseCaseSummaryVo);
        if (org.apache.commons.lang.StringUtils.isNotEmpty(BasicHouseCaseSummary.getProvince())) {
            BasicHouseCaseSummaryVo.setProvinceName(erpAreaService.getSysAreaName(BasicHouseCaseSummary.getProvince()));
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(BasicHouseCaseSummary.getCity())) {
            BasicHouseCaseSummaryVo.setCityName(erpAreaService.getSysAreaName(BasicHouseCaseSummary.getCity()));
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(BasicHouseCaseSummary.getDistrict())) {
            BasicHouseCaseSummaryVo.setDistrictName(erpAreaService.getSysAreaName(BasicHouseCaseSummary.getDistrict()));
        }
        if (BasicHouseCaseSummary.getPracticalUse() != null)
            BasicHouseCaseSummaryVo.setPracticalUseName(baseDataDicService.getNameById(BasicHouseCaseSummary.getPracticalUse()));
        if (BasicHouseCaseSummary.getTradingType() != null)
            BasicHouseCaseSummaryVo.setTradingTypeName(baseDataDicService.getNameById(BasicHouseCaseSummary.getTradingType()));
        if (BasicHouseCaseSummary.getDealType() != null)
            BasicHouseCaseSummaryVo.setDealTypeName(baseDataDicService.getNameById(BasicHouseCaseSummary.getDealType()));
        if (StringUtils.isNotEmpty(BasicHouseCaseSummary.getApprover()))
            BasicHouseCaseSummaryVo.setApproverName(publicService.getUserNameByAccount(BasicHouseCaseSummary.getApprover()));
        if (StringUtils.isNotEmpty(BasicHouseCaseSummary.getCreator()))
            BasicHouseCaseSummaryVo.setCreatorName(publicService.getUserNameByAccount(BasicHouseCaseSummary.getCreator()));
        return BasicHouseCaseSummaryVo;
    }

    /**
     * 新增数据
     *
     * @param BasicHouseCaseSummary
     */
    public void addBaseHouseSummary(BasicHouseCaseSummary BasicHouseCaseSummary) {
        basicHouseCaseSummaryDao.addBaseHouseSummary(BasicHouseCaseSummary);
    }

    /**
     * 更新数据
     *
     * @param BasicHouseCaseSummary
     */
    public void updateBaseHouseSummary(BasicHouseCaseSummary BasicHouseCaseSummary) {
        basicHouseCaseSummaryDao.updateBaseHouseSummary(BasicHouseCaseSummary);
    }

    /**
     * 获取数据列表
     *
     * @param BasicHouseCaseSummary
     * @return
     */
    public List<BasicHouseCaseSummary> getBaseHouseSummaryList(BasicHouseCaseSummary BasicHouseCaseSummary) {
        return basicHouseCaseSummaryDao.getBaseHouseSummaryList(BasicHouseCaseSummary);
    }

    /**
     * 删除数据by caseHouseId
     *
     * @param id
     */
    public void deleteBaseHouseSummaryById(Integer id) {
        basicHouseCaseSummaryDao.deleteBaseHouseSummaryById(id);
    }

    public Integer getCountByFullName(String fullName) {
        return basicHouseCaseSummaryDao.getCountByFullName(fullName);
    }

    public String reportData() throws Exception {
        return null;
    }

    private String reportHandle(String name,HSSFWorkbook wb) throws Exception {
        OutputStream fileOut = null;
        String path = String.join(File.separator, FileUtils.getTempDirectoryPath(), name) ;
        File file = new File(path);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.close();
        return path ;
    }

}
