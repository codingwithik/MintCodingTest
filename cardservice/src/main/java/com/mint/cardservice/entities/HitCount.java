package com.mint.cardservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HitCount implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String accountNumber;
	private Integer count;
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	@PrePersist
	private void setAddedDate() {
		this.dateCreated = new Date();
	}
	
	@PreUpdate
	private void setUpdatedDate() {
		this.dateUpdated = new Date();
	}
	
	@Override
	public boolean equals(Object obj) {

		return this.id.equals(((HitCount) obj).getId());

	}

}
