package kr.or.ddit.ssr;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image/model2SSR.do")
public class Model2SSR extends HttpServlet {
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
		String[] children = folder.list();
		String options = Arrays.stream(children).filter((fn) -> {
			String mime = application.getMimeType(fn);
			return mime != null && mime.startsWith("image/");
		}).map((fn) -> String.format("<option>%s</option>", fn)).collect(Collectors.joining("\n"));

		request.setAttribute("options", options);
		request.getRequestDispatcher("/WEB-INF/view/model2/imageList.jsp").forward(request, response);
	}

}
