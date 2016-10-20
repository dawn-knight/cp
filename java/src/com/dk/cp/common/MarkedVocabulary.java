package com.dk.cp.common;

import java.util.HashMap;

import com.dk.cp.common.MarkedVocabulary.Token;

public class MarkedVocabulary extends HashMap<Token, MarkedVocabulary> {

	private static final long serialVersionUID = -3342988870845409143L;

	public void expand(String word) {
		expand(word, 0);
	}

	private void expand(String word, int startPos) {
		if (word.length() > 1) {
			boolean putStraightly = true;
			Token tokenNeedMark = null;
			for (Token key : keySet()) {
				if (word.substring(startPos, startPos + 1).equals(
						key.getPayload())) {
					putStraightly = false;
					if (startPos + 1 < word.length()) {
						MarkedVocabulary subMv = get(key);
						subMv.expand(word, startPos + 1);
					} else {
						tokenNeedMark = key;
					}
					break;
				}
			}
			if (putStraightly) {
				MarkedVocabulary subMv = new MarkedVocabulary();
				if (startPos + 1 < word.length()) {
					subMv.expand(word, startPos + 1);
				}
				Token token = new Token(word.substring(startPos, startPos + 1));
				if (startPos + 1 < word.length()) {
					token.setCanStopHere(false);
				} else {
					token.setCanStopHere(true);
				}
				put(token, subMv);
			}
			if (tokenNeedMark != null) {
				MarkedVocabulary subMv = get(tokenNeedMark);
				remove(tokenNeedMark);
				tokenNeedMark.setCanStopHere(true);
				put(tokenNeedMark, subMv);
			}
		}
	}

	static class Token {
		private String payload;
		private boolean canStopHere;

		public Token(String payload) {
			this.payload = payload;
		}

		public Token(String payload, boolean canStopHere) {
			this.payload = payload;
			this.canStopHere = canStopHere;
		}

		public String getPayload() {
			return payload;
		}

		public void setPayload(String payload) {
			this.payload = payload;
		}

		public boolean isCanStopHere() {
			return canStopHere;
		}

		public void setCanStopHere(boolean canStopHere) {
			this.canStopHere = canStopHere;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (canStopHere ? 1231 : 1237);
			result = prime * result
					+ ((payload == null) ? 0 : payload.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Token))
				return false;
			Token other = (Token) obj;
			if (canStopHere != other.canStopHere)
				return false;
			if (payload == null) {
				if (other.payload != null)
					return false;
			} else if (!payload.equals(other.payload))
				return false;
			return true;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Token [payload=").append(payload)
					.append(", canStopHere=").append(canStopHere).append("]");
			return builder.toString();
		}
	}
}
