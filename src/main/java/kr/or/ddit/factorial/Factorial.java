package kr.or.ddit.factorial;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/05/factorial")
public class Factorial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	long f(int i){
		if(i <= 0) {
			throw new IllegalArgumentException("팩토리얼 연산의 피연산자는 양의 정수만 입력가능");
		}
		if(i == 1) {
			return 1;
		}
		return i * f(i-1);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("op");
		System.out.println(input);
		
		int num = Optional.ofNullable(input)
				.filter(in->in.matches("[0-9]{1,2}"))
				.map(str->Integer.parseInt(str))
				.filter(n->n>=1 && n<=10)
				.orElse(-1);

		boolean valid = num != -1;
		if(!valid) {
			request.setAttribute("number", -1l);
		} else {
			long output = f(num);
			request.setAttribute("number", output);			
		}
		
		request.getRequestDispatcher("/05/factorialexport").forward(request, response);		
		
	}

}
