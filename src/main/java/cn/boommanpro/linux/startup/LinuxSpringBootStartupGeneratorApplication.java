package cn.boommanpro.linux.startup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
/**
 * @author <a href="mailto:boommanpro@gmail.com">BoomManPro</a>
 * @date 2019/6/5 16:21
 * @created by BoomManPro
 */
@Slf4j
@SpringBootApplication
public class LinuxSpringBootStartupGeneratorApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = new SpringApplication(LinuxSpringBootStartupGeneratorApplication.class).run();
        ServerProperties serverProperties = context.getBean(ServerProperties.class);
        String host = Optional.ofNullable(serverProperties.getAddress()).orElse(InetAddress.getLocalHost()).getHostAddress();
        int port = Optional.ofNullable(serverProperties.getPort()).orElse(8080);
        String contextPath = Optional.ofNullable(serverProperties.getServlet().getContextPath()).orElse("");
        log.info("<------------------------------------------ http://{}:{}{}/ ------------------------------------------>", host, port, contextPath);
        log.info("{}系统启动成功", LinuxSpringBootStartupGeneratorApplication.class.getSimpleName());
    }

}
