package de.marm.Action;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;

import de.marm.Fields.MusicGrid;
import de.marm.Typ.Music;

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
				Music music = this.readMp3Tags(it.next());
				if(music != null) {
					grid.addData(music);
				}
			}
		} else {
			System.out.println("no srcFolder specified -> can not start the Analyse");
		}
		
 
	}

	private Music readMp3Tags(File mp3File) {
		if (mp3File.isFile()) {
			try {
				RandomAccessFile ranFile = new RandomAccessFile(mp3File, "r");
				
				if(ranFile.length() >= 128) {
					byte[] bytearr = new byte[128];
					ranFile.seek(ranFile.length() - 128);
					ranFile.read(bytearr, 0, 128);
					String a = new String(bytearr, "US-ASCII");
					
					if (!a.substring(0, 3).equals("TAG")) {
						System.out.println("Keine Informationen vorhanden");
						ranFile.close();
						return null;
					}
					
					String title, interpret, album;
					interpret = a.substring(33, 63).trim();
					album  = a.substring(63, 93).trim();
					title  =  a.substring(3, 33).trim();
					
					if(interpret.equals("")){
						interpret = Music.DEFAULT_INTERPRET;
					}
					if(album.equals("")) {
						album = Music.DEFAULT_ALBUM;
					}
					ranFile.close();
					
					return new Music(interpret, album, title);
					
	//				System.out.println("TITEL: " + a.substring(3, 33).trim());
	//				System.out.println("ARTIST: " + a.substring(33, 63).trim());
	//				System.out.println("ALBUM: " + a.substring(63, 93).trim());
	//				System.out.println("YEAR: " + a.substring(93, 97).trim());
	//				System.out.println("COMMENT: " + a.substring(97, 126).trim());
	//				System.out.println("GENRE: " + bytearr[127]);
					
				}
				ranFile.close();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
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
				if(fileEntry.toString().matches(".*mp3$")) {
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
