package com.study.mvcxml2.board;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path="/board")
public class BoardController {
	
	BoardService boardService;
	FileUploadLogic uploadService;
	
	@Autowired
	public BoardController(BoardService boardService,FileUploadLogic uploadService) {
		this.boardService=boardService;
		this.uploadService=uploadService;
	}
	
	@GetMapping
	public String boardView() {
		return"board/board";
	}
	
	
	@PostMapping // 파일 여러개 업로드 및 DB 저장 
	public String write(@ModelAttribute BoardDTO boardDTO, HttpServletRequest request) {
		
		List<MultipartFile> files = boardDTO.getFiles(); // 커맨드객체(DTO)특성 상 form의 input의 name과 DTO의 필드 명을 일치시켜줘야 DTO를 통해 값을 넘겨 받을 수 있다. 
		
		//저장할 위치
		String path = request.getSession().getServletContext().getRealPath("/resources/static/upload");
		//System.out.println("path : "+path);
		
		ArrayList<String> fileNameList = new ArrayList<String>();
		
		for(int i=0; i<files.size(); i++) {
			String fileName = files.get(i).getOriginalFilename();
			if(fileName.length()!=0) { //파일이 전송되지 않았을 경우의 처리문인 듯 
				fileNameList.add(fileName);
				uploadService.upload(files.get(i),path,fileName); // 서버 경로로 파일을 업로드하는 코드 
			}
		}
		
		boardService.insert(boardDTO,fileNameList); // db 에 게시글 및, 파일 정보를 저장하는 코드 
		
		return"board/uploadOk";
	}
	
	
	
	@PostMapping("/upload1")  // 파일 1개만 업로드 테스트 , 서비스단등으로 분리하지 않음 
	public String upload(@RequestParam("file") MultipartFile file) {
		
		if(file.getSize()>0) { // 파일이 전송되지 않았을 경우의 처리를 위한 조건문. 파일 사이즈가 0이 아니면 아래 코드 실행
			
			
			System.out.println("파일 이름 : "+file.getOriginalFilename());
			System.out.println("파일 크기 : "+file.getSize());
			
			try(
					FileOutputStream fos = new FileOutputStream("/tmp/"+file.getOriginalFilename());
					InputStream is = file.getInputStream();
					
					){
				int readCount =0;
				byte[] buffer = new byte[1024];
				while((readCount=is.read(buffer))!=-1) {
					fos.write(buffer,0,readCount);
				}
				
			}catch(Exception ex) {
				
				throw new RuntimeException("file Save Error");
				
			}
		}
		
		
		return"board/uploadOk";
	}
	
	
	@PostMapping("/upload2")  // 파일 여러개 업로드 테스트 , 서비스단 등으로 분리하지 않음 
	public String multipleUpload(@RequestParam("file") MultipartFile[] files) {
		
		for(MultipartFile file : files) {
			
			if(file.getSize()>0) { // 파일이 전송되지 않았을 경우의 처리를 위한 조건문. 파일 사이즈가 0이 아니면 아래 코드 실행
				
				System.out.println("파일 이름 : "+file.getOriginalFilename());
				System.out.println("파일 크기 : "+file.getSize());
				
				try(
					FileOutputStream fos = new FileOutputStream("/tmp/"+file.getOriginalFilename());
					InputStream is = file.getInputStream();
					
					){
				int readCount =0;
				byte[] buffer = new byte[1024];
				while((readCount=is.read(buffer))!=-1) {
					fos.write(buffer,0,readCount);
				}
					
				}catch(Exception ex) {
					
					throw new RuntimeException("file Save Error");
					
				}
			}
		}
		
		
		return"board/uploadOk";
	}

}
