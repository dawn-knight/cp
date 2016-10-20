package com.dk.cp.common;

import java.util.HashMap;
import java.util.Set;

public class Vocabulary extends HashMap<String, Vocabulary> {

	private static final long serialVersionUID = -2069405678869357184L;
	
	public void expand(String word) {
		expand(word, 0);
	}

	private void expand(String word, int startPos) {
		if (startPos < word.length() && word.length() > 1) {
			boolean putStraightly = true;
			Set<String> keys = keySet();
			for (String key : keys) {
				if (word.substring(startPos, startPos + 1).equals(key)) {
					putStraightly = false;
					Vocabulary subV = get(key);
					subV.expand(word, startPos + 1);
					break;
				}
			}
			if (putStraightly) {
				Vocabulary subV = new Vocabulary();
				if (startPos + 1 < word.length()) {
					subV.expand(word, startPos + 1);
				}
				put(word.substring(startPos, startPos + 1), subV);
			}
		}
	}
}
