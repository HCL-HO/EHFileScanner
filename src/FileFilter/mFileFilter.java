package FileFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public interface mFileFilter extends FileFilter{
	List<File> getFolders();
	void setFolders(List<File> folders);
	@Override boolean accept(File pathname);
}
