pipeline{
    agent any
    stages{
        stage("checkout"){
            steps{
                 "https://github.com/shubh1sinha/employee-management.git"
            }
        }
        
         stage("package"){
            steps{
            bat "mvn clean package"
            }
        }
        
        stage("docke-tag"){
            steps{
            bat "docker tag app-admin-microservice:1.0 shubh1sinha/app-admin-microservice:1.0"
            }
            steps{
            bat "docker tag tour-eureak-server:1.0 shubh1sinha/tour-eureak-server:1.0"
            }
            steps{
            bat "docker tag app-employee-microservice:1.0 shubh1sinha/app-employee-microservice:1.0"
            }
            steps{
            bat "docker tag app-management-service:1.0 shubh1sinha/app-management-service:1.0"
            }
        }
        
        stage("dockerize"){
            steps{
            bat "docker-compose up"
            }
        }
    }
 }