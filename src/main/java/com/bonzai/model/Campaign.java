package com.bonzai.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;


@Entity
@Table(name="campaign_master")
@DynamicInsert
public class Campaign implements Serializable {
	
	/**
	 * form attributes
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="in_date",length=50,columnDefinition="DATE DEFAULT CURRENT_DATE")
	private Date date;
	
	@Column(name="zone",length=20)
	private String zone;
	
	@Column(name="clientName",length=250)
	private String clientName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",length=50)
	private long id;
	
	@Column(name="campaign_name",length=250)
	private String name;
	
	@Column(name="buildEntry",length=50,columnDefinition="varchar(20) DEFAULT 'New'")
	private String buildEntry;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dueDate",length=50)
	private Date dueDate;
	
	@Column(name="status",length=50)
	private String status;
	
	@Column(name="priority",length=50)
	private String priority;

	@Column(name="team",length=50)
	private String team;
	
	@Column(name="task",length=50)
	private String task;
	
	@Column(name="description",length=1000)
	private String description;
	
	@Column(name="state",length=50,columnDefinition="varchar(20) DEFAULT 'stop'")
	private String state;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuildEntry() {
		return buildEntry;
	}

	public void setBuildEntry(String buildEntry) {
		this.buildEntry = buildEntry;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
