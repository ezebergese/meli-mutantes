package meli.mutantes.lambda;

import java.util.Map;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Record;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;

public class DNAAnalysisResultStatHandler implements RequestHandler<DynamodbEvent, Void>{
	
	private static final DynamoDB dynamoDB = createDynamoDB();
	
	private static DynamoDB createDynamoDB() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
	            .withEndpointConfiguration(
	            		new AwsClientBuilder.EndpointConfiguration("http://localhost:4569", "us-east-1"))
	            .build();
		return new DynamoDB(client);
	}
	/*
	@SuppressWarnings("unchecked")
	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> event = objectMapper.readValue(input, Map.class);
		Collection<Map<String, Object>> records = (Collection<Map<String, Object>>)event.get("Records");
		
		for(Map<String, Object> record : records) {
			Map<String, Object> dynamodb = (Map<String, Object>) record.get("dynamodb");
			Map<String, AttributeValue> newImage = (Map<String, AttributeValue>) dynamodb.get("NewImage");
			Item item = ItemUtils.toItem(newImage);
			updateStats(item);
		}
	}

	private void updateStats(Item item) {
		boolean isMutant = item.getBoolean("isMutant");
		UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("id", 1)
		            .withUpdateExpression("set :dnaCount = :dnaCount + 1")
		            .withConditionExpression("attribute_exists(:dnaCount)");
		 if(isMutant)
			 updateItemSpec.withNameMap(new NameMap().with(":dnaCount", "mutant_count"));
		 else
			 updateItemSpec.withNameMap(new NameMap().with(":dnaCount", "non_mutant_count"));
		 
		 updateItemSpec.withReturnValues(ReturnValue.UPDATED_NEW);
		 
		 UpdateItemOutcome outcome = dynamoDB.getTable("DnaAnalysisResultStats")
				 .updateItem(updateItemSpec);
		 
		 
	}
	*/

	@Override
	public Void handleRequest(DynamodbEvent event, Context context) {
		for(Record record : event.getRecords()) {
			Map<String, AttributeValue> item = record.getDynamodb().getNewImage();
			if(item != null)
				updateStats(item);
		}
		return null;
	}
	
	private void updateStats(Map<String, AttributeValue> item) {
		AttributeValue attribute = item.get("mutant");
		String isMutant = attribute.getN();
		UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("id", 1)
		            .withUpdateExpression("set #dnaCount = #dnaCount + :val")
		            .withValueMap(new ValueMap().with(":val", 1));
		 if(isMutant.equals("1"))
			 updateItemSpec.withNameMap(new NameMap().with("#dnaCount", "mutant_count"));
		 else
			 updateItemSpec.withNameMap(new NameMap().with("#dnaCount", "non_mutant_count"));
		 
		 updateItemSpec.withReturnValues(ReturnValue.NONE);
		 
		 UpdateItemOutcome outcome = dynamoDB.getTable("DnaAnalysisResultStats")
				 .updateItem(updateItemSpec);
	}
}
