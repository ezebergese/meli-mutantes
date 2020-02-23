package meli.mutantes.controller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "DnaAnalysisResultStats")
public class DnaAnalysisResultStats {
	
	private Integer id;
	private Integer mutantCount;
	private Integer nonMutantCount;
	
	
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
}
