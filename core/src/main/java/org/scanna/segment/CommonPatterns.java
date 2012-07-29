/*
 * 
 */
package org.scanna.segment;

import org.scanna.segment.impl.AbstractSegmentPattern;
import org.scanna.segment.impl.AbstractSegmentPatternContext;
import org.scanna.segment.impl.BlockSegmentPattern;
import org.scanna.segment.impl.QuotedSegmentPattern;

/**
 * 
 * @author simonpai
 */
public class CommonPatterns {
	
	/** Line comment pattern. */
	public static final SegmentPattern LINE_COMMENT = 
			new AbstractSegmentPattern(Segment.COMMENT, "[Line Comment]") {
		public SegmentPatternContext match(String str, int index) {
			int i = str.indexOf("//", index);
			return i < 0 ? null : new AbstractSegmentPatternContext(i, _type) {
				public int end0(String str, int start) {
					return SegmentPattern.END_OF_LINE;
				}
			};
		}
	};
	
	/** Block comment pattern. */
	public static final SegmentPattern BLOCK_COMMENT = 
			new BlockSegmentPattern(Segment.COMMENT, "/*", "*/", "[Block Comment]");
	
	/** Documentation pattern. This will be hidden by {@link BLOCK_COMMENT}, 
	 * so it has to precede {@link BLOCK_COMMENT} in the pattern list to be 
	 * effective, if both are present.
	 */
	public static final SegmentPattern DOCUMENTATION = 
			new BlockSegmentPattern(Segment.DOCUMENTATION, "/**", "*/", "[Documentation]");
	
	/** Empty block comment pattern. This is introduced due to the fact that
	 * <tt>/**&#47;</tt> shall be considered as block comment. When 
	 * {@link DOCUMENTATION} and {@link BLOCK_COMMENT} are both present,
	 * this pattern has to precede them.
	 */
	//public static final SegmentPattern EMPTY_BLOCK_COMMENT = 
	//		new AbstractSegmentPattern(Segment.COMMENT, "[Block Comment]") {
	//	public int match(String str, int index) {
	//		return str.indexOf("/**/", index);
	//	}
	//	public int end(String str, int start) {
	//		return start + 4;
	//	}
	//};
	
	/** Single-quoted string pattern. */
	public static final SegmentPattern SINGLE_QUOTED = 
			new QuotedSegmentPattern(Segment.SINGLE_QUOTED, '\'', "[Single-Quoted]");
	
	/** Double-quoted string pattern. */
	public static final SegmentPattern DOUBLE_QUOTED = 
			new QuotedSegmentPattern(Segment.DOUBLE_QUOTED, '"', "[Double-Quoted]");
	
}
