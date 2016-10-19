package com.dk.cp.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class VocabularyTest {

	@Test
	public void put2CharWordIntoEmptyV() {
		Vocabulary v = new Vocabulary();
		v.expand("人民");
		assertTrue(v.containsKey("人"));
		assertTrue(v.get("人").containsKey("民"));
	}

	@Test
	public void put3CharWordIntoEmptyV() {
		Vocabulary v = new Vocabulary();
		v.expand("費曄暉");
		assertTrue(v.containsKey("費"));
		assertTrue(v.get("費").containsKey("曄"));
		assertTrue(v.get("費").get("曄").containsKey("暉"));
	}
	
	@Test
	public void put2WordsWithNoSameCharIntoV() {
		Vocabulary v = new Vocabulary();
		v.expand("人民");
		v.expand("群众");
		assertTrue(v.containsKey("人"));
		assertTrue(v.containsKey("群"));
		assertTrue(v.get("人").containsKey("民"));
		assertTrue(v.get("群").containsKey("众"));
	}
	
	@Test
	public void put1CharWordIntoEmptyV() {
		Vocabulary v = new Vocabulary();
		v.expand("人");
		assertFalse(v.containsKey("人"));
		assertEquals(v.keySet().size(), 0);
	}
	
	@Test
	public void putWordsWithSameCharIntoV() {
		Vocabulary v = new Vocabulary();
		v.expand("中国人");
		v.expand("中国");
		v.expand("中华民族");
		v.expand("中华民国");
		v.expand("中间人");
		v.expand("中华人民共和国");
		v.expand("中华人民党");
		assertTrue(v.containsKey("中"));
		assertEquals(v.keySet().size(), 1);
		assertTrue(v.get("中").containsKey("国"));
		assertTrue(v.get("中").containsKey("间"));
		assertTrue(v.get("中").containsKey("华"));
		assertEquals(v.get("中").keySet().size(), 3);
		assertEquals(v.get("中").get("华").get("民").keySet().size(), 2);
		System.out.println(v);
	}
	
	@Test
	public void putSameWordTwice() {
		Vocabulary v = new Vocabulary();
		v.expand("中国人");
		assertTrue(v.containsKey("中"));
		assertEquals(v.keySet().size(), 1);
		assertTrue(v.get("中").containsKey("国"));
		assertEquals(v.get("中").keySet().size(), 1);
		assertTrue(v.get("中").get("国").containsKey("人"));
		assertEquals(v.get("中").get("国").keySet().size(), 1);
	}
}
