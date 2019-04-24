package com.example.demo.util;

import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.TreeMap;

public class SignUtil {
    /**
     * 签名秘钥
     */
    public static final String SECRET = "xxxx";

    public static String signature(TreeMap<String, String> paramMap) {

        StringBuffer paramStr = new StringBuffer();
        for (Map.Entry entry : paramMap.entrySet()) {
            if (entry.getKey().equals("signature")) {
                continue;
            }
            paramStr.append(entry.getKey() + ":" + entry.getValue() + ";");
        }

        paramStr.append(SECRET);
        String retval = DigestUtils.md5DigestAsHex(paramStr.toString().getBytes());
        System.out.println(paramStr.toString());
        System.out.println("create signature=" + retval);
        return retval;

    }
}
