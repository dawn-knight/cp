package com.dk.cp.common;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dk.cp.common.MarkedVocabulary.TokenNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MarkedVocabularyTest {

	@Test
	public void put2CharWordIntoEmptyV() {
		MarkedVocabulary v = new MarkedVocabulary();
		v.expand("人民");
		TokenNode ren = new TokenNode("人", false);
		TokenNode min = new TokenNode("民", true);
		assertTrue(v.containsKey(ren));
		assertTrue(v.get(ren).containsKey(min));
	}

	@Test
	public void put3CharWordIntoEmptyV() {
		MarkedVocabulary v = new MarkedVocabulary();
		v.expand("費曄暉");
		TokenNode fei = new TokenNode("費", false);
		TokenNode ye = new TokenNode("曄", false);
		TokenNode hui = new TokenNode("暉", true);
		assertTrue(v.containsKey(fei));
		assertTrue(v.get(fei).containsKey(ye));
		assertTrue(v.get(fei).get(ye).containsKey(hui));
	}
	
	@Test
	public void put2WordsWithNoSameCharIntoV() {
		MarkedVocabulary v = new MarkedVocabulary();
		v.expand("人民");
		v.expand("群众");
		TokenNode ren = new TokenNode("人", false);
		TokenNode min = new TokenNode("民", true);
		TokenNode qun = new TokenNode("群", false);
		TokenNode zhong = new TokenNode("众", true);
		assertTrue(v.containsKey(ren));
		assertTrue(v.containsKey(qun));
		assertTrue(v.get(ren).containsKey(min));
		assertTrue(v.get(qun).containsKey(zhong));
	}
	
	@Test
	public void put1CharWordIntoEmptyV() {
		MarkedVocabulary v = new MarkedVocabulary();
		v.expand("人");
		TokenNode ren = new TokenNode("人", true);
		assertFalse(v.containsKey(ren));
		assertEquals(v.keySet().size(), 0);
	}
	
	@Test
	public void putWordsWithSameCharIntoV() throws JsonProcessingException {
		MarkedVocabulary v = new MarkedVocabulary();
		v.expand("中国人");
		TokenNode zhong = new TokenNode("中", false);
		TokenNode guoF = new TokenNode("国", false);
		TokenNode renT = new TokenNode("人", true);
		assertTrue(v.containsKey(zhong));
		assertTrue(v.get(zhong).containsKey(guoF));
		assertTrue(v.get(zhong).get(guoF).containsKey(renT));
		
		v.expand("中国");
		TokenNode guoT = new TokenNode("国", true);
		assertTrue(v.containsKey(zhong));
		assertTrue(v.get(zhong).containsKey(guoT));
		assertTrue(v.get(zhong).get(guoT).containsKey(renT));
		
		v.expand("中华民族");
		v.expand("中华民国");
		TokenNode hua = new TokenNode("华", false);
		TokenNode min = new TokenNode("民", false);
		TokenNode zu = new TokenNode("族", true);
		assertTrue(v.get(zhong).get(hua).get(min).containsKey(zu));
		assertTrue(v.get(zhong).get(hua).get(min).containsKey(guoT));
		
		v.expand("中间人");
		v.expand("中华人民共和国");
		v.expand("中华人民党");
		TokenNode renF = new TokenNode("人", false);
		TokenNode gong = new TokenNode("共", false);
		TokenNode he = new TokenNode("和", false);
		TokenNode dang = new TokenNode("党", true);
		assertEquals(v.keySet().size(), 1);
		assertEquals(v.get(zhong).keySet().size(), 3);
		assertTrue(v.get(zhong).get(hua).get(renF).containsKey(min));
		assertEquals(v.get(zhong).get(hua).get(renF).get(min).keySet().size(), 2);
		assertTrue(v.get(zhong).get(hua).get(renF).get(min).get(gong).get(he).containsKey(guoT));
		assertTrue(v.get(zhong).get(hua).get(renF).get(min).containsKey(dang));
		System.out.println(new ObjectMapper().writeValueAsString(v));
	}
	
	@Test
	public void putSameWordTwice() {
		MarkedVocabulary v = new MarkedVocabulary();
		v.expand("中国人");
		v.expand("中国人");
		TokenNode zhong = new TokenNode("中", false);
		TokenNode guoF = new TokenNode("国", false);
		TokenNode renT = new TokenNode("人", true);
		assertTrue(v.containsKey(zhong));
		assertEquals(v.keySet().size(), 1);
		assertTrue(v.get(zhong).containsKey(guoF));
		assertEquals(v.get(zhong).keySet().size(), 1);
		assertTrue(v.get(zhong).get(guoF).containsKey(renT));
		assertEquals(v.get(zhong).get(guoF).keySet().size(), 1);
	}
}
