package com.dk.cp.common;

import java.util.ArrayList;
import java.util.List;

import com.dk.cp.common.MarkedVocabulary.TokenNode;

public class Tokenizer {
	private MarkedVocabulary mv;

	public MarkedVocabulary getMv() {
		return mv;
	}

	public void setMv(MarkedVocabulary mv) {
		this.mv = mv;
	}

	public List<String> process(String raw) {
		List<String> processed = new ArrayList<String>();
		if (raw != null && raw.length() > 0) {
			StringBuffer sb4AlphanumericCode = null;
			for (int i = 0; i < raw.length();) {
				if (Character.isLetterOrDigit(raw.codePointAt(i))) {
					if (Character.isIdeographic(raw.codePointAt(i))) {
						unloadSb(sb4AlphanumericCode, processed);
						List<Integer> endPosL = getEndPosL(raw, i, mv);
						if (endPosL != null) {
							int nextStartPos = i + 1;
							for (Integer endPos : endPosL) {
								processed.add(raw.substring(i, endPos));
								nextStartPos = endPos;
							}
							i = nextStartPos;
						} else {
							processed.add(raw.substring(i, i + 1));
							i++;
						}
					} else {
						if (sb4AlphanumericCode == null)
							sb4AlphanumericCode = new StringBuffer();
						sb4AlphanumericCode.append(raw.charAt(i));
						i++;
					}
				} else {
					unloadSb(sb4AlphanumericCode, processed);
					i++;
				}
			}
		}
		return processed;
	}
	
	private void unloadSb(StringBuffer sb, List<String> processed) {
		if (sb != null && sb.length() > 0) {
			processed.add(sb.toString());
			sb.delete(0, sb.length());
		}
	}

	private List<Integer> getEndPosL(String raw, int pos, MarkedVocabulary mv) {
		if (raw != null && raw.length() > 0 && pos < raw.length()) {
			List<Integer> endPosL = null;
			String s = raw.substring(pos, pos + 1);
			TokenNode stopNode = new TokenNode(s, true);
			TokenNode nonStopNode = new TokenNode(s, false);
			List<Integer> latterEndPosL = null;
			if (mv.containsKey(stopNode)) {
				if (endPosL == null)
					endPosL = new ArrayList<Integer>();
				endPosL.add(pos + 1);
				latterEndPosL = getEndPosL(raw, pos + 1, mv.get(stopNode));
			} else if (mv.containsKey(nonStopNode)) {
				latterEndPosL = getEndPosL(raw, pos + 1, mv.get(nonStopNode));
			}
			if (latterEndPosL != null) {
				if (endPosL == null)
					endPosL = new ArrayList<Integer>();
				endPosL.addAll(latterEndPosL);
			}
			return endPosL;
		}
		return null;
	}

}
