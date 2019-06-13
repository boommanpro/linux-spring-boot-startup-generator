package cn.boommanpro.linux.startup.util;

import cn.boommanpro.linux.startup.generator.GeneratorFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:boommanpro@gmail.com">BoomManPro</a>
 * @date 2019/6/5 16:21
 * @created by BoomManPro
 */
@Slf4j
public class GzipUtil {
    public static InputStream pack(GeneratorFile[] generatorFiles) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        TarArchiveOutputStream os = new TarArchiveOutputStream(out);
        try {
            for (GeneratorFile generatorFile : generatorFiles) {

                TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(generatorFile.getFileName());
                if (generatorFile.isExecutable()) {
                    // = (octal) 0100755
                    tarArchiveEntry.setMode(33261);
                }
                byte[] bytes = new byte[]{};
                if (StringUtils.notBlank(generatorFile.getContent())) {
                    bytes = generatorFile.getContent().getBytes();
                    tarArchiveEntry.setSize(bytes.length);

                }
                os.putArchiveEntry(tarArchiveEntry);
                IOUtils.copy(new ByteArrayInputStream(bytes), os);
                os.closeArchiveEntry();

            }
        } catch (IOException e) {
            log.error("Exception:", e);
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                log.error("os Exception:", e);
            }
        }
        return IoConvertUtil.parse(out);
    }

}

