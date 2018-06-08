package com.kennedy.java.experienece.type.string;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class UsingStringTest {


	@Test
	public void test() {
		String uri = "http://localhost:8080/luz_person/api/70c1ae52-3f51-4ea6-acbd-aced84daaeb0/companies/10";
		String id = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
		System.out.println(id);
		String url = uri.substring(uri.indexOf("/luz_person/api"), uri.length());
		System.out.println(url);

	}

}
