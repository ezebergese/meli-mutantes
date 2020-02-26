package meli.mutantes.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.data.annotation.Transient;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBTable(tableName = "DnaAnalysisResultStats")
public class DnaAnalysisResultStats {
	
	@JsonIgnore
	private Integer id;
	
	@JsonProperty("count_mutant_dna")
	private Integer mutantCount;
	
	@JsonProperty("count_human_dna")
	private Integer nonMutantCount;
	
	@Transient
	private Integer ratio;
	
	
	public DnaAnalysisResultStats() {
		
	}
	
	@DynamoDBHashKey
	@DynamoDBAttribute(attributeName = "id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@DynamoDBAttribute(attributeName = "mutant_count")
	public Integer getMutantCount() {
		return mutantCount;
	}
	
	public void setMutantCount(Integer count) {
		this.mutantCount = count ;
	}
	
	@DynamoDBAttribute(attributeName = "non_mutant_count")
	public Integer getNonMutantCount() {
		return nonMutantCount;
	}
	
	public void setNonMutantCount(Integer count) {
		this.nonMutantCount = count ;
	}
	
	public BigDecimal getRatio() {
		if(nonMutantCount == 0)
			return BigDecimal.ZERO;
		return new BigDecimal(mutantCount)
				.divide(new BigDecimal(nonMutantCount), 2, RoundingMode.FLOOR);
	}
}
