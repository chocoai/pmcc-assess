package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.HousePriceIndexDao;
import com.copower.pmcc.assess.dal.entity.HousePriceIndex;
import com.copower.pmcc.assess.dto.input.data.HousePriceIndexDto;
import com.copower.pmcc.assess.dto.output.data.HousePriceIndexVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HousePriceIndexService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private HousePriceIndexDao housePriceIndexDao;


    @Transactional
    public boolean addHousePriceIndex(HousePriceIndexDto housePriceIndexDto) {
        HousePriceIndex housePriceIndex = new HousePriceIndex();
        housePriceIndexDto.setCreator(commonService.thisUserAccount());//设置系统账户
        BeanUtils.copyProperties(housePriceIndexDto, housePriceIndex);
        return housePriceIndexDao.add(housePriceIndex);
    }

    @Transactional
    public boolean removeHousePriceIndeX(Integer id){
        return housePriceIndexDao.remove(id);
    }

    @Transactional(readOnly = true)
    public HousePriceIndex get(Integer id)  {
        return housePriceIndexDao.getById(id);
    }

    @Transactional
    public boolean update(HousePriceIndexDto housePriceIndexDto) {
        HousePriceIndex housePriceIndex = new HousePriceIndex();
        BeanUtils.copyProperties(housePriceIndexDto, housePriceIndex);
        return housePriceIndexDao.updateHousePriceIndex(housePriceIndex);
    }


    @Transactional(readOnly = true)
    public List<HousePriceIndexDto> list(Date start, Date end) {
        Map<String, Object> map = new HashMap<>();
        if (start != null) {
            map.put(HousePriceIndexDto.START_TIME, start);
        }
        if (end != null) map.put(HousePriceIndexDto.END_TIME, end);
        List<HousePriceIndex> housePriceIndices = housePriceIndexDao.list(map);
        List<HousePriceIndexDto> housePriceIndexVoList = new ArrayList<>();
        housePriceIndices.forEach(housePriceIndex -> {
            HousePriceIndexDto indexDto = new HousePriceIndexDto();
            BeanUtils.copyProperties(housePriceIndex, indexDto);  //字段拷贝
            housePriceIndexVoList.add(indexDto);
        });
        return housePriceIndexVoList;
    }

    @Transactional(readOnly = true)
    public  List<HousePriceIndexVo> list(List<HousePriceIndexDto> indexDto){
        List<HousePriceIndexVo> voList = new ArrayList<>();
        indexDto.forEach(housePriceIndexDto -> {
            HousePriceIndexVo  vo = new HousePriceIndexVo();
            BeanUtils.copyProperties(housePriceIndexDto, vo);  //字段拷贝
            vo.setYearMonthString(DateUtils.format(housePriceIndexDto.getYearMonthCalendar(),DateUtils.MONTH_PATTERN));
            voList.add(vo);
        });
        return voList;
    }

    public String change(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        StringBuilder builder = new StringBuilder();
        builder.append(rightNow.get(Calendar.YEAR) + "年");
        builder.append(rightNow.get(Calendar.MINUTE) + "月");
        return builder.toString();
    }

    public Date change(String time) throws Exception {
        if (time == null || time == "") return null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(time);
    }

    public BootstrapTableVo getListVo(Date endTime, Date startTime) throws BusinessException {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<HousePriceIndexVo>  housePriceIndexVoList = list(list(startTime, endTime));
        vo.setRows(CollectionUtils.isEmpty(housePriceIndexVoList) ? new ArrayList<HousePriceIndexVo>() : housePriceIndexVoList);
        vo.setTotal(page.getTotal());
        return vo;
    }
}
