package cn.boommanpro.linux.startup.generator.config;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

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
