package kr.or.ddit.csr;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image/csrImageListServlet2.do")
public class CsrImageListServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private String folderPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		folderPath = application.getInitParameter("imageFolder");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File folder = new File(folderPath);
		String[] children = folder.list((d, n)->{
			String mime = application.getMimeType(n);
			return mime != null && mime.startsWith("image");
		});

		request.setAttribute("children", children);
		request.getRequestDispatcher("/image/crsImageListDataServlet.do").forward(request, response);
	}

}
