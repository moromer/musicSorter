package de.marm.Fields;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import de.marm.Typ.Music;

public class MusicGrid {
	
	private Table table;
	private ArrayList<TableItem> items;

	@SuppressWarnings("unused")
	private MusicGrid() {
		
	}
	
	
	public void addData(Music music) {
		TableItem item = new TableItem(table, SWT.None);
		item.setText(music.getData());
		this.items.add(item);
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
		
		items = new ArrayList<TableItem>();
		
		//Dummy Data
		Music m1 = new Music("Linken Park", "Living Things", "Burn it Down");
		this.addData(m1);
		this.addData(m1);
		this.addData(m1);
		this.addData(m1);
		this.addData(m1);
		this.addData(m1);
		this.addData(m1);
		this.addData(m1);
		this.addData(m1);
		this.addData(m1);
		
	}
	
	public MusicGrid(Composite parent, String description, Color bg) {
		GridData gridData = new GridData(GridData.FILL, GridData.FILL, false, true);
		gridData.horizontalSpan = 3;
		
		createMusicGrid(parent, description, bg, gridData);
	}
	
	public MusicGrid(Composite parent, String description, Color bg, GridData grid) {
		createMusicGrid(parent, description, bg, grid);
	}

}
