/*
 * 
 */
package org.scanna.document.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;

import org.scanna.struct.Converter;
import org.scanna.struct.iter.Iterables;
import org.scanna.struct.iter.JointIterator;
import org.scanna.struct.iter.SingletonIterator;

/**
 * 
 * @author simonpai
 */
public class DirectoryDocumentCollection implements Iterable<FileDocument> {
	
	private final File _root;
	
	public DirectoryDocumentCollection(String path) {
		this(new File(path));
	}
	
	public DirectoryDocumentCollection(File root) {
		_root = root;
	}
	
	@Override
	public Iterator<FileDocument> iterator() {
		return _root.isFile() ? new _SingleFileIterable(_root).iterator() : 
			new JointIterator<FileDocument>(Iterables.convert(
				Arrays.asList(_root.listFiles()), 
				new Converter<File, Iterable<FileDocument>>() {
			public Iterable<FileDocument> convert(File file) {
				return new DirectoryDocumentCollection(file);
			}
		}).iterator());
	}
	
	private static class _SingleFileIterable implements Iterable<FileDocument> {
		
		// TODO: supply document name
		private final File _file;
		private _SingleFileIterable(File file) { _file = file; }
		
		@Override
		public Iterator<FileDocument> iterator() {
			return new SingletonIterator<FileDocument>(new FileDocument(_file));
		}
	}
	
}
