package org.github.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * springboot starter
 * <p>
 * 备注：平常测试的类
 *
 * @author sdc
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.github"})
public class SpringBootEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEsApplication.class, args);
    }
}
