#/bin/bash
sh wait-for localstack:4569 -t 60 -- echo "dynamodb ready"
sh wait-for localstack:4574 -t 60 -- echo "lambda ready"
sh wait-for localstack:4568 -t 60 -- echo "kinesis ready"
sh wait-for localstack:4570 -t 60 -- echo "dynamodbstreams ready"
sh wait-for localstack:4586 -t 60 -- echo "cloudwatch ready"
sh setup.sh
