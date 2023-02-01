package com.api.campaign.entity;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campaignRegistration")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class CampaignRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer campaignid;
	private String campaignName;
	private Integer campaignClassfification;
	private Integer evaluationCampaignid;
	private String evaluationCampaignName;
	private String nickname;
	private String description;
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private Map<String, String> Message;
	private Integer campaignkind;
	private String executionDate;
//	@Column(name = "endDate")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date endDate = new Date (System.currentTimeMillis());	
    @Temporal(value=TemporalType.TIMESTAMP)
	private Date endDate;
	private Integer compensation;
	private String notificationNumber;
	private Boolean campaignApproval;
	private Boolean updateApproval;
	private Boolean mechanicalRequired;
	private Integer deliveryPackageKind;
	private Integer filteringConditionsType;
	private Integer updatedUsernotificationinfold;
	private String updateUsernotificationinfoName;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private List<Object> rxswinUpdateinfos;
	private String rxswin;
	private Integer rxswinUpdateinfold;
	private String rxswinUpdateInfoName;
	


}
