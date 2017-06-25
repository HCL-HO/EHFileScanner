package FileSearch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class WriteLog {
	
	public WriterObject init(String path){
			return new WriterObject(path);
	}
	
	public WriterObject init(){
			return new WriterObject("FileLIst");
	}
	
	public class WriterObject{
		String path;
		BufferedWriter BW;
		public WriterObject(String path) {
			this.path =path;
			try {
				this.BW = new BufferedWriter(new FileWriter(path, true));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void write(String s){
			try {

				if(BW == null){
					System.out.println("Please check the File List Path");
					return;
				}
				BW.newLine();
				BW.write(s);
				System.out.println("LOG: "+ s);
				BW.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
			}
		}
		public void clearLog(){
			System.out.println("clearLog");
			try {
				FileOutputStream FOS = new FileOutputStream(new File(path));
				FOS.write(new byte[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
}
