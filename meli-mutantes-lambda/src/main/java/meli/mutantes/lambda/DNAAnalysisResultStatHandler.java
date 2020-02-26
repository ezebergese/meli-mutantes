package meli.mutantes.lambda;

import java.util.Map;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Record;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.util.StringUtils;

public class DNAAnalysisResultStatHandler implements RequestHandler<DynamodbEvent, Void>{
	
	private static DynamoDB dynamoDB = createDynamoDB();
	
	private static DynamoDB createDynamoDB() {
		String endpoint = System.getenv("DYNAMODB_ENDPOINT");
		AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();
		if(!StringUtils.isNullOrEmpty(endpoint))
			builder.setEndpointConfiguration(new EndpointConfiguration(endpoint, builder.getRegion()));
		AmazonDynamoDB client = builder.build();
		
		return new DynamoDB(client);
	}
	
	@Override
	public Void handleRequest(DynamodbEvent event, Context context) {
		for(Record record : event.getRecords()) {
			Map<String, AttributeValue> item = record.getDynamodb().getNewImage();
			if(item != null)
				updateStats(item);
		}
		return null;
	}
	
	/*
	 * For testing
	 */
	public Void handleRequest(DynamodbEvent event, Context context, DynamoDB dynamoDBToUse) {
		dynamoDB = dynamoDBToUse;
		return this.handleRequest(event, context);
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
		 
		 dynamoDB.getTable("DnaAnalysisResultStats").updateItem(updateItemSpec);
	}
}
