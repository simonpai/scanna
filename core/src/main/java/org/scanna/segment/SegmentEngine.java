/*
 * 
 */
package org.scanna.segment;

import java.util.ArrayList;
import java.util.List;

import org.scanna.document.Document;
import org.scanna.segment.SegmentPattern;
import org.scanna.segment.impl.CollectivePattern;
import org.scanna.struct.Converter;
import org.scanna.struct.Iterables;

import static org.scanna.segment.SegmentPattern.END_OF_LINE;
import static org.scanna.segment.SegmentPattern.NOT_FOUND;
import static org.scanna.segment.SegmentPattern.CONTINUED;

/**
 * 
 * @author simonpai
 */
public class SegmentEngine {
	
	protected final SegmentPattern _pptns;
	
	/** Construct a segment engine from given {@link SegmentPattern}.
	 */
	public SegmentEngine(SegmentPattern ... patterns) {
		_pptns = new CollectivePattern(patterns);
	}
	
	/** Return an iterable of {@link Line} from a {@link Document}.
	 */
	public Iterable<Line> run(final Document document) {
		return Iterables.convert(document.getContent(), 
				new Converter<String, Line>() {
			
			private SegmentationContext ctx = new SegmentationContext();
			
			public Line convert(String str) {
				// TODO: organize
				List<Segment> segs = segment(document, str, ctx);
				Line line = createLine(document, segs, ctx);
				ctx.nextRow();
				return line;
			}
		});
	}
	
	protected List<Segment> segment(Document document, String str, 
			SegmentationContext sctx) {
		List<Segment> res = new ArrayList<Segment>();
		Segment prev = null;
		
		// continue from previous line
		int currType = sctx.type();
		int row = sctx.row();
		SegmentPattern.Context currCtx = sctx.patternCtx();
		
		final int strlen = str.length();
		boolean first = true;
		
		for (int i = 0; i < strlen; ) {
			int j = NOT_FOUND;
			SegmentPattern.Context newCtx = null;
			int newType = Segment.RAW;
			
			if (currCtx == null) { // raw
				newCtx = _pptns.match(str, i);
				j = newCtx == null ? NOT_FOUND : newCtx.start();
			} else {
				int[] ends = currCtx.end(str, first ? CONTINUED : i);
				j = ends[0];
				newType = ends[1];
				if (j != NOT_FOUND && j != END_OF_LINE)
					newCtx = null;
			}
			first = false;
			
			// TODO: organize
			if (i != j)
				res.add(prev = createSegment(str, row, i, j, newType, prev));
			
			if (j == END_OF_LINE) {
				currCtx = null;
				currType = Segment.RAW;
				break;
			} else if (j == NOT_FOUND)
				break;
			
			i = j;
			currCtx = newCtx;
			currType = newType;
			
			// if a non-RAW blocked closed at line end, we need to insert an empty
			// RAW block to separate the first block of potentially the same type 
			if (j == strlen)
				res.add(createSegment(str, row, j, j, Segment.RAW, prev));
			
		}
		
		sctx.update(currCtx, currType);
		return res;
	}
	
	protected Segment createSegment(String str, int row, int start, int end, 
			int type, Segment previous) {
		return new Segment(row, start, end < 0 ? 
				str.substring(start) : str.substring(start, end), type, previous);
	}
	
	protected Line createLine(Document document, List<Segment> segments, 
			SegmentationContext ctx) {
		return new Line(document, ctx.row(), segments);
	}
	
	/**
	 * Internally used by {@link SegmentEngine}, which provides a context to
	 * keep track of information carried over lines.
	 */
	protected static class SegmentationContext {
		protected int _row = 1; // 1-based;
		protected SegmentPattern.Context _ctx = null;
		protected int _type = Segment.RAW;
		
		public SegmentPattern.Context patternCtx() { return _ctx; }
		public int type() { return _type; }
		public int row() { return _row; }
		
		public void update(SegmentPattern.Context ctx, int type) {
			_ctx = ctx;
			_type = type;
		}
		
		public void nextRow() {
			_row++;
		}
		
	}
	
}
