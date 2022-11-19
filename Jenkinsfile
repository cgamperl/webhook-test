pipeline {

    agent any

        stages {
            stage('Build Feature') {

                when {
                    branch 'feature/*'
                }

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
                    sh 'git --version'
                    sh 'git switch -c integration'
                    sh 'git merge --no-ff --no-edit feature/feature-1'
                    sh 'git push origin integration'
                }
            }
            stage('Test Feature') {
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