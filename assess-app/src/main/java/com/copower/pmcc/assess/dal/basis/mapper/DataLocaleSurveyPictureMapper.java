package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurveyPicture;
import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurveyPictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataLocaleSurveyPictureMapper {
    int countByExample(DataLocaleSurveyPictureExample example);

    int deleteByExample(DataLocaleSurveyPictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataLocaleSurveyPicture record);

    int insertSelective(DataLocaleSurveyPicture record);

    List<DataLocaleSurveyPicture> selectByExample(DataLocaleSurveyPictureExample example);

    DataLocaleSurveyPicture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataLocaleSurveyPicture record, @Param("example") DataLocaleSurveyPictureExample example);

    int updateByExample(@Param("record") DataLocaleSurveyPicture record, @Param("example") DataLocaleSurveyPictureExample example);

    int updateByPrimaryKeySelective(DataLocaleSurveyPicture record);

    int updateByPrimaryKey(DataLocaleSurveyPicture record);
}