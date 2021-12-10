package me.kunzou.ipAddressTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IpAddressTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpAddressTrackerApplication.class, args);
	}

}
