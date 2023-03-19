package tw.com.eeit.petforum.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.eeit.petforum.conn.ConnectionFactory;
import tw.com.eeit.petforum.model.bean.Pet;

/**
 * Servlet implementation class ShowAllPets
 */
@WebServlet("/ShowAllPets.do")
public class ShowAllPets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection conn = ConnectionFactory.getConnection();

			PreparedStatement preState = conn.prepareStatement("SELECT * FROM [PetForum].[dbo].[Pet]");

			ResultSet rs = preState.executeQuery();

			ArrayList<Pet> pList = new ArrayList<Pet>();
			while (rs.next()) {
				Pet p = new Pet();
				p.setpID(rs.getInt("pID"));
				p.setName(rs.getString("name"));
				p.setPhoto(rs.getBytes("photo"));

				pList.add(p);
			}

			rs.close();

			preState.close();

			conn.close();

			request.setAttribute("petList", pList);

			request.getRequestDispatcher("ShowAllPet.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
