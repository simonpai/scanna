/*
 * 
 */
package org.scanna.event.impl;

import org.scanna.Line;
import org.scanna.event.Scanner;
import org.scanna.event.EventEngine.Console;
import org.scanna.event.EventEngine.Handlers;

/**
 * The skeleton/null implementation of {@link Scanner}.
 * @author simonpai
 */
public class BasicScanner<C> implements Scanner<C> {
	
	@Override
	public void doRegistration(Handlers<C> handlers) {}
	
	@Override
	public Scanner<?>[] getPrerequisites() { return null; }
	
	@Override
	public void onStart(Line line, Console<C> console) {}
	
	@Override
	public void onEnd(Line line, Console<C> console) {}
	
	@Override
	public C newContext() { return null; }
	
}
