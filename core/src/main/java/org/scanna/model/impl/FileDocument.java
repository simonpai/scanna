/*
 * 
 */
package org.scanna.model.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @author simonpai
 */
public class FileDocument extends AbstractDocument {
	
	private static final String _FILE_SEP = System.getProperty("file.separator");
	private static final boolean _REP_SEP = !"/".equals(_FILE_SEP);
	
	private final String _rawPath, _path, _ext;
	private final File _file; 
	
	public FileDocument(String path) {
		this(new File(path));
	}
	
	public FileDocument(String path, String name) {
		this(new File(path), name);
	}
	
	public FileDocument(File file) {
		this(file, fileName(file));
	}
	
	public FileDocument(File file, String name) {
		super(name);
		_file = file;
		_rawPath = file.getPath();
		_path = _REP_SEP ? _rawPath.replaceAll(Pattern.quote(_FILE_SEP), "/") : _rawPath;
		
		int i = _path.lastIndexOf("/");
		int j = _path.lastIndexOf(".");
		_ext = j > 0 && i < j ? _path.substring(j) : null;
	}
	
	/** Return raw path.
	 */
	public String getRawPath() { return _rawPath; }
	
	/** Return path, with system specific file separator character replaced by 
	 * slash.
	 */
	public String getPath() { return _path; }
	
	/** Return the file extension based on its file name. If no comma is present
	 * in the file name, <tt>null</tt> is returned.
	 */
	public String getExtension() { return _ext; }
	
	// line cache
	private List<String> _lines;
	
	@Override
	public List<String> getContent() {
		if (_lines == null) {
			_lines = new ArrayList<String>();
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(_file));
				for(String line; (line = br.readLine()) != null;)
					_lines.add(line);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (br != null) br.close();
				} catch (IOException e) {} finally {}
			}
		}
		return Collections.unmodifiableList(_lines);
	}
	
	/** Clear the cache of document content.
	 */
	public void clearCache() {
		_lines = null;
	}
	
	@Override
	public String toString() {
		return _name; // TODO: better
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_file == null) ? 0 : _file.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDocument other = (FileDocument) obj;
		if (_file == null) {
			if (other._file != null)
				return false;
		} else if (!_file.equals(other._file))
			return false;
		return true;
	}
	
	// helper //
	protected static String fileName(File file) {
		String path = file.getPath();
		int i = path.lastIndexOf('/');
		return i < 0 ? path : path.substring(i+1);
	}
	
}
