package com.copower.pmcc.assess.proxy.face;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/25
 * @time: 16:44
 */
public interface ProjectPlanDetailsDeleteInterface {
    boolean beforeDeleteVerify(Integer projectPlanDetailsId);//删除前验证

    void afterDeleteExecute(Integer projectPlanDetailsId);//删除后执行
}
