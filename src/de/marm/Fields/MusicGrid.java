package de.marm.Fields;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class MusicGrid {
	
	private Group musicGroup;

	@SuppressWarnings("unused")
	private MusicGrid() {
		
	}
	
	private void createMusicGrid(Composite parent, String description, Color bg, GridData grid) {
		musicGroup = new Group(parent, SWT.NONE);
		musicGroup.setBackground(bg);
		musicGroup.setText(description);
		
		musicGroup.setLayoutData(grid);
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
