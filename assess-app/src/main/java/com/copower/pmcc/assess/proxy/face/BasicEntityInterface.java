package com.copower.pmcc.assess.proxy.face;

/**
 * Created by wangpc on 2020/2/14.
 */
public interface BasicEntityInterface {
    public Object getBasicEntityById(Integer id);
    public Object saveAndUpdateBasicEntity(Object object,Boolean updateNull);
    public void saveBasicEntityByFormData(String formData, Integer basicApplyBatchId, Integer planDetailsId);

}
