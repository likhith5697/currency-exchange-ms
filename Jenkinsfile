pipeline {
    agent any

    environment {
        IMAGE_NAME = "currency-exchange-service"
        DOCKERHUB_USERNAME = "likhith2k"
        DOCKERHUB_CREDENTIALS_ID = "dockerhub-cred"
        CONTAINER_NAME = "currency-exchange-container-2.0"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/likhith5697/currency-exchange-ms.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withDockerRegistry([credentialsId: "$DOCKERHUB_CREDENTIALS_ID", url: ""]) {
                    sh "docker tag $IMAGE_NAME $DOCKERHUB_USERNAME/$IMAGE_NAME"
                    sh "docker push $DOCKERHUB_USERNAME/$IMAGE_NAME"
                }
            }
        }



        stage('Deploying the docker container') {
            steps{
                script{
                    sh "docker rm -f $CONTAINER_NAME || true"
                    sh "docker run -d --name $CONTAINER_NAME -p 8082:8082 $DOCKERHUB_USERNAME/$IMAGE_NAME"
                }
            }
        }
    }
}
