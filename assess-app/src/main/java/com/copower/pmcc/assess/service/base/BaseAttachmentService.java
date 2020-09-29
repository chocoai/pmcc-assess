package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.*;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/8/10
 * @time: 17:04
 */
@Service
public class BaseAttachmentService {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    private final Logger logger = LoggerFactory.getLogger(getClass()) ;

    private final static String TEMP_UPLOAD_PATH = "Temp";//临时文件存放目录

    /**
     * 创建不重复的文件名
     *
     * @param suffix 文件的后缀名 如docx jpg
     * @return
     */
    public String createNoRepeatFileName(String suffix) {
        return erpRpcAttachmentService.createNoRepeatFileName(suffix);
    }

    public boolean deleteAttachment(Integer id) {
        return erpRpcAttachmentService.deleteAttachment(id);
    }

    public boolean deleteAttachment(List<Integer> ids) {
        return erpRpcAttachmentService.deleteAttachmentByIds(ids) > 0;
    }

    public void deleteAttachmentByIdList(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> integerList = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isEmpty(integerList)) {
            return;
        }
        deleteAttachment(integerList) ;
    }

    public int deleteAttachmentByDto(SysAttachmentDto sysAttachmentDto) {
        return erpRpcAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
    }

    /**
     * 创建文件存放目录
     *
     * @param params
     * @return
     */
    public String createTempDirPath(String... params) {
        String realPath = servletContext.getRealPath("/");
        realPath = StringUtils.endsWith(realPath, File.separator) ? realPath : realPath + File.separator;
        String filePath = realPath + applicationConstant.getAppKey() + File.separator + TEMP_UPLOAD_PATH;
        //清除今天、昨天以外的临时文件
        FileUtils.deleteDir(filePath, Lists.newArrayList(DateUtils.formatDate(DateUtils.addDay(new Date(), -1), DateUtils.DATE_SHORT_PATTERN), DateUtils.formatNowToYMD()));
        filePath += File.separator + DateUtils.formatNowToYMD() + File.separator + commonService.thisUserAccount();
        for (String param : params) {
            filePath += File.separator + StringUtils.defaultIfBlank(param, "default");
        }
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
        return erpRpcAttachmentService.createFTPPath(applicationConstant.getAppKey(), params);
    }

    public List<SysAttachmentDto> getAttachmentListByTableName(String tableName, List<Integer> integers) {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableName(tableName);
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        return erpRpcAttachmentService.getAttachmentListByIds(integers, sysAttachmentDto);
    }

    /**
     * @param table_id
     * @param fields_name
     * @return
     * @time zch 2018-05-09
     */
    public List<SysAttachmentDto> getByField_tableId(int table_id, String fields_name, String tableName) {
        SysAttachmentDto sysAttachment = new SysAttachmentDto();
        sysAttachment.setTableId(table_id);
        if (org.apache.commons.lang.StringUtils.isNotBlank(fields_name)) {
            sysAttachment.setFieldsName(fields_name);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(tableName)) {
            sysAttachment.setTableName(tableName);
        }
        return getAttachmentList(sysAttachment);
    }


    public List<SysAttachmentDto> getAttachmentList(List<Integer> tableIds, SysAttachmentDto sysAttachmentDto) {
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        return erpRpcAttachmentService.getAttachmentListByTableIds(tableIds, sysAttachmentDto);
    }

    public List<SysAttachmentDto> getAttachmentList(SysAttachmentDto sysAttachmentDto) {
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        return erpRpcAttachmentService.getAttachmentList(sysAttachmentDto);
    }

    public SysAttachmentDto getSysAttachmentDto(Integer attachmentId) {
        return erpRpcAttachmentService.getAttachmentDtoById(attachmentId);
    }

    public void addAttachment(SysAttachmentDto sysAttachmentDto) {
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        sysAttachmentDto.setId(erpRpcAttachmentService.addAttachment(sysAttachmentDto));
    }

    public void updateAttachment(SysAttachmentDto sysAttachment) {
        erpRpcAttachmentService.updateAttachment(sysAttachment);
    }

    /**
     * 更新附件表业务id
     *
     * @param tableName
     * @param tableId
     */
    public void updateTableIdByTableName(String tableName, Integer tableId) {
        SysAttachmentDto queryParam = new SysAttachmentDto();
        queryParam.setTableName(tableName);
        queryParam.setTableId(0);
        queryParam.setCreater(commonService.thisUserAccount());
        queryParam.setAppKey(applicationConstant.getAppKey());
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        erpRpcAttachmentService.updateAttachmentByParam(queryParam, sysAttachmentDto);
    }

    public List<SysAttachmentDto> getApprovalLogList(String processInsId, List<String> taskIds) {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setProcessInsId(processInsId);
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        return erpRpcAttachmentService.getAttachmentListByTaskIds(taskIds, sysAttachmentDto);
    }

    private List<SysAttachmentDto> getSysAttachmentDtos(Integer id, String fieldsName) {
        SysAttachmentDto bidSysAttachmentDto = new SysAttachmentDto();
        bidSysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPhase.class));
        bidSysAttachmentDto.setFieldsName(fieldsName);
        bidSysAttachmentDto.setTableId(id);
        bidSysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        return getAttachmentList(bidSysAttachmentDto);
    }

    /**
     * 获取工作事项的工作模板
     *
     * @param id
     * @return
     */
    public List<SysAttachmentDto> getProjectPhaseWorkTemplate(Integer id) {
        try {
            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_ATTACHMENT_PHASE_TEMPLATE_WORK, id.toString());
            return LangUtils.listCache(costsKeyPrefix, id, SysAttachmentDto.class, input -> {
                return getSysAttachmentDtos(input, "workTemp");
            });
        } catch (Exception e) {
            return getSysAttachmentDtos(id, "workTemp");
        }
    }

    /**
     * 获取
     *
     * @param id
     * @return
     */
    public List<SysAttachmentDto> getProjectPhaseProcessTemplate(Integer id) {
        try {
            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_ATTACHMENT_PHASE_TEMPLATE_PROCESS, id.toString());
            return LangUtils.listCache(costsKeyPrefix, id, SysAttachmentDto.class, input -> {
                return getSysAttachmentDtos(input, "processTemp");
            });
        } catch (Exception e) {
            return getSysAttachmentDtos(id, "processTemp");
        }
    }

    /**
     * 附件信息批量更新
     *
     * @param queryParam
     * @param sysAttachmentDto
     */
    public void updateAttachementByExample(SysAttachmentDto queryParam, SysAttachmentDto sysAttachmentDto) {
        queryParam.setAppKey(applicationConstant.getAppKey());
        erpRpcAttachmentService.updateAttachmentByParam(queryParam, sysAttachmentDto);
    }

    /**
     * 拷贝FTP附件
     *
     * @param attachmentId
     * @param baseAttachment
     * @return
     */
    public SysAttachmentDto copyFtpAttachment(Integer attachmentId, SysAttachmentDto baseAttachment) throws Exception {
        return erpRpcAttachmentService.copyFtpAttachment(attachmentId, baseAttachment);
    }

    /**
     * 拷贝FTP附件
     *
     * @param example
     * @param sysAttachmentDto
     * @return
     */
    public void copyFtpAttachments(SysAttachmentDto example, SysAttachmentDto sysAttachmentDto) throws Exception {
        List<SysAttachmentDto> attachmentList = this.getAttachmentList(example);
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            for (SysAttachmentDto dto : attachmentList) {
                this.copyFtpAttachment(dto.getId(), sysAttachmentDto);
            }
        }
    }

    /**
     * 拷贝FTP附件
     *
     * @param sourceTableId
     * @param targetTableId
     * @return
     */
    public void copyFtpAttachments(String tableName,Integer sourceTableId, Integer targetTableId) throws Exception {
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableName(tableName);
        example.setTableId(sourceTableId);
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(targetTableId);
        copyFtpAttachments(example, attachmentDto);
    }

    /**
     * 获取附件显示html
     *
     * @param baseAttachment
     * @return
     */
    public String getViewHtml(SysAttachmentDto baseAttachment) {
        return erpRpcAttachmentService.getAttachmentViewHtml(baseAttachment);
    }

    /**
     * 获取附件编辑html
     *
     * @param baseAttachment
     * @return
     */
    public String getEditHtml(SysAttachmentDto baseAttachment, Boolean allowSave) {
        return erpRpcAttachmentService.getAttachmentEditHtml(baseAttachment, allowSave);
    }

    /**
     * 下载ftp附件到本地
     *
     * @param attachmentId
     * @return
     * @throws Exception
     */
    public String downloadFtpFileToLocal(Integer attachmentId) throws Exception {
        SysAttachmentDto attachmentDto = erpRpcAttachmentService.getAttachmentDtoById(attachmentId);
        return downloadFtpFileToLocal(attachmentDto);
    }

    public String downloadFtpFileToLocal(SysAttachmentDto attachmentDto) throws Exception {
        if (attachmentDto == null) {
            return null;
        }
        String local = ftpUtilsExtense.downloadFileToLocal(attachmentDto);
        try {
            if (!new File(local).isFile()) {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
        return local;
    }

    public String downloadFtpFileToLocalTimeOut(SysAttachmentDto attachmentDto)throws Exception{
        if (attachmentDto == null) {
            return null;
        }
        final int second = 5;//超过5秒就任务超时
        //发起线程组
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        List<String> stringList = new ArrayList<>(1) ;
        taskExecutor.execute(() -> {
            try {
              String  local = ftpUtilsExtense.downloadFileToLocal(attachmentDto);
              stringList.add(local) ;
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }finally {
                countDownLatch.countDown();
            }
        });
        //能够阻塞线程 直到调用N次end.countDown() 方法才释放线程
        countDownLatch.await(second, TimeUnit.SECONDS);
        if (CollectionUtils.isNotEmpty(stringList)) {
            return stringList.get(0) ;
        }
        return null;
    }

    /**
     * 保存上传文件到临时目录
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String saveUploadFile(MultipartFile multipartFile) throws IOException {
        String localDirPath = createTempDirPath();
        String fileName = createNoRepeatFileName(FileUtils.getExtName(multipartFile.getOriginalFilename(), '.'));
        String fileFullPath = localDirPath + File.separator + fileName;
        InputStream in = multipartFile.getInputStream();
        FileOutputStream out = new FileOutputStream(localDirPath + File.separator + fileName);
        byte buffer[] = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
        return fileFullPath;
    }

    public String importAjaxFile(List<MultipartFile> multipartFileList, String tableName, String tableId, String fieldsName) throws Exception {
        List<String> stringList = new ArrayList<String>(multipartFileList.size());
        if (CollectionUtils.isNotEmpty(multipartFileList)) {
            for (MultipartFile multipartFile : multipartFileList) {
                String id = importAjaxFile(multipartFile, tableName, tableId, fieldsName);
                if (StringUtils.isNotBlank(id)) {
                    stringList.add(id);
                }
            }
        }
        return StringUtils.join(stringList, ",");
    }

    public String importAjaxFile(MultipartFile multipartFile, String tableName, String tableId, String fieldsName) throws Exception {
        SysAttachmentDto target = new SysAttachmentDto();
        target.setTableId(org.apache.commons.lang.StringUtils.isNotBlank(tableId) ? Integer.parseInt(tableId) : 0);
        target.setTableName(tableName);
        target.setFieldsName(fieldsName);
        target.setFileName(multipartFile.getOriginalFilename());
        target.setFileExtension(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        target.setFileSize(FileUtils.getSize(multipartFile.getBytes().length));
        String filePath = this.saveUploadFile(multipartFile);
        return importAjaxFile(filePath, target);
    }

    /**
     * 上传到ftp 形成 附件对象
     *
     * @param filePath
     * @param params
     * @return
     * @throws Exception
     */
    public String importAjaxFile(String filePath, SysAttachmentDto params) throws Exception {
       return importAjaxFileHandle(filePath, params).getId().toString();
    }

    public SysAttachmentDto importAjaxFileHandle(String filePath, SysAttachmentDto params) throws Exception {
        File file = new File(filePath);
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(params.getTableId() == null ? 0 : params.getTableId());
        sysAttachmentDto.setTableName(params.getTableName());
        sysAttachmentDto.setFieldsName(params.getFieldsName());
        sysAttachmentDto.setAppKey(applicationConstant.getAppKey());

        if (StringUtils.isNotBlank(params.getFileName())) {
            sysAttachmentDto.setFileName(params.getFileName());
        } else {
            sysAttachmentDto.setFileName(file.getName());
        }
        if (StringUtils.isNotBlank(params.getFileExtension())) {
            sysAttachmentDto.setFileExtension(params.getFileExtension());
        } else {
            sysAttachmentDto.setFileExtension(FilenameUtils.getExtension(file.getName()));
        }
        if (StringUtils.isNotBlank(params.getFileSize())) {
            sysAttachmentDto.setFileSize(params.getFileSize());
        } else {
            sysAttachmentDto.setFileSize(String.valueOf(org.apache.commons.io.FileUtils.sizeOf(file)));
        }
        if (StringUtils.isNotBlank(params.getCreater())) {
            sysAttachmentDto.setCreater(params.getCreater());
        } else {
            sysAttachmentDto.setCreater(commonService.thisUserAccount());
        }
        if (params.getProjectId() != null) {
            sysAttachmentDto.setProjectId(params.getProjectId());
        } else {
            sysAttachmentDto.setProjectId(0);
        }
        String ftpBasePath =createFTPBasePath(DateUtils.formatDate(new Date(), "yyyy-MM"), DateUtils.formatNowToYMD(), commonService.thisUserAccount());
        String ftpFileName = createNoRepeatFileName(sysAttachmentDto.getFileExtension());
        sysAttachmentDto.setFilePath(ftpBasePath);
        sysAttachmentDto.setFtpFileName(ftpFileName);
        ftpUtilsExtense.uploadFilesToFTP(ftpBasePath, new FileInputStream(file), ftpFileName);
        addAttachment(sysAttachmentDto);
        return sysAttachmentDto;
    }

    public String getViewImageUrl(Integer id) throws BusinessException {
        final String basePath = String.join("", File.separator, "temporary");
        if (id == null) {
            return null;
        }
        SysAttachmentDto sysAttachment = getSysAttachmentDto(id);
        String localDirPath = servletContext.getRealPath(basePath + File.separator + DateUtils.formatNowToYMD());
        String localFileName = String.join("", UUID.randomUUID().toString().substring(1, 8), ".", sysAttachment.getFileExtension());
        //清除今天以前的临时文件
        FileUtils.deleteDir(servletContext.getRealPath(basePath), Lists.newArrayList(DateUtils.formatNowToYMD()));
        FileUtils.folderMake(localDirPath);
        try {
            ftpUtilsExtense.downloadFileToLocal(sysAttachment.getFtpFileName(), sysAttachment.getFilePath(), localFileName, localDirPath);
        } catch (Exception e) {
            return  null;
        }
        Stream<String> stringStream = Arrays.stream(new String[]{basePath, DateUtils.formatNowToYMD(), localFileName});
        return StringUtils.join(stringStream.collect(Collectors.toList()), File.separator);
    }
}
