package com.jollychic.app2.util.business;

import com.jollychic.app2.common.enums.AppEnum;
import com.jollychic.app2.consts.Const;
import com.jollychic.app2.protocol.request.BaseRequestInfo;
import com.jollychic.app2.util.StringUtil;

/**
 * 版本工具类
 *
 * @author luyou
 * @date 2015-11-10
 */
public class VersionUtil {

    private static final byte COMPARE_TYPE_EQUAL_TO = 1;                   //   ==      equal to
    private static final byte COMPARE_TYPE_NOT_EQUAL_TO = 2;               //   !=      not equal to
    private static final byte COMPARE_TYPE_GREATER_THAN = 3;                //   >       greater than
    private static final byte COMPARE_TYPE_GREATER_THAN_OR_EQUAL_TO = 4;   //   >=      greater than or equal to
    private static final byte COMPARE_TYPE_LESS_THAN = 5;                  //   <       less than
    private static final byte COMPARE_TYPE_LESS_THAN_OR_EQUAL_TO = 6;      //   <=      less than or equal to

    /**
     * 客户端APP版本是否大于给定版本
     *
     * @param jcAndroidVersion
     * @param jcIosVersion
     * @param mvVersion
     * @param dealyVersion
     * @param req
     * @return
     */
    public static boolean greaterThan(String jcAndroidVersion, String jcIosVersion, String mvVersion, String dealyVersion, BaseRequestInfo req) {
        return judgeVersion(jcAndroidVersion, jcIosVersion, mvVersion, dealyVersion, req, COMPARE_TYPE_GREATER_THAN);
    }

    /**
     * 客户端APP版本是否等于指定版本
     * @param jcAndroidVersion
     * @param jcIosVersion
     * @param mvVersion
     * @param dealyVersion
     * @param req
     * @return
     */
   /* public static boolean greaterEqualTo(String jcAndroidVersion, String jcIosVersion, String mvVersion, String dealyVersion, BaseRequestInfo req) {
        return judgeVersion(jcAndroidVersion, jcIosVersion, mvVersion, dealyVersion, req, COMPARE_TYPE_EQUAL_TO);
    }*/

    /**
     * 客户端APP版本是否大于等于给定版本
     *
     * @param jcAndroidVersion
     * @param jcIosVersion
     * @param mvVersion
     * @param dealyVersion
     * @param req
     * @return
     */
    public static boolean greaterThanOrEqualTo(String jcAndroidVersion, String jcIosVersion, String mvVersion, String dealyVersion, BaseRequestInfo req) {
        return judgeVersion(jcAndroidVersion, jcIosVersion, mvVersion, dealyVersion, req, COMPARE_TYPE_GREATER_THAN_OR_EQUAL_TO);
    }

    /**
     * 客户端APP版本是否小于给定版本
     *
     * @param jcAndroidVersion
     * @param jcIosVersion
     * @param mvVersion
     * @param dealyVersion
     * @param req
     * @return
     */
    public static boolean lessThan(String jcAndroidVersion, String jcIosVersion, String mvVersion, String dealyVersion, BaseRequestInfo req) {
        return judgeVersion(jcAndroidVersion, jcIosVersion, mvVersion, dealyVersion, req, COMPARE_TYPE_LESS_THAN);
    }

    /**
     * 判断APP版本比较结果是否正确
     *
     * @param jcAndroidVersion
     * @param jcIosVersion
     * @param mvVersion
     * @param dealyVersion
     * @param req
     * @param type             比较类型
     * @return true表示正确，false表示错误
     */
    private static boolean judgeVersion(String jcAndroidVersion, String jcIosVersion, String mvVersion, String dealyVersion, BaseRequestInfo req, Byte type) {
        if (StringUtil.isEmpty(jcAndroidVersion) || StringUtil.isEmpty(jcIosVersion) || StringUtil.isEmpty(mvVersion) || StringUtil.isEmpty(dealyVersion)
                || req == null || type == null) {
            return false;
        }
        //基于站点前端类型确认比较版本值
        String compareVersion = null;
        switch (AppEnum.getSite(req.getAppTypeId())) {
            case MARKAVIP:
                compareVersion = mvVersion;
                break;
            case DEALY:
                compareVersion = dealyVersion;
                break;
            default:
                switch (req.getTerminalType()) {
                    case Const.TERMINAL_TYPE_ANDROID:
                        compareVersion = jcAndroidVersion;
                        break;
                    default:
                        compareVersion = jcIosVersion;
                        break;
                }
                break;
        }
        if (compareVersion == null) {
            return false;
        }

        //# judge by compare type
        String appVersion = req.getAppVersion();
        switch (type) {
            case COMPARE_TYPE_EQUAL_TO:
                if (compareVersion(appVersion, compareVersion) == 0) {
                    return true;
                }
                break;
            case COMPARE_TYPE_NOT_EQUAL_TO:
                if (compareVersion(appVersion, compareVersion) != 0) {
                    return true;
                }
                break;
            case COMPARE_TYPE_GREATER_THAN:
                if (compareVersion(appVersion, compareVersion) > 0) {
                    return true;
                }
                break;
            case COMPARE_TYPE_GREATER_THAN_OR_EQUAL_TO:
                if (compareVersion(appVersion, compareVersion) >= 0) {
                    return true;
                }
                break;
            case COMPARE_TYPE_LESS_THAN:
                if (compareVersion(appVersion, compareVersion) < 0) {
                    return true;
                }
                break;
            case COMPARE_TYPE_LESS_THAN_OR_EQUAL_TO:
                if (compareVersion(appVersion, compareVersion) <= 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * 客户端APP版本是否大于给定版本(不限客户端类型)
     *
     * @param appVersion
     * @param target
     * @return
     */
    public static boolean lessThan(String appVersion, String target) {
        return compareVersion(appVersion, target) < Const.DEFAULT_INT;
    }

    /**
     * 客户端APP版本是否小于给定版本
     *
     * @param jcAndroidVersion
     * @param jcIosVersion
     * @param mvVersion
     * @param dealyVersion
     * @param appTypeId
     * @param terminalType
     * @param appVersion
     * @return
     */
    public static boolean lessThan(String jcAndroidVersion, String jcIosVersion, String mvVersion, String dealyVersion, Byte appTypeId, Integer terminalType, String appVersion) {
        if (StringUtil.isEmpty(jcAndroidVersion) || StringUtil.isEmpty(jcIosVersion) || StringUtil.isEmpty(mvVersion) || StringUtil.isEmpty(dealyVersion)
                || appTypeId == null || terminalType == null) {
            return false;
        }

        //# choose the version of terminal
        String compareVersion = null;
        switch (AppEnum.getSite(appTypeId)) {
            case MARKAVIP:
                compareVersion = mvVersion;
                break;
            case DEALY:
                compareVersion = dealyVersion;
                break;
            default:
                switch (terminalType) {
                    case Const.TERMINAL_TYPE_ANDROID:
                        compareVersion = jcAndroidVersion;
                        break;
                    default:
                        compareVersion = jcIosVersion;
                        break;
                }
                break;
        }
        if (compareVersion == null) {
            return false;
        }

        if (compareVersion(appVersion, compareVersion) < Const.DEFAULT_INT) {
            return true;
        }
        return false;
    }

    /**
     * 比较两个版本号
     *
     * @param version1
     * @param version2
     * @return -1 version1<version2 , 0 version1=version2 , 1 version1>version2
     */
    public static int compareVersion(String version1, String version2) {
        return versionToInteger(version1).compareTo(versionToInteger(version2));
    }

    /**
     * 版本转数字
     */
    private static Integer versionToInteger(String version) {
        if (StringUtil.isEmpty(version)) {
            return Const.DEFAULT_INT;
        }
        String[] versions = version.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String v : versions) {
            if (v.length() < 2) {
                sb.append("0");
            }
            sb.append(v);
        }
        if (sb.length() < 6) {
            int len = 6 - sb.length();
            for (int i = 0; i < len; i++) {
                sb.append("0");
            }
        }
        return Integer.parseInt(sb.toString());
    }

}
