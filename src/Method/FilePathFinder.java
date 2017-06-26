package Method;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import FileFilter.GetFileListFilter;
import FileFilter.mFileFilter;
import FileSearch.FileSearcher;

public class FilePathFinder {

	private FileSearcher searcher;
	private File curFolder;
	private List<File> folderList;
	private mFileFilter filter;
	
	public FilePathFinder(){
	URL f = this.getClass().getClassLoader().getResource("FileList");
	File file = new File(f.getPath());
	}
	
	public String[] deepSearch(String fromDir, String[] names, String[] except, Consumer c){
		curFolder = new File(fromDir);
		filter = new GetFileListFilter();
		searcher = new FileSearcher(filter);
		
		List<File> fileList = searcher.searchBatchJob(new File(fromDir), names, except);
		List<String> pathList = new ArrayList<String>();
		for(File f : fileList){
			pathList.add(f.getAbsolutePath());
		}
		
		String [] array = new String[fileList.size()];
		c.accept(fileList);
		return array = pathList.toArray(array);
	}
	
	public String[] search(String fromDir, String[] names, String[] except){
		curFolder = new File(fromDir);
		filter = new GetFileListFilter();
		searcher = new FileSearcher(filter);
		List<File> fileList = searcher.searchBatchJob(new File(fromDir), names, except);
		List<String> pathList = new ArrayList<String>();
		for(File f : fileList){
			pathList.add(f.getAbsolutePath());
		}
		
		String [] array = new String[fileList.size()];
		return array = pathList.toArray(array);
	}
	
	public FileSearcher getSearcher() {
		return searcher;
	}
	
	public List<File> getFolderList(){
		return filter.getFolders();
	}
	
	public File getCurFolder(){
		return curFolder;
	}
}
