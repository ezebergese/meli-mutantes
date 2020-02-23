package meli.mutantes.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.StreamSpecification;
import com.amazonaws.services.dynamodbv2.model.StreamViewType;

import meli.mutantes.model.dna.DNA;
import meli.mutantes.model.dna.DNAAnalysisResult;
import meli.mutantes.repository.DNAAnalysisResultRepository;

@Service
public class MutantServiceImpl implements MutantService {
	
	@Autowired
    private AmazonDynamoDB amazonDynamoDB;
	
	private DNAAnalysisResultRepository repository;
	
	
	@Autowired
	public MutantServiceImpl(DNAAnalysisResultRepository repository) {
		this.repository = repository;
	}
	
	public boolean isMutant(String[] dnaSequence) {
		DNA dna = new DNA(dnaSequence);
		boolean isMutant = dna.isMutant();
		DNAAnalysisResult result = new DNAAnalysisResult(dna, isMutant);
		// createTable();
		repository.save(result);
		
		return isMutant;
	}

	private void createTable() {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		
		StreamSpecification streamSpecification = new StreamSpecification()
	            .withStreamEnabled(true)
	            .withStreamViewType(StreamViewType.NEW_AND_OLD_IMAGES);
		
        CreateTableRequest tableRequest = dynamoDBMapper
          .generateCreateTableRequest(DNAAnalysisResult.class);
        tableRequest.setProvisionedThroughput(
          new ProvisionedThroughput(1L, 1L));
        
        tableRequest.setStreamSpecification(streamSpecification);
        
        amazonDynamoDB.createTable(tableRequest);
	}
}
