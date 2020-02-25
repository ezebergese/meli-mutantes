import subprocess
import os.path
import json


def deploy():
    role_arn = os.getenv('AWS_ROLE_ARN')
    role_session_name = 'DeployAwsSession'
    print("Assuming role...")
    command = 'aws sts assume-role --role-arn {} --role-session-name {}'
    output = subprocess.getoutput(command.format(role_arn, role_session_name))
    output_json = json.loads(output)
    key = output_json['Credentials']['AccessKeyId']
    secret = output_json['Credentials']['SecretAccessKey']
    access_token = output_json['Credentials']['SessionToken']
    my_env = os.environ.copy()
    my_env["AWS_ACCESS_KEY_ID"] = key
    my_env["AWS_SECRET_ACCESS_KEY"] = secret
    my_env["AWS_SESSION_TOKEN"] = access_token

    print("Deploying to ecs...")
    command = 'aws ecs update-service --cluster meli-cluster --service meli-service ' \
    + '--service meli-service --desired-count 1 --force-new-deployment --region us-east-1'
    output = subprocess.getoutput(command)

    print("Deploying lambda...")
    command = 'aws lambda update-function-code --function-name  updateDnaAnalysisResultStats ' \
    + '--zip-file fileb://./meli-mutantes-lambda/build/libs/meli-mutantes-lambda.jar ' \
    + '--region us-east-1'
    output = subprocess.getoutput(command)

    return my_env


if __name__ == '__main__':
    deploy()
