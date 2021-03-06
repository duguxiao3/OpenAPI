package com.womai.mobile.api.common.util.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 工  程：womai-mobile-api
 * 包  名：com.womai.mobile.api.common.util.prop
 * 创建者: Chang Jinan
 * 日  期: 2016/5/12
 * 时  间: 18:33
 * 描  述：
 */
public class CachePutProp {

    private static Properties cacheProp =  new  Properties();

    private CachePutProp(String filePath) throws IOException {
        InputStream in;
        ClassLoader cl = CachePutProp.class.getClassLoader();
        if  (cl !=  null ) {
            in = cl.getResourceAsStream(filePath);
        }  else {
            in = ClassLoader.getSystemResourceAsStream(filePath);
        }
        cacheProp.load(in);
        in.close();
    }

    public static String getMapValue(String propKey, String filePath) throws IOException {
        if(cacheProp.size() == 0) {
            synchronized (CachePutProp.class){
                if(cacheProp.size() == 0) {
                    new CachePutProp(filePath);
                }
            }
        }
        String beanName = cacheProp.getProperty(propKey);
        return beanName == null ? null : beanName.trim();
    }
}
