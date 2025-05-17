pipeline {
    agent any

    environment {
        EC2_USER = 'ec2-user'
        EC2_IP = '13.201.23.88'            
        SSH_CREDENTIALS_ID = 'my-ec2-key'
        APP_DIR = '/home/ec2-user/app'
        JAR_NAME = 'airline-management-system.jar'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/archanadby05/CI-CD_pipeline_assignment.git', branch: 'master'
            }
        }

         stage('Clean') {
            steps {
                script {
                    def workspace = pwd()
                    bat "docker run --rm -v ${workspace}:/app -w /app maven:3.8.5-openjdk-11 mvn clean"
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    def workspace = pwd()
                    bat "docker run --rm -v ${workspace}:/app -w /app maven:3.8.5-openjdk-11 mvn clean package -DskipTests"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    def workspace = pwd()
                    bat "docker run --rm -v ${workspace}:/app -w /app maven:3.8.5-openjdk-11 mvn test"
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent([env.SSH_CREDENTIALS_ID]) {
                    bat """
                    scp target/${JAR_NAME} ${EC2_USER}@${EC2_IP}:${APP_DIR}/${JAR_NAME}
                    ssh ${EC2_USER}@${EC2_IP} 'pkill -f ${JAR_NAME} || true'
                    ssh ${EC2_USER}@${EC2_IP} 'nohup java -jar ${APP_DIR}/${JAR_NAME} > ${APP_DIR}/app.log 2>&1 &'
                    """
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Check logs."
        }
    }
}
