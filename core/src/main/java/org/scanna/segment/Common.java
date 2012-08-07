/*
 * 
 */
package org.scanna.segment;

import org.scanna.segment.impl.BlockPattern;
import org.scanna.segment.impl.LinePattern;
import org.scanna.segment.impl.QuotedPattern;
import org.scanna.util.Texts;

/**
 * Collection of commonly used implementations of {@link SegmentPattern} and 
 * {@link SegmentMask}, which falls under {@link Patterns} and 
 * {@link Masks}, respectively.
 * @author simonpai
 */
public class Common {
	
	/**
	 * Commonly used {@link SegmentPattern}.
	 * @author simonpai
	 */
	public static class Patterns {
		
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
	
	
	
	/**
	 * Commonly used {@link SegmentMask}.
	 * @author simonpai
	 */
	public static class Masks {
		
		/** A mask that nullifies comment/documentation and replaces single/double
		 * quoted content with <tt>*</tt>.
		 */
		public static final SegmentMask CODE = new CodeMask();
		
		/** A mask that nullifies everything except documentation.
		 */
		public static final SegmentMask DOC = new DocMask();
		
		/** The {@link Masks#CODE} mask implementation class, exposed for easier overriding.
		 */
		public static class CodeMask implements SegmentMask {
			public String mask(Segment segment) {
				switch(segment.type()) {
				case Segment.COMMENT:
				case Segment.DOCUMENTATION:
					return Texts.nullify(segment.content());
				case Segment.SINGLE_QUOTED:
					return "'" + Texts.repeat('*', segment.content().length() - 2) + "'";
				case Segment.DOUBLE_QUOTED:
					return "\"" + Texts.repeat('*', segment.content().length() - 2) + "\"";
				}
				return segment.content();
			}
		}
		
		/** The {@link Masks#DOC} mask implementation class, exposed for easier overriding.
		 */
		public static class DocMask implements SegmentMask {
			public String mask(Segment segment) {
				if (segment.type() == Segment.DOCUMENTATION)
					return segment.content();
				return Texts.nullify(segment.content());
			}
		}
		
	}
	
}
