package tw.com.eeit.petforum.init;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Base64;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import tw.com.eeit.petforum.conn.ConnectionFactory;
import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.model.bean.MemberDetail;
import tw.com.eeit.petforum.model.bean.Pet;

@WebListener
public class Initialize implements ServletContextListener {
	private Connection conn;
	private String realPath;

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		realPath = sce.getServletContext().getRealPath("");

		try {
			conn = ConnectionFactory.getConnection();
			createDataBase();
			createMemberTabale();
			createMemberDetailTabale();
			createPetTabale();
			createLikesTabale();
			insertIntoDataToMember();
			insertIntoDataToMemberDetail();
			insertIntoDataToPet();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createDataBase() throws Exception {
		String SQL = "IF DB_ID('PetForum') IS NULL CREATE DATABASE PetForum";

		Statement state = conn.createStatement();
		state.execute(SQL);
		state.close();
	}

	private void createMemberTabale() throws Exception {
		String SQL = "IF OBJECT_ID('[PetForum].[dbo].[Member]') IS NULL " + "CREATE TABLE [PetForum].[dbo].[Member]("
				+ "[mID] INT IDENTITY(1,1) PRIMARY KEY NOT NULL," + "[email] NVARCHAR(100) NOT NULL,"
				+ "[password] NVARCHAR(50) NOT NULL," + "[enabled] BIT NOT NULL," + "[level] NVARCHAR(50) NOT NULL)";

		Statement state = conn.createStatement();
		state.execute(SQL);
		state.close();
	}

	private void createMemberDetailTabale() throws Exception {
		String SQL = "IF OBJECT_ID('[PetForum].[dbo].[MemberDetail]') IS NULL "
				+ "CREATE TABLE [PetForum].[dbo].[MemberDetail](" + "[mDID] INT IDENTITY(1,1) PRIMARY KEY NOT NULL,"
				+ "[name] NVARCHAR(20)," + "[age] INT," + "[address] NVARCHAR(100)," + "[photo] NVARCHAR(MAX),"
				+ "[f_mID] INT FOREIGN KEY REFERENCES Member(mID))";
		Statement state = conn.createStatement();
		state.execute(SQL);
		state.close();
	}

	private void createPetTabale() throws Exception {
		String SQL = "IF OBJECT_ID('[PetForum].[dbo].[Pet]') IS NULL " + "CREATE TABLE [PetForum].[dbo].[Pet]("
				+ "[pID] INT IDENTITY(1,1) PRIMARY KEY NOT NULL," + "[type] NVARCHAR(20) NOT NULL,"
				+ "[name] NVARCHAR(20)," + "[age] INT," + "[photo] VARBINARY(MAX),"
				+ "[f_mID] INT FOREIGN KEY REFERENCES Member(mID))";
		Statement state = conn.createStatement();
		state.execute(SQL);
		state.close();
	}

	private void createLikesTabale() throws Exception {
		String SQL = "IF OBJECT_ID('[PetForum].[dbo].[Likes]') IS NULL " + "CREATE TABLE [PetForum].[dbo].[Likes]("
				+ "[lID] INT IDENTITY(1,1) PRIMARY KEY NOT NULL," + "[time] DATETIME NOT NULL,"
				+ "[f_mID] INT FOREIGN KEY REFERENCES Member(mID)," + "[f_pID] INT FOREIGN KEY REFERENCES Pet(pID))";
		Statement state = conn.createStatement();
		state.execute(SQL);
		state.close();
	}

	private void insertIntoDataToMember() throws Exception {
		String SQL = "INSERT INTO [PetForum].[dbo].[Member]([email],[password],[enabled],[level]) VALUES (?, ?, ?, ?)";

		if (conn.createStatement().executeQuery("SELECT mID FROM [PetForum].[dbo].[Member]").next()) {
			return;
		}

		BufferedReader br = new BufferedReader(new FileReader(realPath + "WEB-INF/ForInitialization/Member.json"));
		List<Member> memberList = new Gson().fromJson(br, new TypeToken<List<Member>>() {
		}.getType());
		br.close();

		PreparedStatement preState = conn.prepareStatement(SQL);

		for (Member m : memberList) {
			preState.setString(1, m.getEmail());
			preState.setString(2, m.getPassword());
			preState.setInt(3, m.getEnabled());
			preState.setString(4, m.getLevel());
			preState.addBatch();
		}
		preState.executeBatch();

		preState.close();

	}

	private void insertIntoDataToMemberDetail() throws Exception {
		String SQL = "INSERT INTO [PetForum].[dbo].[MemberDetail]([name],[age],[address],[photo],[f_mID]) VALUES (?, ?, ?, ?, ?)";

		if (conn.createStatement().executeQuery("SELECT mDID FROM [PetForum].[dbo].[MemberDetail]").next()) {
			return;
		}

		BufferedReader br = new BufferedReader(
				new FileReader(realPath + "WEB-INF/ForInitialization/MemberDetail.json"));
		List<MemberDetail> memberDetailList = new Gson().fromJson(br, new TypeToken<List<MemberDetail>>() {
		}.getType());
		br.close();

		PreparedStatement preState = conn.prepareStatement(SQL);

		for (MemberDetail mD : memberDetailList) {
			preState.setString(1, mD.getName());
			preState.setInt(2, mD.getAge());
			preState.setString(3, mD.getAddress());

			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(realPath + "WEB-INF/ForInitialization/users/" + mD.getPhoto() + ".png"));

			preState.setString(4, "data:image/png;base64," + Base64.getEncoder().encodeToString(bis.readAllBytes()));
			bis.close();

			preState.setInt(5, mD.getF_mID());
			preState.addBatch();
		}
		preState.executeBatch();

		preState.close();
	}

	private void insertIntoDataToPet() throws Exception {
		String SQL = "INSERT INTO [PetForum].[dbo].[Pet]([type],[name],[age],[photo],[f_mID]) VALUES (?, ?, ?, ?, ?)";

		if (conn.createStatement().executeQuery("SELECT pID FROM [PetForum].[dbo].[Pet]").next()) {
			return;
		}

		BufferedReader br = new BufferedReader(new FileReader(realPath + "WEB-INF/ForInitialization/Pet.json"));
		List<Pet> petList = new Gson().fromJson(br, new TypeToken<List<Pet>>() {
		}.getType());
		br.close();

		PreparedStatement preState = conn.prepareStatement(SQL);

		for (Pet p : petList) {
			preState.setString(1, p.getType());
			preState.setString(2, p.getName());
			preState.setInt(3, p.getAge());

			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(realPath
					+ "WEB-INF/ForInitialization/" + p.getType() + "/" + p.getType() + "-" + p.getName() + ".jpg"));

			preState.setBytes(4, bis.readAllBytes());
			bis.close();

			preState.setInt(5, p.getF_mID());
			preState.addBatch();
		}
		preState.executeBatch();

		preState.close();
	}
}
