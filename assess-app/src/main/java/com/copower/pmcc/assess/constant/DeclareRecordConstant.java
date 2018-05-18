package com.copower.pmcc.assess.constant;

/**
 * Created by 13426 on 2018/5/15.
 */
public class DeclareRecordConstant {
    //相同省 市
    public static String SQLSELECT2 = "select * from tb_declare_record where province in (select province from tb_declare_record group by province having count(1) > 1)\n" +
            "      and city in (select city from tb_declare_record group by city having count(1) > 1)";

    //相同省 市 县
    public static String SQLSELECT1 = "select * from tb_declare_record where province in (select province from tb_declare_record group by province having count(1) > 1)\n" +
            "      and city in (select city from tb_declare_record group by city having count(1) > 1)\n" +
            "      and district in (select district from tb_declare_record group by district having count(1) > 1)\n";

    //相同省
    public static String SQLSELECT3 = "select * from tb_declare_record where province in (select province from tb_declare_record group by province having count(1) > 1)";

    //所有数据
    public static String SQLSELECT4 = "select * from tb_declare_record where project_id=?";


}
