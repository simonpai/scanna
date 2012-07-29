/*
 * 
 */
package org.scanna.segment;

import org.scanna.segment.impl.*;

/**
 * Commonly used {@link SegmentPattern}.
 * @author simonpai
 */
public class CommonPatterns {
	
	/** Line comment pattern. */
	public static final SegmentPattern LINE_COMMENT = 
			new LinePattern("//", Segment.COMMENT, "[Line Comment]");
	
	/** Block comment pattern. */
	public static final SegmentPattern BLOCK_COMMENT = 
			new BlockPattern(Segment.COMMENT, "/*", "*/", "[Block Comment]");
	
	/** Documentation pattern. This will be hidden by {@link BLOCK_COMMENT}, 
	 * so it has to precede {@link BLOCK_COMMENT} in the pattern list to be 
	 * effective, if both are present.
	 */
	public static final SegmentPattern DOCUMENTATION = 
			new BlockPattern(Segment.DOCUMENTATION, "/**", "*/", "[Documentation]");
	
	// TODO: DOCUMENTATION_AND_BLOCK_COMMENT
	
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
			new QuotedPattern(Segment.SINGLE_QUOTED, '\'', "[Single-Quoted]");
	
	/** Double-quoted string pattern. */
	public static final SegmentPattern DOUBLE_QUOTED = 
			new QuotedPattern(Segment.DOUBLE_QUOTED, '"', "[Double-Quoted]");
	
}
