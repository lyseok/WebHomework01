package kr.or.ddit.csr;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

@WebServlet("/image/crsImageListDataServlet.do")
public class CrsImageListDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] children = (String[])request.getAttribute("children");
		response.setContentType("application/json;charset=utf-8");
		try(
			PrintWriter out = response.getWriter();
		){
			out.print(new Gson().toJson(children));
		}
	}
}
