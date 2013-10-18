package de.marm.Fields;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

public class FileFormatList {

	private List list;
	private String possibleValues[] = { "MP3", "M4a" };
	private ArrayList<String> selectedValues = new ArrayList<>();

	@SuppressWarnings("unused")
	private FileFormatList() {

	}

	public FileFormatList(Composite parent, int style) {
		list = new List(parent, style);
		GridData gridData = new GridData(GridData.BEGINNING, GridData.CENTER,
				true, false);
		
		gridData.heightHint = 80;
		gridData.widthHint = 50;

		list.setLayoutData(gridData);

		for (int index = 0; index < possibleValues.length; index++) {
			list.add(possibleValues[index], index);
		}

		list.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				selectedValues.clear();
				int[] selection = list.getSelectionIndices();
				
				for (int i = 0; i < selection.length; i++) {
					selectedValues.add(possibleValues[selection[i]]);
				}
			}
		});
	}

	public ArrayList<String> getSelectedValues() {
		return selectedValues;
	}
}
