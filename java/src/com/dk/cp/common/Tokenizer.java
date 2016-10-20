package com.dk.cp.common;

import java.util.ArrayList;
import java.util.List;

import com.dk.cp.common.MarkedVocabulary.TokenNode;

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

	private int getEndPos(String raw, int pos, Vocabulary v) {
		if (raw != null && raw.length() > 0 && pos < raw.length()) {
			String s = raw.substring(pos, pos + 1);
			TokenNode stopNode = new TokenNode(s, true);
			TokenNode nonStopNode = new TokenNode(s, false);
			if (v.containsKey(stopNode)) {
				getEndPos(raw, pos + 1, v.get(stopNode));
			} else if (v.containsKey(nonStopNode)) {
				getEndPos(raw, pos + 1, v.get(nonStopNode));
			} else {
				return pos;
			}
		}
		return 0;
	}
}
