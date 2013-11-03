package de.marm.Tests.Action;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

import de.marm.Fields.MusicGrid;

public class AnalyseMp3 {
	
	private de.marm.Action.AnalyseMp3 analyze;
	private String expectedPath;
	private MusicGrid grid;

	@Before
	public void prepare() {
		expectedPath = "C:\\Users\\Mario Romer\\workspace\\musicSorter\\testData/";
		analyze = new de.marm.Action.AnalyseMp3(expectedPath);
		grid = de.marm.Fields.MusicGrid.getInstance(new Shell(), "test");
	}
	
	@Test
	public void test() {
		assertEquals("check initialization",de.marm.Action.AnalyseMp3.class, analyze.getClass());
		assertEquals("check path",expectedPath, analyze.getPath());
		
		this.resetAnalyzeObject("C:\\Users\\Mario Romer\\workspace\\musicSorter\\testData");
		
		assertEquals("check initialization",de.marm.Action.AnalyseMp3.class, analyze.getClass());
		assertEquals("check path",expectedPath, analyze.getPath());
	}
	
	@Test
	public void checkFileReader() throws IOException {
		String tmpPath = this.expectedPath + "tmp\\";
		rmdir(tmpPath);
		
		File tmpFolder = new File(tmpPath);
		
    	tmpFolder.mkdir();
    	this.createFile(tmpPath, "File1.mp3");
    	
		this.resetAnalyzeObject(tmpPath);
		
		ArrayList<File> fileList = analyze.getFileList();
	
		assertEquals("check count of files", 1, fileList.size());
		assertEquals("check File", "File1.mp3", fileList.get(0).getName());
		
		
		this.createFile(tmpPath, "File2.mp3");
		fileList = analyze.getFileList();
		
		assertEquals("check count of files is still 1", 1, fileList.size());
		
		assertEquals("check File is still File1.mp3", "File1.mp3", fileList.get(0).getName());
		
		analyze.reLaunchFiles();
		
		assertEquals("check count of files is now 2", 2, fileList.size());
		assertEquals("check File is still File1.mp3", "File1.mp3", fileList.get(0).getName());
		assertEquals("check File is still File1.mp3", "File2.mp3", fileList.get(1).getName());
		
		
		rmdir(tmpPath);
		
		
	}
	
	@Test
	public void checkAnalyse() {
		analyze.startAnalyse();
		assertEquals("check mp3 tags","Alex Clare|The Lateness of the Hour|Hands Are Clever", grid.getItemList().get(0).toString());
	}
	
	private void createFile(String path, String fileName) throws IOException {
		File newFile = new File(path + fileName);
		
		if(!newFile.exists()) {
			newFile.createNewFile();
		}
	}
	
	private void resetAnalyzeObject(String path) {
		analyze = null;
		analyze = new de.marm.Action.AnalyseMp3(path);
		
	}
	
	private void rmdir(String path){
		File folder = new File(path);
		
		if(folder.isDirectory()) {
			for(File file : folder.listFiles()) {
				rmdir(path+file.getName());
			}
		}
		
		folder.delete();
	}
	

}
