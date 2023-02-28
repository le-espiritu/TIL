package kr.or.bbs.BBSSpring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"kr.or.bbs.BBSSpring.dao","kr.or.bbs.BBSSpring.service"})
@Import({DBConfig.class}) // Import 어노테이션을 활용하면 설정 파일을 여러개로 나눠서 작성할 수 있다. 
public class ApplicationConfig {

}
