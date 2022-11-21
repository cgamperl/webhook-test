pipeline {

    agent {
        docker {
            image 'gradle:7.5-jdk17'
            // args '-v /root/.m2:/root/.m2'
        }
    }

    environment {
        ORG_GRADLE_PROJECT_snapshotRepository = 'http://nexus:8081/repository/maven-snapshots/'
        ORG_GRADLE_PROJECT_releaseRepository = 'http://nexus:8081/repository/maven-releases/'
    }

    stages {
        stage('Build Feature') {

            when {
                branch 'feature/*'
            }

            steps {
                echo 'Building...'
                sh 'ls -la'
                sh 'gradle --version'
                sh 'gradle clean build -x test'
                sh 'ls -la build/libs'
                // Run the gradle build
                // Test with JUnit
//                 sh 'gradle test'
//                 sh 'ls -la build/reports'
//                 sh 'ls -la build/test-results'
//                 junit 'build/test-results/**/*.xml'
            }
        }


        stage('Test Feature') {
            when {
                branch 'feature/*'
            }

            steps {
                echo 'Testing...'
                sh 'gradle test'
                sh 'ls -la build/reports'
                sh 'ls -la build/test-results/test'
            }

            post {
                always {
                    junit 'build/test-results/**/*.xml'
                }

                success {
//                     publishHTML target: [
//                             allowMissing         : true,
//                             alwaysLinkToLastBuild: false,
//                             keepAll              : true,
//                             reportDir            : 'build/reports/tests/test',
//                             reportFiles          : 'index.html',
//                             reportName           : 'Test Report'
//                     ]
                    publishHTML([
                        allowMissing: true,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'build/reports/tests/test',
                        reportFiles: 'index.html',
                        reportName: 'HTML Report',
                        reportTitles: ''
                    ])
                }
            }
        }


        stage('Integrate Feature') {
            when {
                branch 'feature/*'
            }

            steps {
                echo 'Integrating Feature...'
                sh 'git --version'
                sh 'git branch -a'
                sh 'git checkout integration'
                sh 'git merge --no-ff --no-edit remotes/origin/feature/feature-1'
                withCredentials([gitUsernamePassword(credentialsId: 'cgamperl_github_pat', gitToolName: 'Default')]) {
                    sh 'git push origin integration'
                }
            }
        }

        stage('Build Integration') {
            when {
                branch 'integration'
            }

            steps {
                sh 'gradle clean build -x test'
            }
        }


        stage('Test Integration') {
            when {
                branch 'integration'
            }

            steps {
                echo 'Testing Integration...'

                sh 'gradle test'
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
                sh 'set'
                sh 'printenv'
//                 sh 'gradle uploadArchives'
                nexusArtifactUploader artifacts: [
                    [
                    artifactId: 'at.tectrain.cicd',
                    classifier: '',
                    file: 'build/libs/app-0.0.1-SNAPSHOT.jar',
                    type: 'jar']
                ],
                credentialsId: 'nexus_credentials',
                groupId: '',
                nexusUrl: 'nexus:8081/repository/maven-snapshots/',
                nexusVersion: 'nexus3',
                protocol: 'http',
                repository: 'maven-snapshots',
                version: '0.0.1-SNAPSHOT'
            }
        }


        stage('Deploy Integration') {
            when {
                branch 'integration'
            }

            steps {
                echo 'Deploying...'
            }
        }
    }
}