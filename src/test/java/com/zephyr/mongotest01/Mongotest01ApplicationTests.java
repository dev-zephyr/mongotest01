package com.zephyr.mongotest01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Mongotest01ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void d() {

		List<H> list = new ArrayList<>();

		list.add(new H("h", 1));
		list.add(new H("g", 1));
		list.add(new H("a", 1));
		list.add(new H("d", 1));
		
		if(list.contains(new H("h", 2))) {
			System.out.println("하하");
		} else {
			System.out.println("ㅜㅜ");
		}
	}
}

class H {
	String name;
	int idx;

	public H(String name, int idx) {
		this.name = name;
		this.idx = idx;
	}

	@Override
	public boolean equals(Object obj) {

		if(obj instanceof H) {
			H input = (H) obj;

			if(this.name.equals(input.name)) {
				return true;
			}

		}
		return false;
	}
}