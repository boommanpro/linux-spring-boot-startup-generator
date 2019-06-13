package cn.boommanpro.linux.startup.generator;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:boommanpro@gmail.com">BoomManPro</a>
 * @date 2019/6/5 16:21
 * @created by BoomManPro
 */
@Data(staticConstructor = "of")
@Accessors(chain = true)
public class GeneratorFile {
    @NonNull
    private String fileName;
    private String content = null;
    private boolean executable = false;
}
