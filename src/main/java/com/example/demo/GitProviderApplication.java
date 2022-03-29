package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/provider")
public class GitProviderApplication {
	@GetMapping("/data")
	
    public String getList() {
		return "hellow world.....";
	}
	public static void main(String[] args) {
		SpringApplication.run(GitProviderApplication.class, args);
	}

}
