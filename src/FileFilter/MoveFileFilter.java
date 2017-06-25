package FileFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class MoveFileFilter implements mFileFilter{
	
	private String singleMode = "S";
	private String allMode = "A";
	private String searchMode = allMode;
	private List<File> folders = new ArrayList<File>();
	
	public MoveFileFilter(){
		System.out.println("----------------------------MoveFileFilter---------------------------------");
	}
	
	@Override
	public boolean accept(File pathname) {
			if(pathname.isDirectory()){
				if(searchMode.equalsIgnoreCase(allMode)){
					folders.add(pathname);
				}
				System.out.println("Folder Found: Size: "+folders.size()+"|" +pathname.getAbsolutePath());
				return false;
			} else {
				System.out.println("File Found: "+ pathname.getAbsolutePath());
				return true;		
			}
		}

	@Override
	public List<File> getFolders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFolders(List<File> folders) {
		// TODO Auto-generated method stub
		
	}

}
