package FileSearch;

import java.io.File;
import java.util.Arrays;

import Method.FilePathFinder;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FilePathFinder finder = new FilePathFinder();
		String[] list = finder.search("C:\\Users\\h6", null, null);
		for(File f: finder.getFolderList()){
			System.out.println(f.getAbsolutePath());
		}
	}
}
