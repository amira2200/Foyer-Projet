pipeline {
    agent any  // Define the agent (where the pipeline will run)
    
    environment {
        EMAIL_RECIPIENT = 'your-email@example.com'  // Set the email recipient here
    }
    
    stages {
        // Stage 1: Checkout the code from Git
        stage('Checkout Code') {
            steps {
                git 'https://github.com/amira2200/Foyer-Projet.git'
            }
        }
        
        // Stage 2: Build/Compile the application (example)
        stage('Build') {
            steps {
                echo 'Building application...'
                // Add your build steps here (e.g., Maven, Gradle, etc.)
            }
        }
        
        // Stage 3: Test the application (example)
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Add your test steps here (e.g., JUnit, Selenium, etc.)
            }
        }
        
        // Stage 4: Deploy the application (example)
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Add your deployment steps here (e.g., to a server or container)
            }
        }
        
        // Stage 5: Send email notification (final stage)
        stage('Send Email Notification') {
            steps {
                script {
                    // Send email notification after pipeline completes
                    emailext (
                        subject: "Build Complete: ${currentBuild.fullDisplayName}",
                        body: "The Jenkins build has completed successfully. Check the details at ${env.BUILD_URL}",
                        to: "${EMAIL_RECIPIENT}"
                    )
                }
            }
        }
    }
    
    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
        always {
            echo 'Cleaning up...'
            // Add any clean-up tasks here if needed
        }
    }
}
