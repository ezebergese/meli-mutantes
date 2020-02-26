import subprocess
import shlex
import os.path
import json


def deploy():
    role_arn = os.getenv('AWS_ROLE_ARN')
    role_session_name = 'DeployAwsSession'
    print("Assuming role...")
    command = 'aws sts assume-role --role-arn {} --role-session-name {}'
    command = command.format(role_arn, role_session_name)
    process = subprocess.Popen(shlex.split(command), stdout=subprocess.PIPE)
    response, _ = process.communicate()
    print(response.decode('utf-8').strip())
    json_object = json.loads(response.decode('utf-8').strip())
    key = json_object['Credentials']['AccessKeyId']
    secret = json_object['Credentials']['SecretAccessKey']
    access_token = json_object['Credentials']['SessionToken']
    my_env = os.environ.copy()
    my_env["AWS_ACCESS_KEY_ID"] = key
    my_env["AWS_SECRET_ACCESS_KEY"] = secret
    my_env["AWS_SESSION_TOKEN"] = access_token

    print("Deploying to ecs...")
    command = 'aws ecs update-service --cluster meli-cluster --service meli-service ' \
    + '--service meli-service --desired-count 1 --force-new-deployment --region us-east-1'
    process = subprocess.Popen(shlex.split(command), stdout=subprocess.PIPE, env=my_env)
    response, _ = process.communicate()
    print(response)

    print("Deploying lambda...")
    command = 'aws lambda update-function-code --function-name  updateDnaAnalysisResultStats ' \
    + '--zip-file fileb://./meli-mutantes-lambda/build/libs/meli-mutantes-lambda.jar ' \
    + '--region us-east-1'
    process = subprocess.Popen(shlex.split(command), stdout=subprocess.PIPE, env=my_env)
    response, _ = process.communicate()
    print(response)

    return my_env


if __name__ == '__main__':
    deploy()
