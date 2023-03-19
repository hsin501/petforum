package tw.com.eeit.petforum.model.bean;

import java.io.Serializable;
import java.util.List;

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	private int mID;
	private String email;
	private String password;
	private int enabled;
	private String level;
	private MemberDetail memberDetail;
	private List<Pet> petList;

	public int getmID() {
		return mID;
	}

	public void setmID(int mID) {
		this.mID = mID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public MemberDetail getMemberDetail() {
		return memberDetail;
	}

	public void setMemberDetail(MemberDetail memberDetail) {
		this.memberDetail = memberDetail;
	}

	public List<Pet> getPetList() {
		return petList;
	}

	public void setPetList(List<Pet> petList) {
		this.petList = petList;
	}

	@Override
	public String toString() {
		return "Member [mID=" + mID + ", email=" + email + ", password=" + password + ", enabled=" + enabled
				+ ", level=" + level + ", memberDetail=" + memberDetail + ", petList=" + petList + "]";
	}


}
