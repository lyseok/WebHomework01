package kr.or.ddit.factorial;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/05/factorialexport")
public class Factorial2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long output = (long)request.getAttribute("number");
		if(output == -1) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "양의 정수를 입력하세요.");
			return;
		}
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(output));
	}

}
