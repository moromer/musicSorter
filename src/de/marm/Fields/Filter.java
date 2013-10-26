package de.marm.Fields;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class Filter {

	private Text filterText;

	@SuppressWarnings("unused")
	private Filter() {

	}

	public Filter(Composite parent, int style) {
		
		Group filterInfo = new Group(parent, SWT.NONE);
		filterInfo.setText("Filter");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		filterInfo.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL, GridData.BEGINNING,
				true, false);
		gridData.widthHint = 400;
		filterInfo.setLayoutData(gridData);
		
		filterText = new Text(filterInfo, style);
		filterText.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING,
				true, false));
		
		
		filterText.addListener(SWT.CHANGED, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				System.out.println("new FilterText: "+ filterText.getText());
				try {
					MusicGrid gMusic = MusicGrid.getInstance();
					if(gMusic == null )
						throw new Exception("no Grid defined");
					gMusic.filterBy(filterText.getText());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
