package cn.boommanpro.linux.startup.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author <a href="mailto:boommanpro@gmail.com">BoomManPro</a>
 * @date 2019/6/5 16:21
 * @created by BoomManPro
 */
public class IoConvertUtil {

    /**
     * ByteArrayOutputStream -> ByteArrayInputStream
     */
    public static ByteArrayInputStream parse(final ByteArrayOutputStream out) {
        return new ByteArrayInputStream(out.toByteArray());
    }
}
