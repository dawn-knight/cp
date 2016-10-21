package com.dk.cp.common;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TokenizerTest {
	
	private static MarkedVocabulary mv;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mv = new MarkedVocabulary();
		mv.expand("中国");
		mv.expand("中国人");
		mv.expand("火星");
		mv.expand("火星人");
		mv.expand("殖民");
		mv.expand("殖民地");
		mv.expand("一定");
		mv.expand("作为");
		mv.expand("代表");
		mv.expand("费晔晖");
		mv.expand("潜力");
		mv.expand("科技");
		mv.expand("创新");
		mv.expand("创新力");
		mv.expand("企业");
		mv.expand("领导者");
		mv.expand("领导");
		mv.expand("美利坚合众国");
		mv.expand("美国");
		mv.expand("美国人");
		mv.expand("全称");
	}

	@Test
	public void testOnylHan1() {
		Tokenizer t = new Tokenizer();
		t.setMv(mv);
		List<String> processed = t.process("我作为一个中国人一定要代表中国殖民火星");
		System.out.println(processed);
		assertEquals(12, processed.size());
	}

	@Test
	public void testOnlyHan2() {
		Tokenizer t = new Tokenizer();
		t.setMv(mv);
		List<String> processed = t.process("费晔晖是中国最有潜力的科技创新企业的领导者");
		System.out.println(processed);
		assertEquals(13, processed.size());
	}

	@Test
	public void testOnlyHan3() {
		Tokenizer t = new Tokenizer();
		t.setMv(mv);
		List<String> processed = t.process("美国的全称是美利坚合众国");
		System.out.println(processed);
		assertEquals(5, processed.size());
	}
	
	@Test
	public void printAllUtf16Char() throws IOException {
		File f = new File("utf16.txt");
		if(f.exists())
			f.delete();
		f.createNewFile();
		FileWriter fw = new FileWriter(f);
		for (int i = 0; i < 65536; i++) {
			fw.append((char)i);
			fw.append('\n');
		}
	}

}
