package com.ccs.szmilitary.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang on 2015/8/3.
 */
public class PNGMapUtil {
    public static Map<String,String> country_name_img_map(){
        Map<String,String> name_img_map = new HashMap<String, String>();
        name_img_map.put("美国","/resources/img/america@3x.png");
        name_img_map.put("中国","/resources/img/china@3x.png");
        name_img_map.put("俄罗斯","/resources/img/russia@3x.png");
        return name_img_map;
    }
}
