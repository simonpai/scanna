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
	public static final SegmentMask CODE = new SegmentMask() {
		public String mask(Segment segment) {
			switch(segment.getType()) {
			case Segment.COMMENT:
			case Segment.DOCUMENTATION:
				return Texts.nullify(segment.getContent());
			case Segment.SINGLE_QUOTED:
				return "'" + Texts.repeat('*', segment.getContent().length() - 2) + "'";
			case Segment.DOUBLE_QUOTED:
				return "\"" + Texts.repeat('*', segment.getContent().length() - 2) + "\"";
			}
			return segment.getContent();
		}
	};
	
	/** A mask that nullifies everything except documentation.
	 */
	public static final SegmentMask DOC = new SegmentMask() {
		public String mask(Segment segment) {
			if (segment.getType() == Segment.DOCUMENTATION)
				return segment.getContent();
			return Texts.nullify(segment.getContent());
		}
	};
	
}
