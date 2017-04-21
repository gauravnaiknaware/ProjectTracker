package com.bonzai.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;


@Entity
@Table(name="campaign_user_master")
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
	
	@Column(name="client_name",length=250)
	private String client_name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",length=50)
	private long id;
	
	@Column(name="campaign_name",length=250)
	private String name;
	
	@Column(name="build_entry",length=50,columnDefinition="varchar(20) DEFAULT 'New'")
	private String build_entry;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="due_date",length=50)
	private Date due_date;
	
	@Column(name="assignee",length=50)
	private String assignee;
	
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

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
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

	public String getBuild_entry() {
		return build_entry;
	}

	public void setBuild_entry(String build_entry) {
		this.build_entry = build_entry;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
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

	public Campaign() {
	
	}

	
	

	public Campaign(Date date, String zone, String client_name, long id, String name, String build_entry, Date due_date,
			String assignee, String status, String priority, String team, String task, String description) {
		super();
		this.date = date;
		this.zone = zone;
		this.client_name = client_name;
		this.id = id;
		this.name = name;
		this.build_entry = build_entry;
		this.due_date = due_date;
		this.assignee = assignee;
		this.status = status;
		this.priority = priority;
		this.team = team;
		this.task = task;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Campaign [date=" + date + ", zone=" + zone + ", client_name=" + client_name + ", id=" + id + ", name="
				+ name + ", build_entry=" + build_entry + ", due_date=" + due_date + ", assignee=" + assignee
				+ ", status=" + status + ", priority=" + priority + ", team=" + team + ", task=" + task
				+ ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignee == null) ? 0 : assignee.hashCode());
		result = prime * result + ((build_entry == null) ? 0 : build_entry.hashCode());
		result = prime * result + ((client_name == null) ? 0 : client_name.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((due_date == null) ? 0 : due_date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campaign other = (Campaign) obj;
		if (assignee == null) {
			if (other.assignee != null)
				return false;
		} else if (!assignee.equals(other.assignee))
			return false;
		if (build_entry == null) {
			if (other.build_entry != null)
				return false;
		} else if (!build_entry.equals(other.build_entry))
			return false;
		if (client_name == null) {
			if (other.client_name != null)
				return false;
		} else if (!client_name.equals(other.client_name))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (due_date == null) {
			if (other.due_date != null)
				return false;
		} else if (!due_date.equals(other.due_date))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone))
			return false;
		return true;
	}

		
	
}
