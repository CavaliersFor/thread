package com.lunjar.ebp.portal.console.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunjar.products.core.config.SysConfig;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.utils.PathUtils;
import com.lunjar.products.core.web.WebCoreConstants;

/**
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/5/3 16:11
 */
@Service
public class PortalBaseService {
    private static final Logger logger = LoggerFactory.getLogger(PortalBaseService.class);

    @Autowired
    private SysConfig sysConfig;

    protected String getStoreDir(){
        return  sysConfig.get(SysConfig.KEY_STORE_DIR);
    }

    /**
     * 拷贝文件，如果父路径不存在则先建立父路径
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/3 17:26
     * @version %I%,%G%
     * @see
     */
    protected void copyFile(String oldFile, String newFile) throws ServiceException {
        File srcFile = new File(oldFile);
        if (!srcFile.exists()) {
            logger.error("原始文件不存在【{}】", oldFile);
            throw new ServiceException("修改失败");
        }

        File destFile = new File(newFile);
        File destParentDir = destFile.getParentFile();
        if (!destParentDir.exists()) {
            destParentDir.mkdirs();
        }

        logger.info("文件拷贝:srcFile【{}】,destFile【{}】", oldFile, newFile);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            logger.error("拷贝文件失败【{}】,【{}】", srcFile,destFile);
            throw new ServiceException("修改失败");
        }
    }
     /**
      * 将位于临时目录的文件拷贝到工作目录,同时保存原图和缩略图
      * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/6 18:31
      * @version %I%,%G%
      * @see
      */
    protected String copyTempFile(Long userId, String subDir, String fileName) throws ServiceException {
        String storeDir = getStoreDir();

        String scaleFileName=FilenameUtils.getBaseName(fileName)+"-scale."+FilenameUtils.getExtension(fileName);
        String tempStoreDir=PathUtils.concat(storeDir, WebCoreConstants.UPLAOD_TEMP);
        String tempStoreSubDir=PathUtils.concat(tempStoreDir, subDir);
        String tempOriginFile=PathUtils.concat(tempStoreSubDir, fileName);
        String tempScaleFile=PathUtils.concat(tempStoreSubDir, scaleFileName);

        String destOriginFile=assemblyPartnerStorePath(userId,storeDir,fileName);
        String destScaleFile=assemblyPartnerStorePath(userId,storeDir,scaleFileName);

        // 保存原始图和缩略图
        copyFile(tempOriginFile, destOriginFile);
        copyFile(tempScaleFile, destScaleFile);

        // 数据库保存缩略图
        String dbScaleFile = assemblyPartnerStorePath(userId, "/", scaleFileName);
        return dbScaleFile;
    }

    /**
     * 返回接入者下的图片路径（机构代码证件正反面,个人的身份证正反面照)
     *
     * 路径统一为:{storeDir}/user/{userId%100}/{fileName}
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/3 17:10
     * @version %I%,%G%
     * @see
     */
    protected String assemblyPartnerStorePath(Long userId, String storeDir, String fileName) {
        userId=userId%100;
        return PathUtils.concat(storeDir, "/user", userId.toString(), fileName);
    }

}
