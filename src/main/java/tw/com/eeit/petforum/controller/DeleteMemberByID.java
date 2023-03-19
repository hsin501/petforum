package tw.com.eeit.petforum.controller;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.eeit.petforum.conn.ConnectionFactory;
import tw.com.eeit.petforum.model.dao.MemberDAO;

@WebServlet("/DeleteMemberByID.do")
public class DeleteMemberByID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer mID = Integer.valueOf(request.getParameter("mID"));

		try {
			Connection conn = ConnectionFactory.getConnection();
			MemberDAO mDAO = new MemberDAO(conn);
			mDAO.deleteMemberByID(mID);

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect("ShowAllMember.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
