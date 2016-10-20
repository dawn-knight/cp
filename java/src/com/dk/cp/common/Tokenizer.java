package com.dk.cp.common;

import java.util.ArrayList;
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
		List<String> processed = new ArrayList<String>();
		if (raw != null && raw.length() > 0) {

		}
		return processed;
	}

	private List<String> process(String raw, int pos, Vocabulary v) {
		if (raw != null && raw.length() > 0 && pos < raw.length()) {
			String s = raw.substring(pos, pos + 1);
			if(v.keySet().contains(s)) {
				process(raw, pos+1, v.get(s));
			} else {
				
			}
		}
		return null;
	}
}
