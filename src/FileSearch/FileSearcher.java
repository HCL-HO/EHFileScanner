package FileSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import FileFilter.mFileFilter;
import Property.PropertiesKey;
import Property.PropertyManager;
import SearchRecord.WriteLog.WriterObject;


public class FileSearcher{
	private List<File> allFileList = new ArrayList<File>();
	private WriterObject Logger;
	private boolean batchJobFinish = false;
	private String[] emptyStringArrays = new String[]{};
	private boolean keepFileList = false;
	private mFileFilter mFilter;

	
	public FileSearcher(mFileFilter filter){
		this.mFilter = filter;
	}
	
//	public void loadLastFileSearch(){
//		System.out.println("----------------------------loadLastFileSearch---------------------------------");
//		try {
//			BufferedReader BR = new BufferedReader(new FileReader(new File("FileList")));
//			String line = BR.readLine();
//			while(line != null){
//				System.out.println("Initial Files: "+ line);
//				if(line!=null){
//					allFileList.add(new File(line));
//				}
//				line = BR.readLine();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	public File[] searchFilesContain(File folder, String name){
//		System.out.println("----------------------------searchFilesContain---------------------------------");
//		
//		if(folder.isDirectory()){
//			File[] list = folder.listFiles(new mFilter(name));
//			return list;
//		} else {
//			System.out.println("File is not a folder");
//			return null;
//		}	
//	}
//	
//	public List<File> searchAllInnerFilesContain(File folder, String name){
//		System.out.println("----------------------------searchAllInnerFilesContain---------------------------------");	
//		System.out.println("Folder: "+ folder.getAbsolutePath());	
//
//		if(mFilter.getFolders().size()>0){
//			mFilter.getFolders().remove(0);
//		}
//			searchMode = allMode;
//		if(folder.isDirectory()){
//			File[] list = folder.listFiles(emptyFilter);
//			searchMode = this.singleMode;
//			for(File file : Arrays.asList(list)){
//				if(file.isFile() && file.getName().contains(name)){
//					allFileList.add(file);
//					if(keepFileList){Logger.write(file.getAbsolutePath());}
//				}
//			}
//			if(mFilter.getFolders().size() >0){
//				searchAllInnerFilesContain(mFilter.getFolders().get(0), name);
//			}
//			return allFileList;
//		} else {
//			System.out.println("File is not a folder");
//			return null;
//		}	
//	}
//	
//	public File[] searchFilesContain(File folder, String[] names){
//		System.out.println("----------------------------searchFilesContain---------------------------------");
//		
//		if(folder.isDirectory()){
//			File[] list = folder.listFiles(new mFilter(names));
//			return list;
//		} else {
//			System.out.println("File is not a folder");
//			return null;
//		}	
//	}
//	
//	public List<File> searchAllInnerFilesContain(File folder, String[] names){
//		System.out.println("----------------------------searchAllInnerFilesContain---------------------------------");	
//		System.out.println("Folder: "+ folder.getAbsolutePath());	
//
//		if(mFilter.getFolders().size()>0){
//			mFilter.getFolders().remove(0);
//		}
//			searchMode = allMode;
//		if(folder.isDirectory()){
//			File[] list = folder.listFiles(emptyFilter);
//			searchMode = this.singleMode;
//			for(File file : Arrays.asList(list)){
//				if(file.isFile() && checkContain(file.getName(), names, null)){
//					allFileList.add(file);
//					if(keepFileList){Logger.write(file.getAbsolutePath());}
//				}
//			}
//			if(mFilter.getFolders().size() >0){
//				searchAllInnerFilesContain(mFilter.getFolders().get(0), names);
//			}
//			return allFileList;
//		} else {
//			System.out.println("File is not a folder");
//			return null;
//		}	
//	}
	
	public List<File> searchAllInnerFilesContainExcept(File folder, String[] names, String[] except){
		if(except == null){
			except = emptyStringArrays ;
			}
		System.out.println("----------------------------searchAllInnerFilesContainExcept---------------------------------");	
		System.out.println("Folder: "+ folder.getAbsolutePath());	
		if(mFilter.getFolders().size()>0){
			mFilter.getFolders().remove(0);
		} 
		
		if(folder.isDirectory()){
			if(checkExcept(folder.getName(), except)){
				File[] list = folder.listFiles(mFilter);
				for(File file : Arrays.asList(list)){
					if(file.isFile() && checkContain(file.getName(), names, except)){
						allFileList.add(file);
					}
				}
			}
			
			if(mFilter.getFolders().size() >0){
				searchAllInnerFilesContainExcept(mFilter.getFolders().get(0), names, except);
			}		
			return allFileList;
		} else {
			System.out.println("File is not a folder");
			return null;
		}	
	}
	
	public List<File> searchBatchJob(File folder, String[] names, String[] except){
		List<File> list = new ArrayList<File>();
		System.out.println("----------------------------searchBatchJob---------------------------------");	
		System.out.println("Folder: "+ folder.getAbsolutePath());
		if(folder.isDirectory()){
			if(checkExcept(folder.getName(), except)){
				try{
				for(File file : Arrays.asList(folder.listFiles(mFilter))){
					if(file.isFile() && checkContain(file.getName(), names, except)){
						list.add(file);
					}
				}
				} catch (NullPointerException e){
					System.out.println("NullPointerException: Folder: "+ folder.getAbsolutePath());
				}
			}			
		} else {
			System.out.println("File is not a folder");
			return list;
		}
		return list;
	}
	
	public boolean checkContain(String A, String[] B, String[] ex){
		if(ex == null){
			ex = emptyStringArrays;
		}
		if(B == null){
			B = new String[]{""};
		}
		for(String s: B){
			if(A.contains(s) && checkExcept(A, ex)){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkExcept(String A, String[] ex){
		if(ex == null){
			ex = emptyStringArrays;
		}
		for(String s: ex){
			if(A.contains(s)){
				return false;
			}
		}
		return true;
	}	
	
	public List<File> getAllFileList() {
		return allFileList;
	}

	public void setAllFileList(List<File> allFileList) {
		this.allFileList = allFileList;
	}
	
	public void clearAllFileList() {
		this.allFileList.clear();
	}
	
	public boolean isBatchJobFinish() {
		return batchJobFinish;
	}

	public void setBatchJobFinish(boolean batchJobFinish) {
		this.batchJobFinish = batchJobFinish;
	}
//	mFilter.getFolders().add(folder);
//	while(mFilter.getFolders().size()>0){
//		folder = mFilter.getFolders().get(0);
//		mFilter.getFolders().remove(0);
//		System.out.println("----------------------------searchBatchJob---------------------------------");	
//		System.out.println("Folder: "+ folder.getAbsolutePath());
//		if(folder.isDirectory()){
//			if(checkExcept(folder.getName(), except)){
//				try{
//				for(File file : Arrays.asList(folder.listFiles(mFilter))){
//					if(file.isFile() && checkContain(file.getName(), names, except)){
//						allFileList.add(file);
//						if(keepFileList){Logger.write(file.getAbsolutePath());}
//					}
//				}
//				} catch (NullPointerException e){
//					System.out.println("NullPointerException: Folder: "+ folder.getAbsolutePath());
//				}
//			}			
//		} else {
//			System.out.println("File is not a folder");
//			return null;
//		}	
//	}	
//	batchJobFinish = true;
//	return allFileList;
}
