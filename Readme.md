## 为什么开发这个小项目
1.之前写了篇博客:[Spring Boot Linux 部署脚本](https://blog.csdn.net/boom_man/article/details/84255457),
2.但是在实际开发中,需要手动修改其中的配置，有三处需要修改。
3.需要用chmod +x 赋予可执行权限
4.需要建立相关目录结构

综上所述,主要研究了 java打包tar,java赋予文件可执行权限相关内容
开发出了[Linux Spring Boot Startup Generator from Github](https://github.com/BoomManPro/linux-spring-boot-startup-generator)

在线使用方式 http://www.boommanpro.cn:8088/downloadTarGz?projectName=xxx

将后缀projectName参数值修改为你自己想要的即可

## Release版本
在[Release页面](https://github.com/BoomManPro/linux-spring-boot-startup-generator/releases)下载 以 jar 结尾的文件

本地部署: `java -jar linux-spring-boot-startup-generator.jar` 运行即可

部署完成后-->点击[本地测试地址](http://127.0.0.1:8088/downloadTarGz?projectName=linux-spring-boot-startup-generator)即可下载


## linux部署
在[Release页面](https://github.com/BoomManPro/linux-spring-boot-startup-generator/releases)下载 以 tar.gz结尾的文件
```
tar -zvxf linux-spring-boot-startup-generator.tar.gz
```
在目录中运行`./startup`即可


## 项目核心源码分析
1.模板框架采用Thymeleaf
```java
/**
 * @author <a href="mailto:boommanpro@gmail.com">BoomManPro</a>
 * @date 2019/6/5 16:21
 * @created by BoomManPro
 */
public enum ThymeLeafConfig {
    /**
     * Thymeleaf默认配置
     */
    INSTANCE;
    private TemplateEngine templateEngine;

    private static final String DEFAULT_PREFIX = "/templates/";

    ThymeLeafConfig() {
        //因读取的是Resource的文件 需要是ClassLoaderTemplateResolver
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(DEFAULT_PREFIX);
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public static TemplateEngine getTemplateEngine() {
        return INSTANCE.templateEngine;
    }
}

```
2.tar压缩后的文件解压具有可执行权限
```
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
```

可执行: `// = (octal) 0100755`  `tarArchiveEntry.setMode(33261);` 涉及到进制转换
## 写在最后

如果当前项目帮助到了你,希望能够给个start哈，感激！！