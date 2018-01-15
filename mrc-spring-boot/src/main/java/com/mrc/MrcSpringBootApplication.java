package com.mrc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
@SpringBootApplication
public class MrcSpringBootApplication {

    @Value("${spring.profiles.active}")
    String profile;

    @Autowired
    DBConnector dbConnector;

    @RequestMapping("/testProfile")
    public String testProfile() {
        dbConnector.configure();
        return "spring.profiles.active=" + profile;
    }


    @RequestMapping("/")
    public String index() {
        return "Hello Spring Boot " + getLocalHost();
    }

    /**
     * mac 启动spring-boot 特别慢：http://ningg.top/tool-personal-intellij-idea-for-mac-optimize/
     * mac 启动tomcat报错：http://www.bubuko.com/infodetail-1865308.html
     * 查看本机的HostName
     *
     * @return
     */
    private String getLocalHost() {
        String host = null;
        try {
            host = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (Exception e) {
            throw new RuntimeException("Error. Failed to retrive crunchifyHost:" + e);
        }
        return host;
    }

    public static void main(String[] args) {
        SpringApplication.run(MrcSpringBootApplication.class, args);
    }
}
