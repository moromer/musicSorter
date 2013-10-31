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
			this.readFiles();
			Iterator<File> it = fileList.iterator();
			
			while(it.hasNext()) {
				File file = it.next();
				Music mp3 = this.readMp3Tags(file);
				grid.addData(mp3);
			}
		} else {
			System.out.println("no srcFolder specified -> can not start the Analyse");
		}
		
 
	}

	private Music readMp3Tags(File mp3File) {
		if (mp3File.isFile()) {
			try {
				RandomAccessFile ranFile = new RandomAccessFile(mp3File, "r");
				byte[] bytearr = new byte[128];
				ranFile.seek(ranFile.length() - 128);
				ranFile.read(bytearr, 0, 128);
				String a = new String(bytearr, "US-ASCII");
				
				if (!a.substring(0, 3).equals("TAG")) {
					System.out.println("Keine Informationen vorhanden");
					ranFile.close();
					return null;
				}
				
				String title, artist, album;
				artist = a.substring(33, 63).trim();
				album  = a.substring(63, 93).trim();
				title  =  a.substring(3, 33).trim();
				ranFile.close();

				return new Music(artist, album, title);
				
//				System.out.println("TITEL: " + a.substring(3, 33).trim());
//				System.out.println("ARTIST: " + a.substring(33, 63).trim());
//				System.out.println("ALBUM: " + a.substring(63, 93).trim());
//				System.out.println("YEAR: " + a.substring(93, 97).trim());
//				System.out.println("COMMENT: " + a.substring(97, 126).trim());
//				System.out.println("GENRE: " + bytearr[127]);
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public void setSrcFolder(String folder) {
		this.srcFolder = folder;
	}

	private void readFiles() {

		if (this.fileList == null) {
			this.fileList = new ArrayList<File>();
		}

		File folder = new File(this.srcFolder);

		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				this.readFiles();
			} else {
				this.fileList.add(fileEntry);
			}
		}

	}

	public ArrayList<File> getFileList() {
		if (this.fileList == null) {
			this.readFiles();
		}
		return this.fileList;
	}

	public String getPath() {
		return this.srcFolder;
	}

	public void reLaunchFiles() {
		this.fileList.removeAll(fileList);
		this.readFiles();

	}
}
