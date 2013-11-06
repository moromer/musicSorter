package de.marm.Fields;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

import de.marm.Typ.MP3;


public class TestMusicGrid {
	
	private de.marm.Fields.MusicGrid musicGrid;
	
	@Before
	public void prepare(){
		musicGrid = de.marm.Fields.MusicGrid.getInstance(new Shell(), "test");
	}

	@Test
	public void initialize() {
		assertEquals("check if initial musicGrid object is correct", de.marm.Fields.MusicGrid.class, musicGrid.getClass());
		
		de.marm.Fields.MusicGrid musicGrid2 = de.marm.Fields.MusicGrid.getInstance();
		assertEquals("check if second musicGrid object is the same as initial", musicGrid, musicGrid2);
	}
	
	@Test
	public void testAdd() {
		MP3 music = new MP3("Michael Jackson", "Black & White", "Black or White");
		System.out.println(musicGrid);
		musicGrid.addData(music);
	}
	
	@Test 
	public void checkItemList() {
		ArrayList<MP3> items = musicGrid.getItemList();
		
		MP3 music = items.get(0);

		assertEquals("correct correct length", 1, items.size());
		assertEquals("item is correct","Michael Jackson|Black & White|Black or White", music.toString());
		
		MP3 newMusic = new MP3("Parov Stelar", "Art of Sampling", "Matilda");
		musicGrid.addData(newMusic);
		
		assertEquals("correct correct length", 2, items.size());
		
		assertEquals("item is correct","Michael Jackson|Black & White|Black or White", music.toString());
		assertEquals("item is correct","Parov Stelar|Art of Sampling|Matilda", items.get(1).toString());
		
		
		
		
		
	}

}
