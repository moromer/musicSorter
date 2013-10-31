package de.marm.Fields;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import de.marm.Typ.Music;

public class MusicGrid{
	
	private Table table;
//	TODO: this arraylist should be 2 dimensional. So that i can specifiy if item x is visible or not
	private ArrayList<Music> items;
	private static MusicGrid instance = null;

	private MusicGrid() {
		
	}
	
	public ArrayList<Music> getItemList() {
		return items;
	}
	
	public void addData(Music music) {
		TableItem item = new TableItem(table, SWT.None);
		
		item.setText(music.getData());
		this.items.add(music);
	}
	
	
	public static MusicGrid getInstance() {
		return instance;
	}
	
	public static MusicGrid getInstance(Composite parent, String description) {
		if(instance == null) {
			instance = new MusicGrid(parent, description);
		}
		return instance;
	}
	
	private void createMusicGrid(Composite parent, String description, Color bg, GridData grid) {
		table = new Table(parent, SWT.BORDER);
		table.setLayoutData(grid);
		
		TableColumn cInterpret = new TableColumn(table, SWT.CENTER);
		cInterpret.setText("Interpret");
		cInterpret.setWidth(200);
		
		TableColumn cAlbum = new TableColumn(table, SWT.CENTER);
		cAlbum.setText("Album");
		cAlbum.setWidth(200);
		
		TableColumn cTitle = new TableColumn(table, SWT.CENTER);
		cTitle.setText("Title");
		cTitle.setWidth(200);
		
		table.setHeaderVisible(true);
		
		items = new ArrayList<Music>();
		
		//Dummy Data should be replaced by data read form srcFolder
//		Music m1 = new Music("Linken Park", "Living Things", "Burn it Down");
//		this.addData(m1);
//		m1 = new Music("Nirvana", "Living Things", "Smells Like Teen Spriit");
//		this.addData(m1);
//		m1 = new Music("Metallica", "S&M", "Nothing else matters");
//		this.addData(m1);
//		m1 = new Music("Parov Stelar", "something", "i dont know");
//		this.addData(m1);
//		this.addData(m1);
//		this.addData(m1);
//		this.addData(m1);
//		this.addData(m1);
//		this.addData(m1);
//		this.addData(m1);
		
	}
	
	private MusicGrid(Composite parent, String description) {
		GridData gridData = new GridData(GridData.FILL, GridData.FILL, false, true);
		gridData.horizontalSpan = 3;
		
		
		Color bg = new Color(
				Display.getCurrent(), 255, 255, 255);
		createMusicGrid(parent, description, bg, gridData);
		
	}
	
	public void filterBy(String filterString) {
		table.removeAll();
		Iterator<Music> it = items.iterator();
		while(it.hasNext()) {
			Music music = it.next();
			if(music.toString().matches(".*"+filterString+".*")) {
				TableItem item = new TableItem(table, SWT.None);
				item.setText(music.getData());
			}
		}
		table.redraw();
	}

}
