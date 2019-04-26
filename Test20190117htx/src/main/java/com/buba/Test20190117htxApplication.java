package com.buba;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@MapperScan("com.buba.mapper")
@SpringBootApplication
public class Test20190117htxApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(Test20190117htxApplication.class, args);
	}
	
	

}

