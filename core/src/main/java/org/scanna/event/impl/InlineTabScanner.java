/*
 * 
 */
package org.scanna.event.impl;

import org.scanna.Common;
import org.scanna.Line;
import org.scanna.event.EventEngine.Console;
import org.scanna.util.Texts;

/** The scanner that searches for in-line tabs. (i.e. tab characters that are 
 * not at the beginning of a line) 
 * @author simonpai
 */
public class InlineTabScanner extends BasicScanner<Object> {
	
	private static final int INIT = 0;
	private static final int IN_TAB = 1;
	private static final int NOT_IN_TAB = 2;
	
	@Override
	public void onStart(Line line, Console<Object> console) {
		String txt = mask(line);
		if (txt != null)
			process(txt, console);
	}
	
	/** Process the scan.
	 */
	protected void process(String txt, Console<Object> console) {
		int state = INIT;
		int i = -1;
		for (int j = 0; j < txt.length(); j++) {
			char c = txt.charAt(j);
			switch (state) {
			case INIT:
				if (!Character.isWhitespace(c))
					state = NOT_IN_TAB;
				break;
				
			case IN_TAB:
				if (c != '\t') {
					flush(i, j, console);
					state = NOT_IN_TAB;
				}
				break;
				
			case NOT_IN_TAB:
			default:
				if (c == '\t') {
					i = j;
					state = IN_TAB;
				}
			}
		}
		
		// flush last IN_TAB sequence, if any
		if (state == IN_TAB)
			flush(i, txt.length(), console);
	}
	
	/** Mask the Line to String for processing.
	 * @return null to skip this line.
	 */
	protected String mask(Line line) {
		String txt = line.getContent(Common.Masks.CODE);
		return Texts.isBlank(txt) ? null : txt;
	}
	
	/** Post the in-line tab event.
	 */
	protected final void flush(int col0, int col1, Console<Object> console) {
		console.postEvent(Common.Events.ON_INLINE_TAB, col0, col1 - col0);
	}
	
}
