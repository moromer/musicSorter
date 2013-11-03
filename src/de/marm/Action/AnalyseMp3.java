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
		instance = this;
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

		if (instance.fileList == null) {
			System.out.println("FILELIST IS NULL");
			instance.fileList = new ArrayList<File>();
		}

		File folder = new File(path);

		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				this.readFiles(fileEntry.toString());
			} else {
				if(fileEntry.toString().matches(".*\\.mp3$")) {
					instance.fileList.add(fileEntry);
				} else {
					System.out.println("File ("+fileEntry.toString() + " ) is not a valid type of .mp3");
				}
			}
		}

	}

	//Currently this is used only for tests
	public ArrayList<File> getFileList() {
		if (instance.fileList == null) {
			instance.readFiles(this.srcFolder);
		}
		return instance.fileList;
	}

	public String getPath() {
		return this.srcFolder;
	}

	public void reLaunchFiles() {
		instance.fileList.removeAll(fileList);
		this.readFiles(this.srcFolder);

	}
	
	public void Write() {
		if(fileList != null && fileList.size() > 0) {
			MusicGrid grid = MusicGrid.getInstance();
			for(MP3 file : grid.getItemList()) {
				System.out.println(file.getInterpret() + "/"+file.getAlbum()+"/"+file.getTitle());
			}
		}
	}
}
