package com.copower.pmcc.assess.common;

/**
 * Created by 13426 on 2018/4/24.
 */
public class URLDecoderHelp {
    public static String encode(String val){
        String s = null;
        try {
            s = java.net.URLDecoder.decode(val,"UTF-8");
        }catch (Exception e){
            try {
                throw e;
            }catch (Exception e1){

            }
        }
        return s;
    }
}
