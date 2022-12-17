pipeline{

    agent any
    
    stages{
        stage("Checkout-Spring-App"){
            steps{
                 sh "git clone https://github.com/shubh1sinha/employee-management.git"
            }
		}
        stage("Package-Application"){
            steps{
                sh "sudo  /home/shusinha5/apache-maven-3.8.6/bin/mvn --v"
                sh "sudo /home/shusinha5/apache-maven-3.8.6/bin/mvn clean package"
                sh "pwd"
            }
        }
        stage("docker-build app admin-microservice"){
            steps{
                sh "cd app-admin-microservice"
                sh "pwd"
                sh "sudo docker build -t shubh1sinha/app-admin-microservice:2.0 ."
                sh "cd"
                sh "pwd"
            }
        }

        stage("docker-build app-employee-microservice"){
            steps{
                sh "cd app-admin-microservice"
                sh "pwd"
                sh "sudo docker build -t shubh1sinha/app-employee-microservice:2.0 ."
                sh "cd"
                sh "pwd"
            }
        }

		
        


    }
 }