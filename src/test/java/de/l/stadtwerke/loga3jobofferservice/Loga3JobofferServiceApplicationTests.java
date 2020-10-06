package de.l.stadtwerke.loga3jobofferservice;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Loga3JobofferServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void hello() {
		System.out.println("Simple Backend test");
		Assert.assertTrue(1==1);
	}
}
