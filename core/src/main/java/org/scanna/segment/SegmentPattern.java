package org.scanna.segment;

import org.scanna.util.Texts;

/**
 * 
 * @author simonpai
 */
public abstract class SegmentPattern {
	
	public static final int NOT_FOUND = -1;
	public static final int END_OF_LINE = -2;
	
	protected final int _type;
	
	public SegmentPattern(int type) {
		_type = type;
	}
	
	public abstract int findOpenIndex(String str, int index);
	
	public abstract int findCloseIndex(String str, int index, boolean continued);
	
	/**
	 * 
	 * @param str
	 * @param start
	 * @param end
	 */
	public int getType(String str, int start, int end) {
		return _type;
	}
	
	// TODO: exception handling
	
	
	
	// common patterns //
	/** Line comment pattern. */
	public static final SegmentPattern LINE_COMMENT = 
			new SegmentPattern(Segment.COMMENT) {
		public int findOpenIndex(String str, int index) {
			return str.indexOf("//", index);
		}
		public int findCloseIndex(String str, int index, boolean continued) {
			return END_OF_LINE;
		}
		public String toString() { return "[Line Comment]"; }
	};
	
	/** Block comment pattern. */
	public static final SegmentPattern BLOCK_COMMENT = 
			new SegmentPattern(Segment.COMMENT) {
		public int findOpenIndex(String str, int index) {
			return str.indexOf("/*", index);
		}
		public int findCloseIndex(String str, int index, boolean continued) {
			int j = str.indexOf("*/", index + (continued ? 0 : 2));
			return j < 0 ? NOT_FOUND : j + 2;
		}
		public String toString() { return "[Block Comment]"; }
	};
	
	/** Documentation pattern. This will be hidden by {@link BLOCK_COMMENT}, 
	 * so it has to precede {@link BLOCK_COMMENT} in the pattern list to be 
	 * effective, if both are present.
	 */
	public static final SegmentPattern DOCUMENTATION = 
			new SegmentPattern(Segment.DOCUMENTATION) {
		public int findOpenIndex(String str, int index) {
			return str.indexOf("/**", index);
		}
		public int findCloseIndex(String str, int index, boolean continued) {
			int j = str.indexOf("*/", index + (continued ? 0 : 3));
			return j < 0 ? NOT_FOUND : j + 2;
		}
		public String toString() { return "[Documentation]"; }
	};
	
	/** Empty block comment pattern. This is introduced due to the fact that
	 * <tt>/**&#47;</tt> shall be considered as block comment. When 
	 * {@link DOCUMENTATION} and {@link BLOCK_COMMENT} are both present,
	 * this pattern has to precede them.
	 */
	public static final SegmentPattern EMPTY_BLOCK_COMMENT = 
			new SegmentPattern(Segment.COMMENT) {
		public int findOpenIndex(String str, int index) {
			return str.indexOf("/**/", index);
		}
		public int findCloseIndex(String str, int index, boolean continued) {
			return index + 4;
		}
		public String toString() { return "[Block Comment]"; }
	};
	
	/** Single-quoted string pattern. */
	public static final SegmentPattern SINGLE_QUOTED = 
			new SegmentPattern(Segment.SINGLE_QUOTED) {
		public int findOpenIndex(String str, int index) {
			return str.indexOf('\'', index);
		}
		public int findCloseIndex(String str, int index, boolean continued) {
			int j = Texts.indexOfUnescaped(str, '\'', '\\', index + (continued ? 0 : 1));
			return j < 0 ? NOT_FOUND : j + 1;
		}
		public String toString() { return "[Single-Quoted]"; }
	};
	
	/** Double-quoted string pattern. */
	public static final SegmentPattern DOUBLE_QUOTED = 
			new SegmentPattern(Segment.DOUBLE_QUOTED) {
		public int findOpenIndex(String str, int index) {
			return str.indexOf('"', index);
		}
		public int findCloseIndex(String str, int index, boolean continued) {
			int j = Texts.indexOfUnescaped(str, '"', '\\', index + (continued ? 0 : 1));
			return j < 0 ? NOT_FOUND : j + 1;
		}
		public String toString() { return "[Double-Quoted]"; }
	};
	
}
