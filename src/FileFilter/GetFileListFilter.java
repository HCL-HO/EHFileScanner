package FileFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class GetFileListFilter implements mFileFilter{
	private String singleMode = "S";
	private String allMode = "A";
	private String searchMode = allMode;
	private List<File> folders = new ArrayList<File>();
	
	public GetFileListFilter(){
		System.out.println("----------------------------GetFileListFilter---------------------------------");
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
		return folders;
	}

	@Override
	public void setFolders(List<File> folders) {
		this.folders = folders;
	}
}
