/*
 * 
 */
package org.scanna.event;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.scanna.Line;
import org.scanna.event.impl.BasicScanner;
import org.scanna.struct.mapc.MapList;
import org.scanna.struct.mapc.impl.HashMapList;

/**
 * The utility to hierarchically capture patterns with {@link Scanner} in 
 * document, and post them as {@link Event}. 
 * @author simonpai
 */
public class EventEngine {
	
	/**
	 * The interface provided for {@link Scanner} to listen to {@link Event}
	 * posted by preceding Scanners.
	 * @author simonpai
	 */
	public interface Handlers<C> {
		
		/** Register a handler for the given Event name.
		 * @param name the Event name
		 */
		public <T> void add(String name, EventHandler<T, C> handler);
		
	}
	
	/**
	 * The interface provided for {@link Scanner} to post event and retrieve
	 * context object. 
	 * @author simonpai
	 */
	public interface Console<C> {
		
		/** Post an Event.
		 * @param column the column index where the Event occurs
		 * @param data Event data
		 */
		public void postEvent(String name, int column, Object data);
		
		/** Retrieve the current Line. */
		public Line getLine();
		
		/** Retrieve the context object.
		 * @see Scanner#newContext()
		 */
		public C getContext();
		
	}
	
	
	
	// initialization //
	protected final HandlerBundle<?>[] _hbs;
	protected final HandlerBundle<Object> _ghb;
	
	/**
	 * Construct an EventEngine with given Scanners. The engine will also 
	 * include their prerequisite Scanners and load them in order.
	 */
	public EventEngine(Scanner<?> ... scanners) {
		
		// install all dependent Scanners
		final Set<Scanner<?>> scnSet = new LinkedHashSet<Scanner<?>>();
		for (Scanner<?> s : scanners)
			install(scnSet, new Node<Scanner<?>>(null, s));
		
		// initialize handlers
		List<HandlerBundle<?>> hblist = new ArrayList<HandlerBundle<?>>();
		for (Scanner<?> s : scnSet)
			hblist.add(HandlerBundle.create(s));
		
		// global event handlers, wrapped into a proxy Scanner
		hblist.add(_ghb = HandlerBundle.create(new BasicScanner<Object>()));
		_hbs = hblist.toArray(new HandlerBundle[0]);
		
	}
	
	protected static void install(Set<Scanner<?>> set, Node<Scanner<?>> node) {
		if (set.contains(node.item))
			return;
		Scanner<?>[] prereq = node.item.getPrerequisites();
		if (prereq != null)
			for (Scanner<?> sc : prereq) {
				if (node.contains(sc))
					throw new IllegalArgumentException(
							"Scanner dependency cannot be cyclic: " + sc);
				install(set, new Node<Scanner<?>>(node, sc));
			}
		set.add(node.item);
	}
	
	protected static class Node<T> {
		
		protected final Node<T> parent;
		protected final T item;
		
		protected Node(Node<T> parent, T item) {
			this.parent = parent;
			this.item = item;
		}
		
		public boolean contains(T item) {
			for (Node<T> n = this; n != null; n = n.parent)
				if (item.equals(n.item))
					return true;
			return false;
		}
		
	}
	
	protected static class HandlerBundle<C> {
		
		protected final MapList<String, EventHandler<?, C>> _map = 
				new HashMapList<String, EventHandler<?, C>>();
		public final Scanner<C> scanner;
		
		protected HandlerBundle(Scanner<C> scanner) {
			this.scanner = scanner;
			scanner.doRegistration(new Handlers<C>() {
				public <T> void add(String name, EventHandler<T, C> handler) {
					_map.add(name, handler);
				}
			});
		}
		
		public void fireEvent(String name, int col, Object data, Console<C> console) {
			List<EventHandler<?, C>> list = _map.get(name);
			if (list != null)
				for (EventHandler<?, C> h : list)
					applyHandler(h, name, col, data, console);
		}
		
		@SuppressWarnings("unchecked")
		protected <T> void applyHandler(EventHandler<T, C> handler, String name, 
				int col, Object data, Console<C> console) {
			handler.run(new Event<T>(name, (T) data, console.getLine(), col), console);
		}
		
		public static <C> HandlerBundle<C> create(Scanner<C> scanner) {
			return new HandlerBundle<C>(scanner);
		}
		
	}
	
	/** Register a global {@link EventHandler}. The handler will be called after
	 * all the Scanners are served. (i.e., as if registered on an extra last
	 * Scanner of the engine.)
	 */
	public <T> void listen(String name, EventHandler<T, Object> handler) {
		_ghb._map.add(name, handler);
	}
	
	
	
	// run //
	/** Process an Iterable of Lines.
	 */
	public void run(Iterable<Line> lines) {
		
		// prepare queue and consoles
		final int slen = _hbs.length;
		final PriorityQueue<EventContent> queue = new PriorityQueue<EventContent>();
		final ConsoleBundle<?>[] cbs = new ConsoleBundle[slen];
		for (int i = 0; i < slen; i++)
			cbs[i] = ConsoleBundle.create(_hbs[i], queue);
		
		// process it line by line
		for (final Line line : lines) {
			queue.clear();
			for (int i = 0; i < slen; i++)
				cbs[i].run(line);
		}
		
		// TODO: call clean up (for context)
		
	}
	
	protected static class ConsoleBundle<C> {
		
		public final Console<C> console;
		public final PriorityQueue<EventContent> queue;
		protected final Scanner<C> _scanner;
		protected final HandlerBundle<C> _hbundle;
		protected Line _curr;
		
		protected ConsoleBundle(final HandlerBundle<C> hb, 
				final PriorityQueue<EventContent> queue) {
			_hbundle = hb;
			_scanner = hb.scanner;
			this.queue = queue;
			final C ctx = hb.scanner.newContext();
			console = new Console<C>() {
				public void postEvent(String name, int column, Object data) {
					queue.add(new EventContent(name, _curr, column, data));
				}
				public Line getLine() { return _curr; }
				public C getContext() { return ctx; }
			};
		}
		
		public void run(Line line) {
			_curr = line;
			_scanner.onStart(line, console);
			for (EventContent ec : queue)
				_hbundle.fireEvent(ec.name, ec.column, ec.data, console);
			_scanner.onEnd(line, console);
		}
		
		public static <C> ConsoleBundle<C> create(HandlerBundle<C> hb, 
				PriorityQueue<EventContent> queue) {
			return new ConsoleBundle<C>(hb, queue);
		}
		
	}
	
	protected static class EventContent implements Comparable<EventContent> {
		
		public final String name;
		public final Line line;
		public final int column;
		public final Object data;
		
		protected EventContent(String name, Line line, int column, Object data) {
			this.name = name;
			this.line = line;
			this.column = column;
			this.data = data;
		}
		
		@Override
		public int compareTo(EventContent ec) {
			return column - ec.column;
		}
		
	}
	
}
