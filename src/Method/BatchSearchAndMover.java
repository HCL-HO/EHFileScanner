package Method;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import FileSearch.FileMover;
import FileSearch.FileSearcher;
import Property.PropertyManager;

public class BatchSearchAndMover {

	private List<File> files;
	private String[] keywords;
	private String[] filter;
	private String folderFrom;
	private String folderTo;
	private int counter = 1;
	private boolean isInitial = true;
	private boolean isFinished = false;
	private FilePathFinder finder = new FilePathFinder();

	public BatchSearchAndMover(){
//		this.batchSize = batchSize;
		System.out.println("----------------------BatchSearchAndMover-------------------------");
//		this.folderTo = (String) ((folderTo==null)? new PropertyManager().getProperties().get("MoveFileToPath") : folderTo);
	}
	
	public void move(String folderFrom, String folderTo, String[] keywords, String[] filter){
		System.out.println("----------------------move-------------------------");
		files = new ArrayList<File>();
		this.keywords = keywords;
		this.filter = filter;
		this.folderFrom = folderFrom;
		this.folderTo = folderTo;
		MoveFileJob consume = new MoveFileJob();
		
		if(isInitial){
			File folder = new File(folderFrom);
			if(folder.isDirectory()){
				finder.getFolderList().add(folder);
				isInitial = false;	
			}
		}	
		
		while(!finder.getFolderList().isEmpty()){
			finder.deepSearch(finder.getFolderList().get(0).getAbsolutePath(), keywords, filter, consume);
			finder.getFolderList().remove(0);
		}
	}
	

	
	public class MoveFileJob implements Consumer<List<File>>{
		FileMover mover = new FileMover();

		public MoveFileJob(){
		}
		
		@Override
		public void accept(List<File> t) {
			if(t instanceof List<?>){
				files = (List<File>) t;
				moveFiles();				
				System.out.println("-----------Finish Batch Job Mover--------------");
			}
		}

		private void moveFiles() {
			if(files.size()>0){
				System.out.println("Batch Number: "+ "("+counter+")" );
				counter++;
				for(File f : files){
					System.out.println("File: " + f.getName());
					mover.getFile(f).copyFileTo(folderTo);
				}
			} else {
				System.out.println("No Files Found");
				return;
			}			
		}
	}
	
//	public boolean isFinished() {
//		return searcher.isBatchJobFinish();
//	}
}