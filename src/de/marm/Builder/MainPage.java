package de.marm.Builder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import de.marm.Action.AnalyseMp3;
import de.marm.Fields.Filter;
import de.marm.Fields.FolderSelection;
import de.marm.Fields.MusicGrid;

public class MainPage {

	protected Shell shell;

	protected FolderSelection srcSelection;
	protected FolderSelection dstSelection;
	
	protected Filter fileFormatist;         

	protected MusicGrid gMusic;

	protected Button bDiscard;
	protected Button bAnalyze;
	protected Button bSave;

	protected Label lblmessage;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainPage window = new MainPage();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(){

		shell = new Shell();
		final Image image = new Image(shell.getDisplay(), "images/music.png");
		shell.setImage(image);
		shell.setSize(704, 451);
		shell.setText("MP3 List Sorter");

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;

		shell.setLayout(gridLayout);

		// Modify Source Destionation Folder Group View
		Group folderInfo = new Group(shell, SWT.NONE);
		folderInfo.setText("Src/Dest Folder");
		gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		folderInfo.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL, GridData.BEGINNING,
				true, false);
		gridData.widthHint = 400;
		folderInfo.setLayoutData(gridData);

		srcSelection = new FolderSelection(folderInfo, "Src. Folder", FolderSelection.SRC);
		dstSelection = new FolderSelection(folderInfo, "Dst. Folder", FolderSelection.DST);
		
		fileFormatist = new Filter(shell, SWT.SINGLE | SWT.BORDER);

		// Modify Action Buttons
		Composite cActions = new Composite(shell, SWT.NONE);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 1;

		cActions.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL, GridData.BEGINNING, true, false);

		cActions.setLayoutData(gridData);

		gridData = new GridData(GridData.BEGINNING, GridData.BEGINNING, true,
				false);
		gridData.widthHint = 100;

		bDiscard = new Button(cActions, SWT.NONE);
		bDiscard.setLayoutData(gridData);
		bDiscard.setText("Discard");

		bDiscard.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				srcSelection.removeText();
				dstSelection.removeText();
				MusicGrid.getInstance().removeStore();
				lblmessage.setText("");
			}
		});

		bAnalyze = new Button(cActions, SWT.NONE);
		bAnalyze.setLayoutData(gridData);
		bAnalyze.setText("start Analyze");

		bAnalyze.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (srcSelection.isDirectory() && dstSelection.isDirectory()) {
					lblmessage.setText("");
					AnalyseMp3 analyse = new AnalyseMp3(srcSelection.getPath());
					analyse.setSrcFolder(srcSelection.getPath());
					analyse.startAnalyse();
				
				} else {
					lblmessage
							.setText("Please choose a Source AND Destination Folder");
					lblmessage.setSize(300, 20);
					lblmessage.setForeground(new Color(null, 204, 0, 0));
				}
			}
		});

		bSave = new Button(cActions, SWT.NONE);
		bSave.setLayoutData(gridData);
		bSave.setText("Write Changes");

		// Warn, Info, Error Message
		lblmessage = new Label(shell, SWT.WRAP);
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, true,
				false, 3, 1);
		lblmessage.setLayoutData(gridData);
		lblmessage.setText("");
		
		gMusic = MusicGrid.getInstance(shell, "Ordered Music");
		
	}
}
