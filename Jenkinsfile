pipeline {
    agent any
      environment {

            DOCKER_CREDENTIALS_ID = '3e79e975-e998-4c86-8b83-48a49a44ea77'
                   }
    stages {
        stage('Git') {
            steps {
                // Récupère le code depuis le dépôt Git
                git branch: 'FAHED', url: 'https://github.com/amira2200/Foyer-Projet.git'
            }
        }
        stage('Maven Build') {
            steps {
                // Compile le projet avec Maven
                sh "mvn clean install"
            }
        }
        stage('SonarQube Analysis') {
            environment {
                scannerHome = tool 'SonarQube Scanner'
            }
            steps {
                withSonarQubeEnv('SonarQube_Server') {
                    sh """
                        ${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=Projet_devops \
                        -Dsonar.sources=src \
                        -Dsonar.java.binaries=target/classes \
                        -Dsonar.host.url=http://192.168.50.4:9000 \
                        -Dsonar.login=sqa_579d194661251cb6789c226177fce0c6985c0581
                    """
                }
            }
        }
        stage('Mockito') {
                    steps {
                        sh 'mvn test'
                    }
        }
        stage('Nexus') {
            steps {
                script {
                    // Remplacez les valeurs par vos configurations spécifiques
                    def nexusUrl = "http://192.168.50.4:8081"
                    def artifactId = "tp-foyer"
                    def version = "5.0.0"
                    def groupId = "tn.esprit"

                    // Déployer l'artifact dans Nexus
                    sh """
                        mvn deploy:deploy-file -DgroupId=${groupId} \
                        -DartifactId=${artifactId} \
                        -Dversion=${version} \
                        -Dpackaging=jar \
                        -Dfile=target/${artifactId}-${version}.jar \
                        -DrepositoryId=deploymentRepo \
                        -Durl=${nexusUrl}/repository/maven-releases/

                    """
                }
            }
        }
        stage('Docker Image') {
                    steps {
                        sh "docker build -t fahedmaatoug/app.jar ."
                    }
                }
        stage('Docker Hub') {
                    steps {
                        withCredentials([usernamePassword(credentialsId: 'dckr_pat_A5N7MauhPQdXNR8-dTKNn8CN9_g', usernameVariable: 'fahedmaatoug', passwordVariable: 'Aouida@1975')]) {
                            sh '''
                            echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                            docker push fahedmaatoug/app.jar
                            '''
                        }
                    }
                }
        stage('Docker compose ') {
                            steps {
                                sh '''
                                        docker-compose down
                                        docker-compose up -d
                                        '''

                            }
        }

    }
    post {


            success {
                echo 'Build, Test, and SonarQube Analysis completed successfully!'
                emailext (
                    subject: "Jenkins Pipeline Success: ${currentBuild.fullDisplayName}",
                    body: """<p>The Jenkins pipeline for <b>${env.JOB_NAME}</b> completed successfully.</p>
                             <p>Build URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                    to: 'fahed.maatoug@esprit.tn'
                )
            }

            failure {
                echo 'There was an error in the pipeline stages.'
                emailext (
                    subject: "Jenkins Pipeline Failure: ${currentBuild.fullDisplayName}",
                    body: """<p>The Jenkins pipeline for <b>${env.JOB_NAME}</b> failed.</p>
                             <p>Check the logs for more details: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                    to: 'fahed.maatoug@esprit.tn'
                )
            }
        }
}
