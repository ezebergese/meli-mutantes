import subprocess
import sys
import os.path
import json


def assume_role():
    role_arn = os.getenv('AWS_ROLE_ARN')
    role_session_name = 'DeployAwsSession'
    command = 'aws sts assume-role --role-arn {} --role-session-name {}'
    output = subprocess.getoutput(command.format(role_arn, role_session_name))
    print(output)
    output_json = json.loads(output)
    key = output_json['Credentials']['AccessKeyId']
    secret = output_json['Credentials']['SecretAccessKey']
    access_token = output_json['Credentials']['SessionToken']
    my_env = os.environ.copy()
    my_env["AWS_ACCESS_KEY_ID"] = key
    my_env["AWS_SECRET_ACCESS_KEY"] = secret
    my_env["AWS_SESSION_TOKEN"] = access_token
    return my_env


if __name__ == '__main__':
    env = assume_role()
    variables = 'export AWS_ACCESS_KEY_ID=' + env['AWS_ACCESS_KEY_ID'] \
        + ' AWS_SECRET_ACCESS_KEY=' + env['AWS_SECRET_ACCESS_KEY'] \
        + ' AWS_SESSION_TOKEN=' + env['AWS_SESSION_TOKEN']
    sys.exit(variables)
