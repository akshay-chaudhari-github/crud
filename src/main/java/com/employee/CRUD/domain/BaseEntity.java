package com.employee.CRUD.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2051933567093349584L;

	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@Column(insertable = true, updatable = false)
	private Instant createdOn;
	
	@Column(insertable = false, updatable = true)
	private Instant updatedOn;
	
	@Column(insertable = false, updatable = true)
	private String updatedBy;
	
	@Column(insertable = true, updatable = false)
	private String createdBy;

	@PrePersist
	public void populateAuditable(){
		this.setCreatedOn(Instant.now());
		this.setCreatedBy("audit");
	}

	@PreUpdate
	public void populateUpdate(){
		this.setUpdatedOn(Instant.now());
		this.setUpdatedBy("audit");
	}
}
