pipeline {

    agent any

        stages {
            stage('Build') {

                agent {
                    docker {
                        image 'gradle:7.5-jdk17'
    //                     args '-v /root/.m2:/root/.m2'
                    }
                }

                steps {
                    echo 'Building...'
                    sh 'ls -la'
                    sh 'gradle --version'
                    sh 'gradle bootJar'
                    sh 'ls -la build/libs'
                    // Run the gradle build
                    // Test with JUnit
                    sh 'gradle test'
                    sh 'ls -la build/reports'
                    sh 'ls -la build/test-results'
                    junit 'build/test-results/test/*.xml'



                }
            }
            stage('Test') {
                steps {
                    echo 'Testing...'
                }
            }
            stage('Deploy') {
                steps {
                    echo 'Deploying...'
                }
            }
        }

}