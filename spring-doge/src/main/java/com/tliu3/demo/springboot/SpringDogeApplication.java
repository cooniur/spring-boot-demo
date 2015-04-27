package com.tliu3.demo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;

import doge.photo.DogePhotoManipulator;

@SpringBootApplication
public class SpringDogeApplication {

	@Bean
	WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("client");
				registry.addViewController("/log").setViewName("log");
			}
		};
	}

	@Bean
	Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		// Serialize `LocalDateTime` to string; otherwise it'll be serialized to an array
		builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return builder;
	}

	@Bean
	DogePhotoManipulator dogePhotoManipulator() {
		DogePhotoManipulator dogePhotoManipulator = new DogePhotoManipulator();
		dogePhotoManipulator.addTextOverlay("illumina", "amazing", "HiSeq");
		dogePhotoManipulator.addTextOverlay("pivotal", "abstractfactorybean", "java");
		dogePhotoManipulator.addTextOverlay("spring", "annotations", "boot");
		dogePhotoManipulator.addTextOverlay("code", "semicolonfree", "groovy");
		dogePhotoManipulator.addTextOverlay("clean", "juergenized", "spring");
		dogePhotoManipulator.addTextOverlay("js", "nonblocking", "wat");
		return dogePhotoManipulator;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDogeApplication.class, args);
	}
}
