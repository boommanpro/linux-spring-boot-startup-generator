package cn.boommanpro.linux.startup.util;

/**
 * @author <a href="mailto:boommanpro@gmail.com">BoomManPro</a>
 * @date 2019/6/5 16:21
 * @created by BoomManPro
 */
public class StringUtils {

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public  static boolean notBlank(String ... strs ){
        for (String str: strs) {
            if (StringUtils.isBlank(str)){
                return false;
            }
        }
        return true;
    }

}
