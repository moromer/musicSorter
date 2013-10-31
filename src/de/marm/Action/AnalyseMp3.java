package de.marm.Action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AnalyseMp3 {
	private String path;
	private ArrayList<File> fileList = null;
 
	public AnalyseMp3(String path){
		if(!path.matches("^.*/$")) {
			path += "/";
		}
		this.path = path;
	}
	
	public void startAnalyze() throws FileNotFoundException{
		File file = new File(path);
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
	}
	
	public File getFolder() {
		return new File(this.path);
	}
	
	private void readFiles() {
		
		if(this.fileList == null) {
			this.fileList = new ArrayList<File>();
		}
		
		File folder = new File(this.path);
		
		for(File fileEntry : folder.listFiles()) {
			if(fileEntry.isDirectory()) {
				this.readFiles();
			} else {
				this.fileList.add(fileEntry);
			}
		}
		
	}
	
	public ArrayList<File> getFileList(){
		if(this.fileList == null) {
			this.readFiles();
		}
		return this.fileList;
	}
	
	
	public String getPath() {
		return this.path;
	}
	
	public void readInFiles() {
		this.fileList.removeAll(fileList);
		this.readFiles();
		
	}
}
