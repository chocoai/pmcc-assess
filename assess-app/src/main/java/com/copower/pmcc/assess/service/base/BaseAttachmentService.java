package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.BaseAttachmentKeepDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.BaseAttachmentKeep;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.AttachmentDto;
import com.copower.pmcc.erp.api.dto.model.AttachmentVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.*;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/10
 * @time: 17:04
 */
@Service
public class BaseAttachmentService {
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private BaseAttachmentKeepDao cmsBaseAttachmentKeepDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private ErpRpcUserService erpRpcUserService;

    private final static String KEEP_UPLOAD_PATH = "Keep";//归档文件存放目录
    private final static String TEMP_UPLOAD_PATH = "Temp";//临时文件存放目录

    public static String getTempUploadPath() {
        return TEMP_UPLOAD_PATH;
    }

    /**
     * 创建不重复的文件名
     *
     * @param suffix 文件的后缀名 如docx jpg
     * @return
     */
    public String createNoRepeatFileName(String suffix) {
        return UUID.randomUUID().toString().replace("-", "") + DateUtils.formatNowToYMDHMS() + "." + suffix.replaceAll("^\\.", "");
    }

    /**
     * 创建文件存放目录
     *
     * @param params
     * @return
     */
    public String createBasePath(String... params) {
        if (params == null || params.length == 0)
            return "default";
        String filePath = servletContext.getRealPath("/") + File.separator + applicationConstant.getAppKey();
        for (String param : params) {
            filePath += File.separator + StringUtils.defaultIfBlank(param, "default");
        }
        //清除当天以外的临时文件
        FileUtils.folderMake(filePath);
        return filePath;
    }

    /**
     * 创建FTP文件存放目录
     *
     * @param params
     * @return
     */
    public String createFTPBasePath(String... params) {
        if (params == null || params.length == 0)
            return applicationConstant.getAppKey();
        String filePath = applicationConstant.getAppKey();
        for (String param : params) {
            filePath += "/" + StringUtils.defaultIfBlank(param, "default");
        }
        return filePath;
    }

    /**
     * 保存上传的附件信息
     *
     * @param attachmentDto 附件相关说明信息
     * @param multipartFile 文件信息
     * @return
     * @throws IOException
     */
    public Integer uploadFileToServer(AttachmentDto attachmentDto, MultipartFile multipartFile) throws BusinessException {
        List<String> strings = FormatUtils.transformString2List(multipartFile.getOriginalFilename(), ".");
        String extension = strings.get(strings.size() - 1);//取得扩展名
        strings.remove(extension);
        String newFilesName = createNoRepeatFileName(extension);
        String filePath = createFTPBasePath(FormatUtils.underlineToCamel(attachmentDto.getTableName(), false), DateUtils.formatDate(new Date(), "yyyy-MM"), processControllerComponent.getThisUser(),
        attachmentDto.getReActivityName(), DateUtils.formatNowToYMD());
        try {
            ftpUtilsExtense.uploadFilesToFTP(filePath, multipartFile.getInputStream(), newFilesName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //保存数据信息
        String currUser = processControllerComponent.getThisUser();
        BaseAttachment sysAttachment = new BaseAttachment();
        BeanUtils.copyProperties(attachmentDto, sysAttachment);
        sysAttachment.setFtpFilesName(newFilesName);
        sysAttachment.setFileExtension(extension);
        sysAttachment.setFilePath(filePath);
        sysAttachment.setFileName(StringUtils.defaultIfBlank(attachmentDto.getFileName(), multipartFile.getOriginalFilename()));
        sysAttachment.setFileSize(FileUtils.getSize(multipartFile.getSize()));
        sysAttachment.setCreater(currUser);
        sysAttachment.setModifier(currUser);
        Integer integer = baseAttachmentDao.addAttachment(sysAttachment);
        return integer;
    }

    /**
     * 根据条件取得服务器相应在附件表中数据信息
     *
     * @param attachmentDto
     * @return
     * @throws Exception
     */
    public List<AttachmentVo> getFilesFromServer(AttachmentDto attachmentDto) throws Exception {
        BaseAttachment sysAttachment = new BaseAttachment();
        try {
            BeanUtils.copyProperties(attachmentDto, sysAttachment, "tableId");
        } catch (Exception e) {
            throw new BusinessException(HttpReturnEnum.COPYFAIL.getName());
        }
        sysAttachment.setTableId(attachmentDto.getTableId());
        List<AttachmentVo> attachmentVos = getAttachmentVos(sysAttachment);
        return attachmentVos;
    }

    public List<AttachmentVo> getFilesFromServer(String ids) throws Exception {
        if (StringUtils.isBlank(ids))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        List<AttachmentVo> attachmentVos = Lists.newArrayList();
        List<BaseAttachment> attachments = baseAttachmentDao.getAttachmentById(FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids)));
        if (CollectionUtils.isNotEmpty(attachments)) {
            getVoList(attachments, attachmentVos);
        }

        return attachmentVos;
    }

    public List<BaseAttachment> getAttachmentListByTableName(String tableName, List<Integer> integers) {
        return baseAttachmentDao.getAttachmentListByTableName(tableName, integers);
    }

    /**
     * 查询满足条件附件的记录
     *
     * @param sysAttachment
     * @return
     */
    public List<AttachmentVo> getAttachmentVos(BaseAttachment sysAttachment) {
        List<BaseAttachment> attachmentList = baseAttachmentDao.getAttachmentList(sysAttachment);
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            //将查询结果的数据进行格式化成统一页面使用的数据

            List<AttachmentVo> list = Lists.newArrayList();
            getVoList(attachmentList, list);
            return list;
        }
        return null;
    }

    private void getVoList(List<BaseAttachment> attachmentList, List<AttachmentVo> list) {
        for (BaseAttachment sysAttachment1 : attachmentList) {
            AttachmentVo attachmentVo = new AttachmentVo();
            attachmentVo.setFileName(sysAttachment1.getFileName() + "(" + sysAttachment1.getFileSize() + ")");
            attachmentVo.setId(sysAttachment1.getId());
            attachmentVo.setHasKeep(cmsBaseAttachmentKeepDao.getCount(sysAttachment1.getId()) > 0);
            attachmentVo.setFileExtension(sysAttachment1.getFileExtension());
            list.add(attachmentVo);
        }
    }

    public List<BaseAttachment> getAttachmentList(BaseAttachment sysAttachment) {
        return baseAttachmentDao.getAttachmentList(sysAttachment);
    }

    public BaseAttachment getBaseAttachment(Integer attachmentId) {
        return baseAttachmentDao.getAttachmentById(attachmentId);
    }

    /**
     * 查询满足条件附件的记录
     *
     * @param attachmentDto
     * @return
     */
    public BootstrapTableVo getAttachmentList(AttachmentDto attachmentDto) throws BusinessException {
        BaseAttachment sysAttachment = new BaseAttachment();
        try {
            BeanUtilsBean.getInstance().copyProperties(sysAttachment, attachmentDto);//将页面查询条件复制到后台查询条件类中
        } catch (Exception e) {
            throw new BusinessException(HttpReturnEnum.COPYFAIL.getName());
        }
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseAttachment> attachmentList = baseAttachmentDao.getAttachmentList(attachmentDto.getProcessInsId(), requestBaseParam.getSearch());
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(attachmentList) ? new ArrayList<BaseAttachment>() : attachmentList);
        return bootstrapTableVo;
    }

    /**
     * 根据附件编号删除相应的附件信息
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteFileToServer(Integer id) throws Exception {
        BaseAttachment attachment = baseAttachmentDao.getAttachmentById(id);
        if (attachment == null) {
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        }
        ftpUtilsExtense.deleteFilesFromFTP(attachment.getFtpFilesName(), attachment.getFilePath());
        if (!baseAttachmentDao.deleteAttachmentById(id)) {//删除数据库中记录
            throw new BusinessException(HttpReturnEnum.DELETEFAIL.getName());
        }
    }

    /**
     * 根据附件编号删除相应的附件信息不包含原文件
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    public void deleteDataToServer(Integer id) throws Exception {
        BaseAttachment attachmentById = baseAttachmentDao.getAttachmentById(id);
        if (attachmentById == null) {
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        }
        if (!baseAttachmentDao.deleteAttachmentById(id)) {//删除数据库中记录
            throw new BusinessException(HttpReturnEnum.DELETEFAIL.getName());
        }
    }

    /**
     * 附件下载
     *
     * @param id
     * @param response
     * @throws Exception
     */
    public void downloadFileFromServer(Integer id, HttpServletResponse response) throws Exception {
        BaseAttachment attachment = baseAttachmentDao.getAttachmentById(id);
        if (attachment == null) {
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        }
        try {
            //下载的文件必须包含文件名，否则在线编辑无法保存
            String fileName = FileUtils.joinFileName(attachment.getFileName(), attachment.getFileExtension());
            ftpUtilsExtense.downloadFilesFromFTP(fileName, attachment.getFtpFilesName(), attachment.getFilePath(), response);
        } catch (BusinessException e) {

        }
    }

    /**
     * 附件下载,下载存档附件
     *
     * @param id
     * @param response
     * @throws Exception
     */
    public void downloadKeepFileFromServer(Integer id, HttpServletResponse response) throws Exception {
        BaseAttachmentKeep sysAttachmentKeep = cmsBaseAttachmentKeepDao.getSingleObject(id);
        if (sysAttachmentKeep == null) {
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        }
        //下载的文件必须包含文件名，否则在线编辑无法保存
        String fileName = FileUtils.joinFileName(sysAttachmentKeep.getFileName(), sysAttachmentKeep.getFileExtension());
        ftpUtilsExtense.downloadFilesFromFTP(fileName, sysAttachmentKeep.getFtpFilesName(), sysAttachmentKeep.getFilePath(), response);
    }

    /**
     * 获取附件历史存档数据列表
     *
     * @param attachmentId
     * @return
     */
    public BootstrapTableVo getAttachmentKeepList(Integer attachmentId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseAttachmentKeep> list = Lists.newArrayList();
        BaseAttachment sysAttachment = baseAttachmentDao.getAttachmentById(attachmentId);
        BaseAttachmentKeep sysAttachmentKeep = new BaseAttachmentKeep();
        sysAttachmentKeep.setFileName("【当前版本】" + sysAttachment.getFileName() + "." + sysAttachment.getFileExtension() + "(" + sysAttachment.getFileSize() + ")");
        sysAttachmentKeep.setId(sysAttachment.getId());
        sysAttachmentKeep.setCreated(sysAttachment.getCreated());
        if (StringUtils.isNotBlank(sysAttachment.getModifier())) {
            SysUserDto sysUserDto = erpRpcUserService.getSysUser(sysAttachment.getModifier());
            if (sysUserDto != null) {
                sysAttachmentKeep.setOwner(sysUserDto.getUserName());
            }
        }
        list.add(sysAttachmentKeep);
        List<BaseAttachmentKeep> keepList = cmsBaseAttachmentKeepDao.getListObject(attachmentId);

        if (CollectionUtils.isNotEmpty(keepList)) {
            for (BaseAttachmentKeep item : keepList) {
                sysAttachmentKeep = new BaseAttachmentKeep();
                sysAttachmentKeep.setFileName(item.getFileName() + "(" + item.getFileSize() + ")");
                sysAttachmentKeep.setId(item.getId());
                sysAttachmentKeep.setCreated(item.getCreated());
                if (StringUtils.isNotBlank(item.getOwner())) {
                    SysUserDto sysUserDto = erpRpcUserService.getSysUser(item.getOwner());
                    if (sysUserDto != null) {
                        sysAttachmentKeep.setOwner(sysUserDto.getUserName());
                    }
                }
                list.add(sysAttachmentKeep);
            }
        }
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(list == null ? new ArrayList<BaseAttachmentKeep>() : list);
        return bootstrapTableVo;
    }

    /**
     * 上传裁剪后的图片
     *
     * @param attachmentDto
     * @return
     * @throws Exception
     */
    public Integer uploadBase64Image(AttachmentDto attachmentDto) throws Exception {
        String filePath = "";
        List<String> strings = FormatUtils.transformString2List(attachmentDto.getFileName(), ".");
        String extension = StringUtils.defaultIfBlank(strings.get(strings.size() - 1), "jpg");//取得扩展名
        filePath = createFTPBasePath(FormatUtils.underlineToCamel(attachmentDto.getTableName(), false), DateUtils.formatDate(new Date(), "yyyy-MM"), String.valueOf(attachmentDto.getTableId()),
                attachmentDto.getReActivityName(), DateUtils.formatNowToYMD());
        byte[] bytes = ImageUtils.base64ToByte(attachmentDto.getBase64Data());

        String newFileName = createNoRepeatFileName(extension);
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);

        ftpUtilsExtense.uploadFilesToFTP(filePath, stream, newFileName);
        //保存数据信息
        String currUser = processControllerComponent.getThisUser();
        BaseAttachment sysAttachment = new BaseAttachment();
        sysAttachment.setFileExtension(extension);
        sysAttachment.setFilePath(filePath);
        sysAttachment.setFtpFilesName(newFileName);
        sysAttachment.setFileName(attachmentDto.getFileName());
        sysAttachment.setFileSize(FileUtils.getSize(bytes.length));
        sysAttachment.setTableName(attachmentDto.getTableName());
        sysAttachment.setTableId(attachmentDto.getTableId());
        sysAttachment.setProcessInsId(attachmentDto.getProcessInsId());
        sysAttachment.setReActivityName(attachmentDto.getReActivityName());
        sysAttachment.setReName(attachmentDto.getReName());
        sysAttachment.setProjectId(attachmentDto.getProjectId());
        sysAttachment.setFieldsName(attachmentDto.getFieldsName());//如果是业务数据，则存相应的字段名称，如果为日志文件，则存日志表的ID
        sysAttachment.setCreater(currUser);
        Integer integer = baseAttachmentDao.addAttachment(sysAttachment);
        return integer;
    }

    /**
     * 获取图片查看地址
     *
     * @param id
     * @return
     */
    public String getViewImageUrl(Integer id) {
        //复制一份附件到临时目录，并返还临时文件的查看地址
        BaseAttachment sysAttachment = baseAttachmentDao.getAttachmentById(id);
        String strDayTempDirName = DateUtils.formatDate(new Date(), "yyyyMMdd");
        String basePath = "/temporary";
        String baseRealPath = RequestContext.getRequestBaseParam().getRequest().getSession().getServletContext().getRealPath(basePath);
        String newDirPath = baseRealPath + File.separator + strDayTempDirName;
        FileUtils.deleteDir(baseRealPath, Lists.newArrayList(strDayTempDirName));
        FileUtils.folderMake(newDirPath);
        String newFileName = sysAttachment.getId() + "." + sysAttachment.getFileExtension();
        String viewUrl = basePath + "/" + strDayTempDirName + "/" + newFileName;
        String fullPath = newDirPath + File.separator + newFileName;
        if (!FileUtils.checkFileExists(new File(fullPath))) {
            try {
                ftpUtilsExtense.downloadFileToLocal(sysAttachment.getFtpFilesName(), sysAttachment.getFilePath(), newFileName, newDirPath);
            } catch (Exception e) {
                //拷贝失败
            }
        }
        return viewUrl;
    }


    /**
     * 原文件存档并更新文件编辑者
     *
     * @param attachmentId
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveKeepFile(Integer attachmentId) throws Exception {
        BaseAttachment sysAttachment = baseAttachmentDao.getAttachmentById(attachmentId);
        if (sysAttachment == null)
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        Date date = new Date();
        String strYear = String.valueOf(DateUtils.getYear(date));
        String strMonth = String.valueOf(DateUtils.getMonth(date));
        String strDay = String.valueOf(DateUtils.getMonthDay(date));
        String targetPath = createFTPBasePath(KEEP_UPLOAD_PATH, strYear, strMonth, strDay, processControllerComponent.getThisUser());
        String targetFileName = createNoRepeatFileName(sysAttachment.getFileExtension());
        ftpUtilsExtense.copyFile(sysAttachment.getFtpFilesName(), sysAttachment.getFilePath(), targetFileName, targetPath);
        BaseAttachmentKeep sysAttachmentKeep = new BaseAttachmentKeep();
        sysAttachmentKeep.setAttachmentId(sysAttachment.getId());
        sysAttachmentKeep.setOwner(sysAttachment.getModifier());//记录的该版本的拥有者
        sysAttachmentKeep.setFileExtension(sysAttachment.getFileExtension());
        sysAttachmentKeep.setFileName(sysAttachment.getFileName());
        sysAttachmentKeep.setFilePath(targetPath);
        sysAttachmentKeep.setFtpFilesName(targetFileName);
        sysAttachmentKeep.setFileSize(sysAttachment.getFileSize());
        cmsBaseAttachmentKeepDao.addObject(sysAttachmentKeep);

        sysAttachment.setModifier(processControllerComponent.getThisUser());
        baseAttachmentDao.updateAttachment(sysAttachment);
    }

    /**
     * 更新附件大小信息
     *
     * @param attachmentId
     * @param size
     */
    public void updateFileSize(Integer attachmentId, long size) {
        BaseAttachment baseAttachment = baseAttachmentDao.getAttachmentById(attachmentId);
        if (baseAttachment != null) {
            baseAttachment.setFileSize(FileUtils.getSize(size));
            baseAttachmentDao.updateAttachment(baseAttachment);
        }
    }

    private List<BaseAttachment> getBaseAttachments(Integer id, String fieldsName) {
        BaseAttachment bidBaseAttachment = new BaseAttachment();
        bidBaseAttachment.setTableName("tb_project_phase");
        bidBaseAttachment.setFieldsName(fieldsName);
        bidBaseAttachment.setTableId(id);
        return baseAttachmentDao.getAttachmentList(bidBaseAttachment);
    }

    /**
     * 获取工作事项的工作模板
     *
     * @param id
     * @return
     */
    public List<BaseAttachment> getProjectPhaseWorkTemplate(Integer id) {
        try {
            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_ATTACHMENT_PHASE_TEMPLATE_WORK, id.toString());
            return LangUtils.listCache(costsKeyPrefix, id, BaseAttachment.class, input -> {
                return getBaseAttachments(input, "workTemp");
            });
        } catch (Exception e) {
            return getBaseAttachments(id, "workTemp");
        }
    }

    /**
     * 获取
     *
     * @param id
     * @return
     */
    public List<BaseAttachment> getProjectPhaseProcessTemplate(Integer id) {
        try {
            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_ATTACHMENT_PHASE_TEMPLATE_PROCESS, id.toString());
            return LangUtils.listCache(costsKeyPrefix, id, BaseAttachment.class, input -> {
                return getBaseAttachments(input, "processTemp");
            });
        } catch (Exception e) {
            return getBaseAttachments(id, "processTemp");
        }
    }
}
