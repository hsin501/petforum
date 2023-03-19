package tw.com.eeit.petforum.model.bean;

import java.io.Serializable;
import java.util.Date;

public class Likes implements Serializable {
	private static final long serialVersionUID = 1L;

	private int lID;
	private Date time;
	private Member member;
	private Pet pet;

	public int getlID() {
		return lID;
	}

	public void setlID(int lID) {
		this.lID = lID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

}
