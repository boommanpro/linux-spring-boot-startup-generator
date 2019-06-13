package cn.boommanpro.linux.startup.generator;

import cn.boommanpro.linux.startup.generator.config.ThymeLeafConfig;
import cn.boommanpro.linux.startup.util.GzipUtil;
import org.thymeleaf.context.Context;

import java.io.InputStream;

/**
 * @author <a href="mailto:boommanpro@gmail.com">BoomManPro</a>
 * @date 2019/6/5 16:21
 * @created by BoomManPro
 */
public class TemplateGenerator {

    private static final String JAR_FOLDER = "jar/";
    private static final String LOGS_FOLDER = "logs/";
    private static final String README_FILE = "Readme.md";
    private static final String STARTUP_FILE = "startup.sh";
    private static final String SHUTDOWN_FILE = "shutdown.sh";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";

    /**
     * 需要进行模板输出的是
     * <p>
     * 1.Readme.md
     * 2.shutdown.sh
     * 3.startup.sh
     * <p>
     * 然后将所有文件进行压缩
     */
    public static InputStream generator(String projectName) {
        GeneratorFile[] generatorFiles = new GeneratorFile[]{
                GeneratorFile.of(getPackagingName(projectName,JAR_FOLDER)),
                GeneratorFile.of(getPackagingName(projectName,LOGS_FOLDER)),
                GeneratorFile.of(getPackagingName(projectName,README_FILE)).setContent(doGenerator(projectName, README_FILE)),
                GeneratorFile.of(getPackagingName(projectName,STARTUP_FILE)).setExecutable(true).setContent(doGenerator(projectName, STARTUP_FILE)),
                GeneratorFile.of(getPackagingName(projectName,SHUTDOWN_FILE)).setExecutable(true).setContent(doGenerator(projectName, SHUTDOWN_FILE)),
        };

        return  GzipUtil.pack(generatorFiles);

    }

    private static String doGenerator(String projectName, String fileName) {
        Context context = new Context();
        context.setVariable("projectName", projectName);
        context.setVariable("LEFT_BRACKET", LEFT_BRACKET);
        context.setVariable("RIGHT_BRACKET", RIGHT_BRACKET);
        return ThymeLeafConfig.getTemplateEngine().process("config/" + fileName, context);
    }

    private static String getPackagingName(String projectName,String fileName){
        return projectName + "/" + fileName;
    }


}
