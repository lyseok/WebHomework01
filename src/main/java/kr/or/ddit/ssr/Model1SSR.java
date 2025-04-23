package kr.or.ddit.ssr;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image/model1SSR.do")
public class Model1SSR extends HttpServlet {
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

		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<body>");
		sb.append("<form action='" + request.getContextPath() + "/image/streaming.do'>");
		sb.append("<select onchange='this.form.submit()' name='image'>");
		sb.append(options);
		sb.append("</select>");
		sb.append("</form>");
		sb.append("</body>");
		sb.append("</html>");

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter();) {
			out.print(sb);
		}
		

	}
}
