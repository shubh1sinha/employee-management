pipeline{
    agent any
    stages{
        stage("checkout-spring-app"){
            steps{
                 sh "git clone https://github.com/shubh1sinha/employee-management.git"
            }
		}
			stage("checkout-react-app"){
			steps{
                 sh "git clone https://github.com/shubh1sinha/employee-management-react-app.git"
            }
        }
        
         stage("package-spring"){
            steps{
            sh "mvn clean package"
            }
		}
		stage("Enter-react-folder"){
			
			steps{
					    dir('employee-management-react-app') {
							sh "cd"
							sh "docker build -t shubh1sinha/employee-management-react:1.0 ."
					}
            }
		}
		stage("return-home"){
			steps{
					sh "cd"
					    dir('employee-management') {
							sh "cd"
					}
					
            }
		}
        
        stage("docke-tag-admin"){
            steps{
				sh "docker tag app-admin-microservice:1.0 shubh1sinha/app-admin-microservice:1.0"
            }
			}
		stage("docke-tag-eureka"){
            steps{
				sh "docker tag tour-eureak-server:1.0 shubh1sinha/tour-eureak-server:1.0"
            }
			}
		stage("docke-tag-employee"){
            steps{
				sh "docker tag app-employee-microservice:1.0 shubh1sinha/app-employee-microservice:1.0"
            }
			}
		stage("docke-tag-management"){
            steps{
				sh "docker tag app-management-service:1.0 shubh1sinha/app-management-service:1.0"
            }
        }

    }
 }