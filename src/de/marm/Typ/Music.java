package de.marm.Typ;

public class Music {
	private String interpret;
	private String album;
	private String title;
	private String path;
	
	public static String DEFAULT_INTERPRET = "UNSORTED";
	public static String DEFAULT_ALBUM     = "NO_ALBUM_DEFINED";
	

	public Music(String interpret, String album, String title) {
		this.interpret = interpret;
		this.album     = album;
		this.title     = title;
	}
	
	public Music(String interpret, String album, String title, String path) {
		this.interpret = interpret;
		this.album     = album;
		this.title     = title;
		this.path      = path;
	}
	
	public String[] getData() {
		return new String[] {this.interpret, this.album, this.title};
	}

	public String getInterpret() {
		return interpret;
	}

	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String toString() {
		return this.interpret + "|" + this.album + "|" + this.title;
	}
	
	
	
}

