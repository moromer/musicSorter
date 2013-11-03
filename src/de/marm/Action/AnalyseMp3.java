package de.marm.Action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import de.marm.Fields.MusicGrid;
import de.marm.Typ.MP3;

public class AnalyseMp3 {
	private String srcFolder;
	private String dstFolder;
	
	private static AnalyseMp3 instance = null;
	
	private ArrayList<File> fileList = null;
	
	public static AnalyseMp3 getInstance() {
		return instance;
	}

	public AnalyseMp3(String path) {
		if (!path.matches("^.*/$")) {
			path += "/";
		}
		this.srcFolder = path;
	}

	public void startAnalyse() {
		MusicGrid grid = MusicGrid.getInstance();
		
		if(!this.srcFolder.isEmpty()) {
			this.readFiles(this.srcFolder);
			Iterator<File> it = fileList.iterator();
			
			while(it.hasNext()) {
				MP3 mp3 = new MP3(it.next());
				
				if(mp3.isValidFile()) {
					mp3.readValuesFromMp3Tag();
					grid.addData(mp3);
				}
			}
		} else {
			System.out.println("no srcFolder specified -> can not start the Analyse");
		}
		
 
	}

	public void setSrcFolder(String folder) {
		this.srcFolder = folder;
	}
	
	public void setDstFolder(String folder) {
		this.dstFolder = folder;
	}

	private void readFiles(String path) {

		if (this.fileList == null) {
			System.out.println("FILELIST IS NULL");
			this.fileList = new ArrayList<File>();
		}

		File folder = new File(path);

		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				this.readFiles(fileEntry.toString());
			} else {
				if(fileEntry.toString().matches(".*\\.mp3$")) {
					fileList.add(fileEntry);
				} else {
					System.out.println("File ("+fileEntry.toString() + " ) is not a valid type of .mp3");
				}
			}
		}

	}

	//Currently this is used only for tests
	public ArrayList<File> getFileList() {
		if (this.fileList == null) {
			this.readFiles(this.srcFolder);
		}
		return this.fileList;
	}

	public String getPath() {
		return this.srcFolder;
	}

	public void reLaunchFiles() {
		this.fileList.removeAll(fileList);
		this.readFiles(this.srcFolder);

	}
}
