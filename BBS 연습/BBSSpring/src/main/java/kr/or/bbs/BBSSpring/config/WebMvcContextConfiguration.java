package kr.or.bbs.BBSSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// 프레젠테이션 부분에 해당하는 config파일 (설정 파일) 

@Configuration // 자바 config 파일(설정파일)임을 명시하는 애노테이션 
// 자바 config 파일을 통해 Bean 애노테이션이 적용된 클래스들을 읽어오고, 이를 통해 스프링 컨테이너에 새로운 빈 객체를 제공한다.

@EnableWebMvc // Web에 필요한 빈들을 대부분 자동으로 설정해준다.
@ComponentScan(basePackages= {"kr.or.bbs.BBSSpring.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter { // EnableWebMvc를 이용하면
	//기본적인 설정이 모두 자동으로 되지만, 기본 설정 이외의 설정이 필요한 경우 WebMvcConfigurerAdapter를 상속 받은 후, 메소드를 오버라딩하여 구현한다. 
	
	// 컨트롤러의 URL 요청 이외에 css,이미지, 자바스크립트같은 요청에 대한 처리를 담당하는 메서드 
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
 
    // 아래 메서드는 default servlet handler를 사용하게 한다.
	// 매핑 정보가 없는 URL 요청이 들어왔을 때 WAS의 default servlet이 static한 자원을 읽어서 보여줄 수 있게끔 하는 설정 
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
   
    // 특정 URL에 대한 처리를 컨트롤러 클래스를 작성하지 않고 매핑할 수 있도록 해준다.
    // "/"라는 요청이 들어오면 "main"이라고 하는 뷰의 이름으로 보여준다. 
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    	System.out.println("addViewControllers가 호출됩니다. ");
        registry.addViewController("/").setViewName("main");
    }
    
    // 아래 메서드에서 설정된 형태로 뷰를 사용하게 된다.
    // InternalResourceViewResolver를 생성하고 있고 이 리졸버에게 Prefix, Suffix를 지정하고 있다. 
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
