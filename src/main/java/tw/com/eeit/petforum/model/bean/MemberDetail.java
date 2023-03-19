package tw.com.eeit.petforum.model.bean;

import java.io.Serializable;

public class MemberDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int mDID;
	private String name;
	private int age;
	private String address;
	private String photo;
	private int f_mID;
	private Member member;

	public int getmDID() {
		return mDID;
	}

	public void setmDID(int mDID) {
		this.mDID = mDID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getF_mID() {
		return f_mID;
	}

	public void setF_mID(int f_mID) {
		this.f_mID = f_mID;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "MemberDetail [mDID=" + mDID + ", name=" + name + ", age=" + age + ", address=" + address + ", photo="
				+ photo + ", f_mID=" + f_mID + ", member=" + member + "]";
	}

}
