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
                    sh 'gradle --version'
                    sh 'gradle bootJar'
                    // Run the gradle build
                    // Test with JUnit


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