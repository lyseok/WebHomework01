package kr.or.ddit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image/streaming.do")
public class Streaming extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private String folderPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		folderPath = application.getInitParameter("imageFolder");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String imageName = request.getParameter("image");
		System.out.println(folderPath);
		if(imageName==null || imageName.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "이미지 이름 누락");
			return;
		}
		Path filePath = Paths.get(folderPath, imageName);
		System.out.println(filePath);
		
		if(!Files.exists(filePath)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "이미지 이름의 파일이 존재하지 않음");
			return;
		}
		
		String mime = application.getMimeType(imageName);
		
		response.setContentType(mime);
		
		try(
			ServletOutputStream out = response.getOutputStream();
		){
			Files.copy(filePath, out);
		}
	}

}
