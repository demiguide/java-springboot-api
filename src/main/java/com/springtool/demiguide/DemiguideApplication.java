package com.springtool.demiguide;

import com.springtool.demiguide.util.JwtSecretGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemiguideApplication {
	@Autowired
	private static JwtSecretGenerator generator;

	public static void main(String[] args) {
		System.out.println(generator.genSecretKey());
		SpringApplication.run(DemiguideApplication.class, args);
	}

}
