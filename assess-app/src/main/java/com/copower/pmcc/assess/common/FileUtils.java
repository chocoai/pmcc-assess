package com.copower.pmcc.assess.common;

import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by kings on 2018-6-5.
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static FileUtils fileUtils = new FileUtils();


    /**
     * 传入 字节流和文件名称
     *
     * @param fileName
     * @param bodys
     * @return
     */
    public static ResponseEntity<byte[]> createResponse(String fileName, byte[] bodys) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> responseEntity = null;
        String downloadFileName = null;
        try {
            downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            logger.error("抛出异常:" + e.getMessage());
        }
        //解决下载显示的文件名问题
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //二进制流数据(最常见的文件下载)
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseEntity = new ResponseEntity<byte[]>(bodys, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    /**
     * @param zipPathAndName 压缩成的文件路径
     * @return 返回一个压缩后的字节流
     * @throws IOException
     */
    public static byte[] getZipFile(List<File> fileList, String zipPathAndName) throws IOException {
        File zipFile = new File(zipPathAndName);
        File[] srcFile = new File[fileList.size()];
        for (int i = 0; i < fileList.size(); i++) {
            srcFile[i] = fileList.get(i);
        }
        zipFiles(srcFile, zipFile);
        return org.apache.commons.io.FileUtils.readFileToByteArray(zipFile);
    }

    /**
     * 功能:压缩多个文件成一个zip文件
     *
     * @param srcFile：源文件列表
     * @param zipFile：压缩后的文件
     */
    public static void zipFiles(File[] srcFile, File zipFile) throws IOException {
        byte[] buf = new byte[1024];
        //ZipOutputStream类：完成文件或文件夹的压缩
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        for (int i = 0; i < srcFile.length; i++) {
            FileInputStream in = new FileInputStream(srcFile[i]);
            out.putNextEntry(new ZipEntry(srcFile[i].getName()));
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
    }

    //拼接2-4张图片
    public static String getCombinationOfhead(List<String> paths) throws IOException {
        if (paths.size() == 1) return paths.get(0);
        List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
        // 压缩图片所有的图片生成尺寸 250x250
        for (int i = 0; i < paths.size(); i++) {
            bufferedImages.add(FileUtils.resize(paths.get(i), 250, 247, true));
        }
        int width = 514; // 这是画板的宽高
        int height = 510; // 这是画板的高度
        BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 生成画布
        Graphics g = outImage.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        // 设置背景色 白色
        g2d.setBackground(new Color(255, 255, 255));
        // 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
        g2d.clearRect(0, 0, width, height);
        // 开始拼凑 根据图片的数量判断该生成那种样式的组合头像目前为4中
        int j = 1;
        for (int i = 1; i <= bufferedImages.size(); i++) {
            if (bufferedImages.size() <= 4) {
                if (i <= 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), 250 * i + 10 * i
                            - 260, 0, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 250 * j + 10 * j
                            - 260, 260, null);
                    j++;
                }
            }
        }
        String replace = UUID.randomUUID().toString().replace("-", "");
        String localFileName = String.format("%s.%s", replace, "jpg");
        String strDayTempDirName = DateUtils.formatNowToYMD();
        String localDirPath = String.join(File.separator, org.apache.commons.io.FileUtils.getTempDirectoryPath() ,strDayTempDirName);
        String localFullPath = localDirPath + File.separator + localFileName;
        File file = new File(localDirPath) ;
        if (!file.exists()){
            file.mkdirs();
        }
        ImageIO.write(outImage, "jpg", new File(localFullPath));
        return localFullPath;
    }


    /**
     * 图片缩放
     *
     * @param filePath 图片路径
     * @param height   高度
     * @param width    宽度
     * @param bb       比例不对时是否需要补白
     */
    public static BufferedImage resize(String filePath, int height, int width,
                                        boolean bb) {
        try {
            File f = new File(filePath);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            if (bb) {
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            return (BufferedImage) itemp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 注意： 该方法适用的图片格式为 bmp/gif/jpg/png
     *
     * @param path
     * @return
     */
    public static boolean checkImgSuffix(String path) {
        if (StringUtils.isBlank(path)){
            return false;
        }
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(new File(path));
            BufferedImage sourceImg = ImageIO.read(fi);//判断图片是否损坏
            int picWidth = sourceImg.getWidth(); //确保图片是正确的（正确的图片可以取得宽度）
            // 通过ImageReader来解码这个file并返回一个BufferedImage对象
            // 如果找不到合适的ImageReader则会返回null，我们可以认为这不是图片文件
            // 或者在解析过程中报错，也返回false
        } catch (Exception e) {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e1) {

                }
            }
            return false;
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e2) {

                }
            }
        }
        return true;
    }


    /**
     * @param base64String base64编码字符串
     * @param path         图片路径-具体到文件
     * @return
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     */
    public static boolean base64ToImage(String base64String, String path) {
        if (base64String == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(base64String);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static FileUtils getFileUtils() {
        return fileUtils;
    }

    private FileUtils() {
    }

    /**
     * @param path 图片路径
     * @return
     * @Description: 图片转化成base64字符串
     * @Author:
     * @CreateTime:
     */
    public static String base64Encode(String path) throws Exception {
        byte[] bytes = org.apache.commons.io.FileUtils.readFileToByteArray(new File(path));
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }
}
