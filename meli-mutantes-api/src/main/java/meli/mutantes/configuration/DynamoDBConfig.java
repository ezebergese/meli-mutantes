package meli.mutantes.configuration;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackages = "meli.mutantes.repository")
public class DynamoDBConfig {

    @Value("${DYNAMODB_ENDPOINT:}")
    private String dynamoDbEndpoint;
    
    
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
    	AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClient.builder();
        if (!StringUtils.isEmpty(dynamoDbEndpoint)) {
        	builder.setEndpointConfiguration(new EndpointConfiguration(dynamoDbEndpoint, builder.getRegion()));
        }
         
        return builder.build();
    }
}