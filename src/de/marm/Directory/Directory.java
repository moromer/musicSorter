package de.marm.Directory;

import java.io.File;

import javax.swing.JFileChooser;

public class Directory {

	private String absolutePath;
	protected JFileChooser chooser;

	public Directory() {
		chooser = new JFileChooser();

		// FileChooser Configuration
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Please choose a folder");
		chooser.setAcceptAllFileFilterUsed(false);
	}
	
	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String path) {
		absolutePath = path;
	}

	public void readFolder() {
		//find a solution to add this seleciton in foreground		
		int returnVal = chooser.showDialog(null, "select folder");

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			absolutePath = chooser.getSelectedFile().getAbsolutePath();
		}
	}

	public boolean isDirectory() {
		
		if (this.absolutePath != null && new File(this.getAbsolutePath()).isDirectory()) {
			return true;
		} 
		return false;
		
	}

}
