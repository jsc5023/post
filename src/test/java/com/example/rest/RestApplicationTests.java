package com.example.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class RestApplicationTests {

	@Test
	void t1() {
		// 모든 걸 다 하나의 어레이에 저장하니까 좋다.
		ArrayList<Integer> list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		Integer num = (Integer)list.get(0);
		for(int i = 0; i < list.size(); i++) {
			int a = list.get(i);
		}
	}

}
