package FileSearch;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import Property.PropertyManager;

public class FileMover {
	
	int bufferSize = 2048;
	
	public FileMover(){
//		Object bufferSize =  new PropertyManager().getProperties().get("BufferSize");
//		if(bufferSize != null){
//			this.bufferSize =  Integer.parseInt((String) bufferSize);
//		}
	}
	
	public FileObject getFile(File file){
		System.out.println("----------------------FileMover-----------------------------");
		return new FileObject(file);
	}
	
	
	public class FileObject{
		File file;
		private String parentPath;

		public FileObject (File file){
		System.out.println("FileToMove: " + file.getAbsolutePath());
		this.file = file;
		}
		
		public void copyFileTo(String parentPath){
				this.parentPath = parentPath;
				makeParentFile(parentPath);
				FileInputStream FIS = null;
				BufferedInputStream BIS= null;
				FileOutputStream FOS= null;
				BufferedOutputStream BOS= null;
				try {
				FIS = new FileInputStream(file);
				BIS = new BufferedInputStream(FIS);
				
				FOS = new FileOutputStream(addNumIfFileExist(file));
				BOS = new BufferedOutputStream(FOS);
				
				byte[] fileByte = new byte[10192];
				int len;
				
				while((len = BIS.read(fileByte))>0){
					FOS.write(fileByte, 0, len);
				}
				
				System.out.println("FileMoveTo: " + parentPath);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					FIS.close();
					FOS.close();
					BIS.close();
					BOS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void copyFileTo(){
			parentPath = new PropertyManager().getProperties().getProperty("MoveFileToPath");
			makeParentFile(parentPath);
			try {
				FileInputStream FIS = new FileInputStream(file);
				BufferedInputStream BIS = new BufferedInputStream(FIS);
				
				FileOutputStream FOS = new FileOutputStream(addNumIfFileExist(file));
				BufferedOutputStream BOS = new BufferedOutputStream(FOS);
				
				byte[] fileByte = new byte[(int)file.length()];
				int len;
				
				while((len = BIS.read(fileByte))>0){
					FOS.write(fileByte, 0, len);
				}
					
//				FIS.read(fileByte);
				System.out.println("FileMoveTo: " + parentPath);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private File addNumIfFileExist(File file){
			File f = new File(parentPath+ "\\"+ file.getName());
			while(f.exists() && f.getAbsolutePath() != parentPath){
				System.out.println("File exists: "+ file.getAbsolutePath());
				int i = 1;
				f = new File(f.getAbsolutePath()+"("+i+")");
			}
			return f;
		}
		
		private void makeParentFile(String parentPath){
			File parent = new File(parentPath);
			if(parent != null){
				System.out.println("Making ParentPath: "+ parentPath);
				parent.mkdirs();
			}
		}
	}
	

}
