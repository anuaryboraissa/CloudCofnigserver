package com.example.share.Entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Messages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="msg_id")
	private long id;
	@Column(name="user_msg",nullable = false)
	private String msg;
	@Column(name="deleted")
	private int deleted;
	@Column(name="seen")
	private int seen;
	@Column(name="achieved")
	private int achieved;
	@Column(name="date")
    private String time;
	
	 @ManyToMany
		@JoinTable(
				name="grp_messages",
				joinColumns= @JoinColumn(
						name="msg_id"),
				inverseJoinColumns=@JoinColumn(
						name="Group_id")
				  )
	 private Collection<Groups> groups;
	
	public Collection<Groups> getGroups() {
		return groups;
	}

	public void setGroups(Collection<Groups> groups) {
		this.groups = groups;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@ManyToOne()
	@JoinColumn(name="stdFrom_id", referencedColumnName = "st_id")    
	private Student stdFrom;
	@ManyToOne()
	@JoinColumn(name="stdTo_id", referencedColumnName = "st_id")    
	private Student stdTo;
	
	public int getAchieved() {
		return achieved;
	}

	public void setAchieved(int achieved) {
		this.achieved = achieved;
	}

	public int getSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Student getStdFrom() {
		return stdFrom;
	}
	
	public void setStdFrom(Student stdFrom) {
		this.stdFrom = stdFrom;
	}
	
	public Student getStdTo() {
		return stdTo;
	}
	public void setStdTo(Student stdTo) {
		this.stdTo = stdTo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
