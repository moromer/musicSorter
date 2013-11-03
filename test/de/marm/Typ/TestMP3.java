package de.marm.Typ;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMP3 {
	
	private de.marm.Typ.MP3 music;
	
	@Before
	public void prepare() {
		music = new de.marm.Typ.MP3("Michael Jackson", "Black & White", "Black and White");
	}

	@Test
	public void createNewWithValidFields() {
		
		assertEquals("get correct Interpret", "Michael Jackson", music.getInterpret());
		assertEquals("get correct album",     "Black & White",   music.getAlbum());
		assertEquals("get correct title",     "Black and White", music.getTitle());
		assertEquals("no path specified", null, music.getPath());
		
	}
	
	@Test
	public void checkToString() {
		assertEquals("get correctString", "Michael Jackson|Black & White|Black and White", music.toString());
	}
	
	@Test
	public void changeValues() {
		music.setInterpret("Parov Stelar");
		music.setAlbum("The Art of Sampling");
		music.setTitle("Keep on Dancing");
		music.setPath("/home/mario/Music/");
		
		assertEquals("get correct Interpret", "Parov Stelar", music.getInterpret());
		assertEquals("get correct album",     "The Art of Sampling",   music.getAlbum());
		assertEquals("get correct title",     "Keep on Dancing", music.getTitle());
		assertEquals("no path specified",     "/home/mario/Music/", music.getPath());
	}

}
