package tw.com.eeit.petforum.model.bean;

import java.io.Serializable;
import java.util.Arrays;

public class Pet implements Serializable {
	private static final long serialVersionUID = 1L;

	private int pID;
	private String type;
	private String name;
	private int age;
	private byte[] photo;
	private int f_mID;
	private Member member;

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
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
		return "Pet [pID=" + pID + ", type=" + type + ", name=" + name + ", age=" + age + ", photo="
				+ Arrays.toString(photo) + ", member=" + member + "]";
	}

}
