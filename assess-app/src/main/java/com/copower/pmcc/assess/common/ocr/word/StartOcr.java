package com.copower.pmcc.assess.common.ocr.word;

import com.copower.pmcc.assess.common.ocr.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/9/30 17:19
 * @Description:
 */
public class StartOcr {

    public static void main(String[] args) {
        try {
            String v = "D:\\temp\\word\\urlimage.png";
            v = "https://fegine-drug.oss-cn-shanghai.aliyuncs.com/image/urlimage.png" ;
            start(v);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void start(String startPath)throws Exception{
        String host = "https://wordimg.market.alicloudapi.com";
        String path = "/word";
        String method = "POST";
        String appcode = "a415256d0b42457791b60904237383c3";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("image", startPath);

        //或者base64
//        bodys.put("image", "{\"img\":\"" + changeToBase64(startPath) + "\",\"prob\":false}");


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String changeToBase64(String fileName) throws Exception{
        File file = new File(fileName);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        String base64Code=new BASE64Encoder().encode(buffer);
        return base64Code;
    }
}
