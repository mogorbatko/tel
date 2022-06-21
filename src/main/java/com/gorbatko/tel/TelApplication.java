package com.gorbatko.tel;

import com.gorbatko.tel.beans.ListOfLinks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(ListOfLinks.class)
public class TelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelApplication.class, args);



	}

}
