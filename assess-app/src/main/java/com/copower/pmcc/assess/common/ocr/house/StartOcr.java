package com.copower.pmcc.assess.common.ocr.house;

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
 * @Date: 2018/9/30 16:29
 * @Description:开始解析房产证的处理类
 */
public class StartOcr {

    public static void main(String[] args) {
        try {
            String v = "" ;
            v = "D:\\temp\\img\\A12.jpg" ;
//            v = "D:\\temp\\img\\A9.png" ;
            String item = start(v);
            System.out.println("数据:"+item);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     *
     * 功能描述: 解析传入图像中的数据
     *
     * @param: D:\332A4807-69EE-4a1a-B44B-673C65F81289.png
     * @return: 返回获取的数据 example:"data":{"房产证号":"4376103","权利人":"陈","共有情况":"按份共有","坐落":"成都市天府新区华阳街道天府大道南段2039号36栋2楼9号","登记时间":"2016-05-30","房屋性质":"","房屋用途":"商业","建筑面积":"269.07","土地权利类型/取得方式":"出让"},"angle":0}
     * @auther: zch
     * @date: 2018/9/30 16:41
     */
    public static   String start(String startPath) throws Exception {
        String data = null;
        String host = "https://ocrapi-house-cert.taobao.com";
        String path = "/ocrservice/houseCert";
        String method = "POST";
        String appcode = "a415256d0b42457791b60904237383c3";//以后需要替换为公司账户的code(目前暂时用临时的)
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
//        String bodys = "{//图像数据：base64编码，要求base64编码后大小不超过4M，最短边至少15px，最长边最大4096px，支持jpg/png/bmp格式，和url参数只能同时存在一个\"img\":\"\",//图像url地址：图片完整URL，URL长度不超过1024字节，URL对应的图片base64编码后大小不超过4M，最短边至少15px，最长边最大4096px，支持jpg/png/bmp格式，和img参数只能同时存在一个\"url\":\"\"}";
//        String bodys = "{img:'https://github.com/noatnu/web-install/blob/master/zTreejs/src/main/webapp/template/A4.jpg'}";
        String bodys = "{img:'RDpcMzMyQTQ4MDctNjlFRS00YTFhLUI0NEItNjczQzY1RjgxMjg5LnBuZw==',prob:false}";
        bodys = "{\"img\":\"" + changeToBase64(startPath) + "\",\"prob\":false}";


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //获取response的body
            data = EntityUtils.toString(response.getEntity()) ;
            return data;
        } catch (Exception e) {
            return String.format("exception:%s",e.getMessage());
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
