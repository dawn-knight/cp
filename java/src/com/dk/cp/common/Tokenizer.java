package com.dk.cp.common;

import java.util.Arrays;
import java.util.List;

public class Tokenizer {
	private Vocabulary v;

	public Vocabulary getV() {
		return v;
	}

	public void setV(Vocabulary v) {
		this.v = v;
	}

	public List<String> process(String raw) {
		if (raw != null && raw.length() > 0) {
			for (int i = 0; i < raw.length(); i++) {
				if (v.containsKey(raw.substring(i, i + 1))) {
					
				}
			}
		}
		return Arrays.asList(raw);
	}
}
