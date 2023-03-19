package tw.com.eeit.petforum.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.model.bean.MemberDetail;
import tw.com.eeit.petforum.model.bean.Pet;

/**
 * 與會員資料表相關的方法，欲使用必須傳送建構子Connection連線物件才可建立。
 */
public class MemberDAO {
	private Connection conn;

	public MemberDAO(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 根據會員ID尋找對應會員，不包含寵物資訊。<br>
	 * 
	 * @param memberID 會員ID<br>
	 * @return Member 會員的JavaBean。
	 */
	public Member findMemberByID(int memberID) {
		try {
			String SQL = "SELECT * FROM [PetForum].[dbo].[Member] AS [m] "
					+ "RIGHT JOIN [PetForum].[dbo].[MemberDetail] AS [md] " + "ON [m].[mID] = [md].[f_mID]"
					+ "WHERE [mID] = ?";

			Member member = null;

			PreparedStatement preState = conn.prepareStatement(SQL);
			preState.setInt(1, memberID);

			ResultSet rs = preState.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setmID(rs.getInt("mID"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setEnabled(rs.getInt("enabled"));
				member.setLevel(rs.getString("level"));

				MemberDetail memberDetail = new MemberDetail();
				memberDetail.setName(rs.getString("name"));
				memberDetail.setAge(rs.getInt("age"));
				memberDetail.setAddress(rs.getString("address"));
				memberDetail.setPhoto(rs.getString("photo"));

				member.setMemberDetail(memberDetail);
			}

			rs.close();
			preState.close();

			return member;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 取得所有會員的資訊，不包含寵物資訊。<br>
	 * 
	 * @return List 會員資訊列表
	 */
	public List<Member> findAllMembers() {
		try {
			String SQL = "SELECT * FROM [PetForum].[dbo].[Member] AS [m] "
					+ "RIGHT JOIN [PetForum].[dbo].[MemberDetail] AS [md] " + "ON [m].[mID] = [md].[f_mID]";

			List<Member> memberList = new ArrayList<Member>();

			PreparedStatement preState = conn.prepareStatement(SQL);

			ResultSet rs = preState.executeQuery();

			while (rs.next()) {

				Member member = new Member();
				member.setmID(rs.getInt("mID"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setEnabled(rs.getInt("enabled"));
				member.setLevel(rs.getString("level"));

				MemberDetail memberDetail = new MemberDetail();
				memberDetail.setName(rs.getString("name"));
				memberDetail.setAge(rs.getInt("age"));
				memberDetail.setAddress(rs.getString("address"));
				memberDetail.setPhoto(rs.getString("photo"));

				member.setMemberDetail(memberDetail);

				memberList.add(member);
			}

			rs.close();
			preState.close();

			return memberList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 根據會員ID尋找對應會員，包含寵物資訊。<br>
	 * 
	 * @param memberID 會員ID<br>
	 * @return Member 會員的JavaBean。
	 */
	public Member findMemberWithPetsByID(int memberID) {

		try {
			String SQL = "SELECT [mID],[password],[email],[enabled],[level],[md].[name] AS [mName],[md].[age] AS [mAge],[address],[md].[photo] AS [mPhoto],"
					+ "[pID],[type],[p].[name] AS [pName],[p].[age] AS [pAge]"
					+ "FROM [PetForum].[dbo].[Member] AS [m] " + "RIGHT JOIN [PetForum].[dbo].[MemberDetail] AS [md] "
					+ "ON [m].[mID] = [md].[f_mID]" + "RIGHT JOIN [PetForum].[dbo].[Pet] AS [p] "
					+ "ON [m].[mID] = [p].[f_mID]" + "WHERE [mID] = ?";

			Member member = null;

			PreparedStatement preState = conn.prepareStatement(SQL);
			preState.setInt(1, memberID);

			ResultSet rs = preState.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setmID(rs.getInt("mID"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setEnabled(rs.getInt("enabled"));
				member.setLevel(rs.getString("level"));

				MemberDetail memberDetail = new MemberDetail();
				memberDetail.setName(rs.getString("mName"));
				memberDetail.setAge(rs.getInt("mAge"));
				memberDetail.setAddress(rs.getString("address"));
				memberDetail.setPhoto(rs.getString("mPhoto"));

				List<Pet> petList = new ArrayList<Pet>();
				do {
					Pet pet = new Pet();
					pet.setpID(rs.getInt("pID"));
					pet.setType(rs.getString("type"));
					pet.setName(rs.getString("pName"));
					pet.setAge(rs.getInt("pAge"));
					petList.add(pet);
				} while (rs.next());

				member.setMemberDetail(memberDetail);
				member.setPetList(petList);
			}

			rs.close();
			preState.close();

			return member;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得所有會員的資訊，包含寵物資訊。<br>
	 * 
	 * @return List 會員資訊列表
	 */
	public List<Member> findAllMembersWithPets() {
		try {
			String SQL = "SELECT [mID],[password],[email],[enabled],[level],[md].[name] AS [mName],[md].[age] AS [mAge],[address],[md].[photo] AS [mPhoto],"
					+ "[pID],[type],[p].[name] AS [pName],[p].[age] AS [pAge]"
					+ "FROM [PetForum].[dbo].[Member] AS [m] " + "RIGHT JOIN [PetForum].[dbo].[MemberDetail] AS [md] "
					+ "ON [m].[mID] = [md].[f_mID]" + "RIGHT JOIN [PetForum].[dbo].[Pet] AS [p] "
					+ "ON [m].[mID] = [p].[f_mID]";

			List<Member> memberList = new ArrayList<Member>();

			PreparedStatement preState = conn.prepareStatement(SQL);

			ResultSet rs = preState.executeQuery();

			int currentMemberID = -1;
			Member currentMember = null;

			while (rs.next()) {

				if (rs.getInt("mID") != currentMemberID) {
					currentMember = new Member();
					currentMember.setmID(rs.getInt("mID"));
					currentMember.setEmail(rs.getString("email"));
					currentMember.setPassword(rs.getString("password"));
					currentMember.setEnabled(rs.getInt("enabled"));
					currentMember.setLevel(rs.getString("level"));

					MemberDetail memberDetail = new MemberDetail();
					memberDetail.setName(rs.getString("mName"));
					memberDetail.setAge(rs.getInt("mAge"));
					memberDetail.setAddress(rs.getString("address"));
					memberDetail.setPhoto(rs.getString("mPhoto"));

					currentMember.setMemberDetail(memberDetail);
					currentMemberID = rs.getInt("mID");
					currentMember.setPetList(new ArrayList<Pet>());
					memberList.add(currentMember);

				}

				Pet pet = new Pet();
				pet.setpID(rs.getInt("pID"));
				pet.setType(rs.getString("type"));
				pet.setName(rs.getString("pName"));
				pet.setAge(rs.getInt("pAge"));
				currentMember.getPetList().add(pet);
			}

			rs.close();
			preState.close();

			return memberList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根據傳入的JavaBean建立對應的會員，但不建立詳細資料<br>
	 * 
	 * @param member 需包含會員基本資料
	 * @return true 表示建立成功、false 表示建立失敗
	 */
	public boolean createMemberWithoutDetail(Member member) {
		try {
			String SQL = "INSERT INTO [PetForum].[dbo].[Member]([email],[password],[enabled],[level]) VALUES(?, ?, ?, ?)";

			PreparedStatement preState = conn.prepareStatement(SQL);
			preState.setString(1, member.getEmail());
			preState.setString(2, member.getPassword());
			preState.setInt(3, member.getEnabled());
			preState.setString(4, member.getLevel());

			preState.execute();
			preState.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 根據傳入的JavaBean建立對應的會員，同時建立詳細資料；<br>
	 * 若發生錯誤則會roll back。<br>
	 * 
	 * @param member 需包含會員基本資料、詳細資料
	 * @return true 表示建立成功、false 表示建立失敗
	 */
	public boolean createMember(Member member) {
		try {

			String memberSQL = "INSERT INTO [PetForum].[dbo].[Member]([email],[password],[enabled],[level]) VALUES(?, ?, ?, ?)";

			conn.setAutoCommit(false);

			PreparedStatement preState = conn.prepareStatement(memberSQL, 1);
			preState.setString(1, member.getEmail());
			preState.setString(2, member.getPassword());
			preState.setInt(3, member.getEnabled());
			preState.setString(4, member.getLevel());

			preState.executeUpdate();

			ResultSet rs = preState.getGeneratedKeys();
			if (rs.next()) {
				String memberDetailSQL = "INSERT INTO [PetForum].[dbo].[MemberDetail]([name],[age],[address],[photo],[f_mID]) VALUES(?, ?, ?, ?, ?)";
				preState = conn.prepareStatement(memberDetailSQL);
				preState.setString(1, member.getMemberDetail().getName());
				preState.setInt(2, member.getMemberDetail().getAge());
				preState.setString(3, member.getMemberDetail().getAddress());
				preState.setString(4, member.getMemberDetail().getPhoto());
				preState.setInt(5, rs.getInt(1));
			}

			preState.execute();
			preState.close();

			conn.commit();
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 根據ID刪除對應會員與其詳細資料。<br>
	 * 
	 * @param memberID 會員ID
	 * @return true 表示刪除成功、false 表示刪除失敗
	 */
	public boolean deleteMemberByID(int memberID) {
		try {
			String memberDetailSQL = "DELETE FROM [PetForum].[dbo].[MemberDetail] WHERE f_mID = ?";

			conn.setAutoCommit(false);

			PreparedStatement preState = conn.prepareStatement(memberDetailSQL);
			preState.setInt(1, memberID);
			preState.executeUpdate();

			String memberSQL = "DELETE FROM [PetForum].[dbo].[Member] WHERE mID = ?";
			preState = conn.prepareStatement(memberSQL);
			preState.setInt(1, memberID);
			preState.executeUpdate();

			conn.commit();
			conn.setAutoCommit(true);

			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 根據ID修改對應會員的資料，不修改詳細資料。<br>
	 * 
	 * @param memberID 會員ID
	 * @param member   欲修改的資料
	 * @return true 表示刪除成功、false 表示刪除失敗
	 */
	public boolean updateMember(int memberID, Member member) {
		try {
			String SQL = "UPDATE [PetForum].[dbo].[Member] "
					+ "SET [email] = ?, [password] = ?, [enabled] = ?, [level] = ? WHERE mID = ?";

			PreparedStatement preState = conn.prepareStatement(SQL);
			preState.setString(1, member.getEmail());
			preState.setString(2, member.getPassword());
			preState.setInt(3, member.getEnabled());
			preState.setString(4, member.getLevel());
			preState.setInt(5, memberID);
			preState.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}
}
