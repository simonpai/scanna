/*
 * 
 */
package org.scanna.segment;

import org.scanna.util.Texts;

/**
 * Common implementation of {@link SegmentTask}.
 * @author simonpai
 */
public class CommonMasks {
	
	/** A mask that nullifies comment/documentation and replaces single/double
	 * quoted content with <tt>*</tt>.
	 */
	public static final SegmentMask CODE = new CodeMask();
	
	/** A mask that nullifies everything except documentation.
	 */
	public static final SegmentMask DOC = new DocMask();
	
	
	
	/** The {@link CommonMasks#CODE} mask implementation class, exposed for easier overriding.
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
	
	/** The {@link CommonMasks#DOC} mask implementation class, exposed for easier overriding.
	 */
	public static class DocMask implements SegmentMask {
		public String mask(Segment segment) {
			if (segment.type() == Segment.DOCUMENTATION)
				return segment.content();
			return Texts.nullify(segment.content());
		}
	}
	
}
