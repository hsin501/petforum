package tw.com.eeit.petforum.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Base64;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tw.com.eeit.petforum.conn.ConnectionFactory;
import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.model.dao.MemberDAO;

@WebServlet("/ShowAllMember.do")
public class ShowAllMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			MemberDAO memberDAO = new MemberDAO(conn);
			List<Member> memberList = memberDAO.findAllMembers();

			conn.close();

			request.setAttribute("mList", memberList);
			request.getRequestDispatcher("ShowAllMember.jsp").forward(request, response);

//			HttpSession session = request.getSession();
//			session.setAttribute("mList", memberList);
//			response.sendRedirect("ShowAllMember.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
