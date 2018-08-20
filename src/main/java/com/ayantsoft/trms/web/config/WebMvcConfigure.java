package com.ayantsoft.trms.web.config;

import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mongodb.MongoClient;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.ayantsoft.trms" })
public class WebMvcConfigure extends WebMvcConfigurerAdapter {
	private Logger log = Logger.getLogger(WebMvcConfigure.class);

	public WebMvcConfigure() {
		log.info("WebMvcConfigure OBJECT CREATED");
	}


	@Bean
	public MongoDbFactory mongoDbFactory() throws UnknownHostException{
		SimpleMongoDbFactory simpleMongoDbFactory = null;
		simpleMongoDbFactory =	new SimpleMongoDbFactory(new MongoClient("localhost",27017),"ayant_trmsv2");
		return simpleMongoDbFactory;
	}


	
	@Bean
	public MongoTemplate mongoTemplate(){
		try {
			return new MongoTemplate(mongoDbFactory());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return multipartResolver;
	}
	
	
	@Bean
    public Message message(){
		
		Message message = null;
		try{
			Session session = null;
			Properties properties = new Properties();
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.host", "smtp.office365.com");
			properties.put("mail.smtp.auth", "true");
			
			session = Session.getInstance(properties,new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("sghosh@globalitexperts.com","gites_2015");
				}
			});
			
			message = new MimeMessage(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
    }
	
	
	@Bean
	public JavaMailSenderImpl getJavaMailSenderImpl(){
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("smtp.office365.com");
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername("sghosh@globalitexperts.com");
		javaMailSenderImpl.setPassword("gites_2015");
		
		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "smtp.office365.com");
		properties.put("mail.smtp.starttls.enable",true);
		properties.put("mail.smtp.auth",true);	
		javaMailSenderImpl.setJavaMailProperties(properties);
		return javaMailSenderImpl;
	}
	
	
	
	/*@Bean
	public JavaMailSenderImpl getJavaMailSenderImpl(){
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername("mrigankakoleyjob@gmail.com");
		javaMailSenderImpl.setPassword("1990aknagirmyelok");
		
		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable",true);
		properties.put("mail.smtp.auth",true);
		
		javaMailSenderImpl.setJavaMailProperties(properties);
		
		return javaMailSenderImpl;
	}
*/
	
	
	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();
	}
	

}
