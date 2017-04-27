package org.unreal.common.core.util;

import java.util.Locale;

/**
 * <b>类名称：</b> SensitiveUtil <br/>
 * <b>类描述：</b> 数据脱敏工具类<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年02月21日 11:21<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class SensitiveUtil {

    private static final String SENSITIVE_CN_NAME = "([\\u4e00-\\u9fa5])([\\u4e00-\\u9fa5]+)";
    private static final String SENSITIVE_BETWEEN = "(.{%d}).*(.{%d})";
    private static final String SENSITIVE_START = "(.{%d}).*";

    public static String sensitiveCnName(String realName){
        return realName.replaceAll(SENSITIVE_CN_NAME,getPaddingStartFixStar(2));
    }

    public static String sensitiveMobile(String mobile){
        return mobile.replaceAll(getSensitiveReg(3,4), getPaddingStar(mobile,3,4));
    }

    public static String sensitiveIdentity(String identity){
        return identity.replaceAll(getSensitiveReg(2,4), getPaddingStar(identity,2,4));
    }

    public static String sensitiveCustomer(String source , int keepStart , int keepEnd){
        return source.replaceAll(getSensitiveReg(keepStart,keepEnd),
                getPaddingStar(source, keepStart, keepEnd));
    }

    public static String sensitiveCustomer(String source , int keepStart){
        return source.replaceAll(getSensitiveReg(keepStart),
                getPaddingStar(source, keepStart));
    }

    private static String getSensitiveReg(int keepStart,int keepEnd){
        return String.format(Locale.getDefault(), SENSITIVE_BETWEEN , keepStart , keepEnd);
    }

    private static String getSensitiveReg(int keepStart){
        return String.format(Locale.getDefault() , SENSITIVE_START , keepStart);
    }

    private static String getPaddingStar(String source, int keepStart, int keepEnd){
        return getPaddingBetweenFixStar( source.length() - keepStart - keepEnd);
    }

    private static String getPaddingStar(String source, int keepStart){
        return getPaddingStartFixStar(source.length() - keepStart);
    }

    private static String getPaddingStartFixStar(int starNum){
        StringBuilder sb = new StringBuilder();
        sb.append("$1");
        for (int i = 0; i < starNum; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    private static String getPaddingBetweenFixStar(int starNum){
        StringBuilder sb = new StringBuilder();
        sb.append("$1");
        for (int i = 0; i < starNum; i++) {
            sb.append("*");
        }
        sb.append("$2");
        return sb.toString();
    }
}
