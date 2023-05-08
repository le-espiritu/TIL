package com.study.mvcxml2.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadLogic {
	
	public void upload(MultipartFile file, String path, String fileName) {
		
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(path+File.separator+fileName); //FileOutputStream은 기존의 폴더가 존재해야 사용할 수 있다.
			byte[] data = file.getBytes();
			fos.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fos!=null) {
					fos.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

}
