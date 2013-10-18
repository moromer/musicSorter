package de.marm.Fields;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

import de.marm.Directory.Directory;

public class FolderSelection {

	private Button buttonField;
	private Text textField;
	private Directory dir;

	@SuppressWarnings("unused")
	private FolderSelection() {
	}

	public FolderSelection(Composite parent, String description) {
		dir = new Directory();
		new Label(parent, SWT.NONE).setText(description);

		textField = new Text(parent, SWT.SINGLE | SWT.BORDER);

		textField.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING,
				true, false));

		buttonField = new Button(parent, SWT.NONE);
		buttonField.setText("...");

		buttonField.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				dir = new Directory();
				dir.readFolder();

				if (dir.getAbsolutePath() != null) {
					textField.setText(dir.getAbsolutePath());
					if (!isDirectory()) {
						// TODO: mark srcFolder as red
						// setRedBorder();
						System.out.println("hightlight text as red");
					}
				}

			}
		});

		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				dir.setAbsolutePath(textField.getText());
				if (textField.getText().equals("")) {
					System.out
							.println("No text inserted. -> no validation necessary");
					return;
				}

				if (!dir.isDirectory()) {
					// setRedBorder();
					System.out.println("hightlight text as red");

				}
				System.out.println("validation finished!");

			}
		});
	}

	public void removeText() {
		textField.setText("");
		dir.setAbsolutePath("");
	}

	public boolean isDirectory() {
		if (dir != null) {
			System.out.println("dir exist i will start now the check");
			return dir.isDirectory();
		}
		System.out.println("i will return now false");
		return false;
	}

	// TODO: set TextField Border red
	private void setRedBorder() {

	}
	
}
