/*
 * 
 */
package org.scanna;

import org.scanna.segment.Segment;
import org.scanna.segment.SegmentMask;
import org.scanna.segment.SegmentPattern;
import org.scanna.segment.impl.AbstractPattern;
import org.scanna.segment.impl.BlockPattern;
import org.scanna.segment.impl.FixedStringPattern;
import org.scanna.segment.impl.LinePattern;
import org.scanna.segment.impl.QuotedPattern;
import org.scanna.segment.impl.SimpleContext;
import org.scanna.util.Texts;

/**
 * Collection of commonly used implementations of {@link SegmentPattern} and 
 * {@link SegmentMask}, which falls under {@link Patterns} and {@link Masks}, 
 * respectively.
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
		
		/** A pattern that captures both documentation and block comment. It
		 * also correctly handles the case that <tt>/**&#47;</tt> shall be 
		 * considered as block comment.
		 */
		public static final SegmentPattern DOCUMENTATION_AND_BLOCK_COMMENT = 
				new AbstractPattern(0, "[DOC / BLOCK COMMENT]") {
			public Context match(String str, int index) {
				int i = str.indexOf("/*", index);
				if (i < 0)
					return null;
				int len = str.length();
				boolean doc = len > i + 2 && str.charAt(i + 2) == '*'
						&& (len < i + 4 || str.charAt(i + 3) != '/'); // exclude /**/
				int type = doc ? Segment.DOCUMENTATION : Segment.COMMENT;
				return new SimpleContext(i, type) {
					public int end0(String str, int start) {
						int st = start == SegmentPattern.CONTINUED ? 0 : start + 2;
						int j = str.indexOf("*/", st);
						return j < 0 ? SegmentPattern.NOT_FOUND : j;
					}
				};
			}
		};
		
		/** Single-quoted string pattern. */
		public static final SegmentPattern SINGLE_QUOTED = 
				new QuotedPattern(Segment.SINGLE_QUOTED, '\'', "[Single-Quoted]");
		
		/** Double-quoted string pattern. */
		public static final SegmentPattern DOUBLE_QUOTED = 
				new QuotedPattern(Segment.DOUBLE_QUOTED, '"', "[Double-Quoted]");
		
		/** Left brace (curly bracket) */
		public static final SegmentPattern LEFT_BRACE = 
				new FixedStringPattern(Segment.LEFT_BRACE, "{", "[Left Brace]");
		
		/** Right brace (curly bracket) */
		public static final SegmentPattern RIGHT_BRACE = 
				new FixedStringPattern(Segment.RIGHT_BRACE, "}", "[Right Brace]");
		
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
