pipeline {


    environment {
        ORG_GRADLE_PROJECT_snapshotRepository = 'http://nexus:8081/repository/maven-snapshots/'
        ORG_GRADLE_PROJECT_releaseRepository = 'http://nexus:8081/repository/maven-releases/'
    }

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
                    sh 'gradle clean build -x test'
                    sh 'ls -la build/libs'
                    // Run the gradle build
                    // Test with JUnit
                    sh 'gradle test'
                    sh 'ls -la build/reports'
                    sh 'ls -la build/test-results'
                    junit 'build/test-results/**/*.xml'
                    sh 'git --version'
                    sh 'git branch -a'
                    sh 'git checkout integration'
                    sh 'git merge --no-ff --no-edit remotes/origin/feature/feature-1'
                    withCredentials([gitUsernamePassword(credentialsId: 'cgamperl_github_pat', gitToolName: 'Default')]) {
                        sh 'git push origin integration'
                    }


                }
            }
            stage('Test Feature') {
                steps {
                    echo 'Testing...'
                }
            }

            stage('Build Integration') {
                when {
                    branch 'integration'
                }

                steps {
                    sh './gradlew clean build -x test'
                }
            }

            stage('Test Integration') {
                steps {
                    echo 'Testing Integration...'

                    sh './gradlew test'
                }

                post {
                    always {
                        junit 'build/test-results/**/*.xml'
                    }

                    success {
                        publishHTML target: [
                                allowMissing         : true,
                                alwaysLinkToLastBuild: false,
                                keepAll              : true,
                                reportDir            : 'build/reports/tests/test',
                                reportFiles          : 'index.html',
                                reportName           : 'Test Report'
                        ]
                    }
                }

            }

            stage('Publish Integration') {
                when {
                    branch 'integration'
                }

                steps {
                    sh './gradlew uploadArchives'
                }
            }


            stage('Deploy') {
                steps {
                    echo 'Deploying...'
                }
            }
        }

}