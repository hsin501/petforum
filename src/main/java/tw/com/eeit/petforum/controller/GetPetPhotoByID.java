package tw.com.eeit.petforum.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.eeit.petforum.conn.ConnectionFactory;
import tw.com.eeit.petforum.model.bean.Pet;

@WebServlet("/GetPetPhotoByID.do")
public class GetPetPhotoByID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer pID = Integer.valueOf(request.getParameter("pID"));

		try {
			Connection conn = ConnectionFactory.getConnection();

			PreparedStatement preState = conn.prepareStatement("SELECT * FROM [PetForum].[dbo].[Pet] WHERE pID = ?");
			preState.setInt(1, pID);
			ResultSet rs = preState.executeQuery();
			byte[] bytes = null;

			if (rs.next()) {
				bytes = rs.getBytes("photo");
			}

			rs.close();
			preState.close();
			conn.close();

			response.setContentType("image/jpeg");
			ServletOutputStream out = response.getOutputStream();

			out.write(bytes);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
