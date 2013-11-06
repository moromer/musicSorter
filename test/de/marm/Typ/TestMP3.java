package de.marm.Typ;

import static org.junit.Assert.*;

import java.io.File;

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
		assertEquals("no File specified", null, music.getmp3File());
		
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
		music.setmp3File(new File("/home/mario/Music/aaa.mp3"));
		
		assertEquals("get correct Interpret", "Parov Stelar", music.getInterpret());
		assertEquals("get correct album",     "The Art of Sampling",   music.getAlbum());
		assertEquals("get correct title",     "Keep on Dancing", music.getTitle());
		assertEquals("no path specified",     "aaa.mp3", music.getmp3File().getName());
	}

}
