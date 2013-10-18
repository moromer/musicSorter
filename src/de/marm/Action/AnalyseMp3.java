package de.marm.Action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AnalyseMp3 {
	private String path;

	public AnalyseMp3(String path){
		this.path = path;
	}
	
	public void startAnalyze() throws FileNotFoundException{
		File file = new File(path);
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
	}
}
