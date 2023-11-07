package com.greedy.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@PostMapping("single-file")
	public String singleFileUpload(HttpServletRequest request, 
			@RequestParam("singleFile") MultipartFile singleFile,
			Model model) 
			throws UnsupportedEncodingException {
		
		/* 
		 * encType이 multipart/formdata더라도 file타입이 아닌 input 태그의 value를
		 * request의 getParameter로 쉽게 꺼낼 수 있다.
		 * (단, 한글이 깨지지 않으려면 인코딩 필터가 필요하다.) 
		 */
		String singleFileDescription = request.getParameter("singleFileDescription");
		System.out.println("파일 설명 : " + singleFileDescription);
		
		/* file타입으로 넘어온 파일을 저장하는 과정 진행 */
		/* 1. 파일 저장 위치 설정 */
		/* 1-1. root(webapp폴더 아래의 resources폴더) 경로 추출하기 */
		String root = request.getSession().getServletContext().getRealPath("resources");
		System.out.println("root까지의 경로 : " + root);
		
		String filePath = root + "/uploadFiles";
		
		/* 1-2. uploadFiles 폴더 생성 */
		File mkdir = new File(filePath);	// file.io 패키지로 import
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		/* 2. 파일을 전달받아 파일명 변경 처리 */
		String originFileName = singleFile.getOriginalFilename();
		System.out.println("원본 이름 : " + originFileName);
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
		System.out.println("변경한 이름 : " + saveName);
		
		/* 3. 파일을 저장한다. */
		try {
			singleFile.transferTo(new File(filePath + "/" + saveName));
			
			/* DB에 업로드한 파일의 정보를 저장하는 비즈니스 로직 수행 */
			
			model.addAttribute("message", "파일 업로드 성공!");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			/* 실패 시 파일 삭제 */
			new File(filePath + "/" + saveName).delete();
			model.addAttribute("message", "파일 업로드 실패!");
		}
		
		return "result";
	}
	
	@PostMapping("multi-file")
	public String multiFileUpload(@RequestParam("multiFileDescription") String multiFileDescription,
			@RequestParam("multiFile") List<MultipartFile> multiFiles,
			Model model, HttpSession session) {
		
		/* 굳이 request가 아닌 @RequestParam을 활용해 매개변수에서 바로 파라미터를 사용할 수 있게 하자. */
		System.out.println("파일 설명 : " + multiFileDescription);
		
		/* HttpSession 매개변수 추가해서 활용(getRealPath("") 기존에 web폴더로 잡아주던 경로가 아닌 target쪽 경로를 잡게 된다.) */
		String root = session.getServletContext().getRealPath("resources");
		
		
		String filePath = root + "\\uploadFiles";
		
		/* 이후 transferTo로 파일 저장 시 해당 폴더가 없으면 경로상 폴더를 만들어 줌 */
		
		List<Map<String, String>> files = new ArrayList<>();
		for(int i = 0; i < multiFiles.size(); i++) {
			String originFileName = multiFiles.get(i).getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			/* 하나의 업로드 된 파일 정보 추출 후 Map에 보관 */
			Map<String, String> file = new HashMap<>();
			file.put("originFileName", originFileName);
			file.put("saveName", saveName);
			file.put("filePath", filePath);
			
			/* List에 파일 정보 누적 */
			files.add(file);
		}
		
		try {
			for(int i = 0; i < multiFiles.size(); i++) {
				Map<String, String> file = files.get(i);
				multiFiles.get(i).transferTo(new File(filePath + "\\" + file.get("saveName")));
			}
			
			/* DB 다녀오는 비즈니스 로직 수행 */
			
			model.addAttribute("message", "파일 업로드 성공!");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			for(int i = 0; i < multiFiles.size(); i++) {
				Map<String, String> file = files.get(i);
				
				new File(filePath + "\\" + file.get("saveName")).delete();
			}
			
			model.addAttribute("message", "파일 업로드 실패!");
		}
		
		return "result";
	}
	
}






