package org.example.impls;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExampleTest {

	@Test
	public void test() {
		String result = new Example().sayHello("Bob");
		assertEquals("Hello Bob", result);
	}

}
