package meli.mutantes.lambda;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DNAAnalysisResultStatHandlerTest {
	
	private static final String INPUT = 
			"{\n" + 
			"  \"Records\": [\n" + 
			"    {\n" + 
			"      \"eventID\": \"1\",\n" + 
			"      \"eventVersion\": \"1.0\",\n" + 
			"      \"dynamodb\": {\n" + 
			"        \"Keys\": {\n" + 
			"          \"id\": {\n" + 
			"            \"N\": \"1\"\n" + 
			"          }\n" + 
			"        },\n" + 
			"        \"NewImage\": {\n" + 
			"          \"isMutant\": {\n" + 
			"            \"BOOL\": \"true\"\n" + 
			"          },\n" + 
			"          \"id\": {\n" + 
			"            \"N\": \"1\"\n" + 
			"          }\n" + 
			"        },\n" + 
			"        \"StreamViewType\": \"NEW_IMAGES\",\n" + 
			"        \"SequenceNumber\": \"111\",\n" + 
			"        \"SizeBytes\": 26\n" + 
			"      },\n" + 
			"      \"awsRegion\": \"us-east-1\",\n" + 
			"      \"eventName\": \"INSERT\",\n" + 
			"      \"eventSourceARN\": \"eventsourcearn\",\n" + 
			"      \"eventSource\": \"aws:dynamodb\"\n" + 
			"    }\n" + 
			"  ]\n" + 
			"}";

	//@Test
	public void executeLambdaHandler_shouldUpdateStatsTable() throws Exception {
		/*
		OutputStream output = new ByteArrayOutputStream();
		Context context = Mockito.mock(Context.class);
		InputStream input = createInputStream();
		
		// new DNAAnalysisResultStatHandler().handleRequest(input, output, context);
		// new DNAAnalysisResultStatHandler().handleRequest(input, context);
		 * 
		 */
	}

	private InputStream createInputStream() {
		return new ByteArrayInputStream(INPUT.getBytes());
	}
}
