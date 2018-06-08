package com.kennedy.java.experienece.performance;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class PerformanceExampleTest {

	private static final int LOOP = 10000;
	
	@Test
	public void test_DifferentBetweenFindInMapAndList() {

		List<Example> examples = new ArrayList<>();
		
		for (int i = 0; i < LOOP; i++) {
			examples.add(new Example(Long.valueOf(i), "data=" + i));
		}
		Instant t1, t2;
		
		t1 = Instant.now();
		doMapSearch(examples);
		t2 = Instant.now();
		long ns = Duration.between(t1, t2).toMillis();
		System.out.println("Time excute mapSearch = " + ns + "mls");
		
		t1 = Instant.now();
		doListSearch(examples);
		t2 = Instant.now();
		ns = Duration.between(t1, t2).toMillis();
		System.out.println("Time excute listSearch = " + ns + "mls");
		
		/* Result
		memory = 11073656
		Time excute mapSearch = 3mls
		
		counter = 10000, memory = 600897656
		Time excute listSearch = 754mls
		*/
		
	}

	private void doListSearch(List<Example> examples) {
		long m1 = Runtime.getRuntime().freeMemory();
		
		int counter = 0;
		for (int i = 0; i < LOOP; i++) {
			for (Example example : examples) {
				if (example.getId().equals(Long.valueOf(i))) {
					counter++;
				}
			}
		}

		long m2 = Runtime.getRuntime().totalMemory();
		System.out.println("counter = " + counter + ", memory = " + (m2-m1));
		
	}

	private void doMapSearch(List<Example> examples) {
		long m1 = Runtime.getRuntime().freeMemory();
		Map<Long, Example> exMap = new HashMap<>();
		for (Example example : examples) {
			exMap.put(example.getId(), example);
		}
		
		for (int i = 0; i < LOOP; i++) {
			exMap.get(Long.valueOf(i));
		}
		
		long m2 = Runtime.getRuntime().totalMemory();
		System.out.println("memory = " + (m2-m1));
	}

	@Getter
	@Setter
	@AllArgsConstructor
	class Example {
		private Long id;
		private String data;
	}
	
}
