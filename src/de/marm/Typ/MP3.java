package de.marm.Typ;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MP3 {
	private String interpret;
	private String album;
	private String title;
	private File mp3File;

	public static String DEFAULT_INTERPRET = "UNSORTED";
	public static String DEFAULT_ALBUM = "NO_ALBUM_DEFINED";

	public MP3(String interpret, String album, String title) {
		this.interpret = interpret;
		this.album = album;
		this.title = title;
	}

	public MP3(File mp3File) {
		this.mp3File = mp3File;
	}

	public String[] getData() {
		return new String[] { this.interpret, this.album, this.title };
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

	// TODO: check performance
	public void setmp3File(File mp3File) {
		this.mp3File = mp3File;
	}

	public File getmp3File() {
		return mp3File;
	}

	public boolean isValidFile() {
		return mp3File.getName().matches(".*\\.mp3$");
	}

	public String toString() {
		return this.interpret + "|" + this.album + "|" + this.title;
	}

	public void readValuesFromMp3Tag() {
		if (mp3File.isFile()) {
			if(!readValuesFromMP3Tag("UTF-8")) {
				if(!readValuesFromMP3Tag("US-ASCII")) {
					interpret = MP3.DEFAULT_INTERPRET;
					album = MP3.DEFAULT_ALBUM;
					title = mp3File.getName();
				}
			}
		}
	}

	private boolean readValuesFromMP3Tag(String encodeType) {
		try {
			RandomAccessFile ranFile = new RandomAccessFile(mp3File, "r");

			if (ranFile.length() >= 128) {
				byte[] bytearr = new byte[128];
				ranFile.seek(ranFile.length() - 128);
				ranFile.read(bytearr, 0, 128);
				String a = new String(bytearr, encodeType);

				if (a.substring(0, 3).equals("TAG")) {
					interpret = a.substring(33, 63).trim();
					album     = a.substring(63, 93).trim();
					title     = a.substring(3, 33).trim();

				} else {
					System.out.println("no tag information readable");
					System.out.println(a);
					
					ranFile.close();
					return false;
				}

				// System.out.println("TITEL: " + a.substring(3,
				// 33).trim());
				// System.out.println("ARTIST: " + a.substring(33,
				// 63).trim());
				// System.out.println("ALBUM: " + a.substring(63,
				// 93).trim());
				// System.out.println("YEAR: " + a.substring(93,
				// 97).trim());
				// System.out.println("COMMENT: " + a.substring(97,
				// 126).trim());
				// System.out.println("GENRE: " + bytearr[127]);

			}
			ranFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
}
