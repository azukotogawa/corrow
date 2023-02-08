package com.kjerne.src;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileLoad {
	private String information;
	private String infor;
	public FileLoad(String filePath){
		File file = new File(filePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			
			int c;
			while((c = fis.read()) != -1){
				char ch = (char) c;
				information += Character.toString(ch);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}finally{
			try{
				if(fis != null){
					fis.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public String getInformation(){
		return information;
	}

	public String getBrokenInformation(){
		return infor;
	}
	
	public String purifyInformation(String info){
		info.trim();
		int ind = 2 + info.indexOf("=");
		int inde = info.indexOf(";");
		String inform = info.substring(ind, inde);
		return inform;
	}
	
	public void breakInformation(){
		int inde = information.indexOf(";");
		infor = information.substring(0, inde + 1);
		information = information.substring(inde + 1);
	}
	
}
