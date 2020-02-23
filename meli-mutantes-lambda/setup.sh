#!/bin/bash
export AWS_ACCESS_KEY_ID=dummy
export AWS_SECRET_ACCESS_KEY=dummy
DYNAMODB_ENDPOINT=http://localstack:4569
LAMBDA_ENDPOINT=http://localstack:4574

aws --endpoint-url=http://localstack:4569 dynamodb create-table \
    --region us-east-1 \
    --table-name DnaAnalysisResult \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
    --stream-specification StreamEnabled=true,StreamViewType=NEW_IMAGE

aws --endpoint-url=http://localstack:4569 dynamodb create-table \
    --region us-east-1 \
    --table-name DnaAnalysisResultStats \
    --attribute-definitions AttributeName=id,AttributeType=N \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1

aws --endpoint-url=http://localstack:4569 dynamodb put-item \
    --region us-east-1 \
    --table-name DnaAnalysisResultStats \
    --item '{"id": {"N": "1"}, "mutant_count": {"N": "0"}, "non_mutant_count": {"N": "0"}}' \
    --condition-expression "attribute_not_exists(id)"

STREAM_ARN=$(aws --endpoint-url=http://localstack:4569 dynamodb describe-table \
    --region us-east-1 \
    --table-name DnaAnalysisResult \
    --query Table.LatestStreamArn)

STREAM_ARN="${STREAM_ARN%\"}"
STREAM_ARN="${STREAM_ARN#\"}"

aws --endpoint-url=http://localstack:4574 lambda create-function \
    --region us-east-1 \
    --function-name updateDnaAnalysisResultStats \
    --runtime java8 \
    --zip-file fileb://meli-mutantes-lambda.jar \
    --handler meli.mutantes.lambda.DNAAnalysisResultStatHandler \
    --role dummy

aws --endpoint-url=http://localstack:4574 lambda create-event-source-mapping \
    --region us-east-1 \
    --function-name updateDnaAnalysisResultStats \
    --batch-size 5 \
    --event-source-arn $STREAM_ARN
