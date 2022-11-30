pipeline{
    agent any
    stages{
        stage("checkout-1"){
            steps{
                 bat "git clone https://github.com/shubh1sinha/employee-management.git"
            }
		}
			stage("checkout-2"){
			steps{
                 bat "git clone https://github.com/shubh1sinha/employee-management-react-app.git"
            }
        }
        
         stage("package-1"){
            steps{
            bat "mvn clean package"
            }
		}
		stage("Enter-Location"){
			
			steps{
					    dir('employee-management-react-app') {
							bat "cd"
					}
					bat "docker build -t shubh1sinha/employee-management-react:1.0 ."
            }
		}
		stage("Home-location"){
			steps{
					bat "cd"
					    dir('employee-management') {
							bat "cd"
					}
					
            }
		}
        
        stage("docke-tag-1"){
            steps{
				bat "docker tag app-admin-microservice:1.0 shubh1sinha/app-admin-microservice:1.0"
            }
			}
		stage("docke-tag-2"){
            steps{
				bat "docker tag tour-eureak-server:1.0 shubh1sinha/tour-eureak-server:1.0"
            }
			}
		stage("docke-tag-3"){
            steps{
				bat "docker tag app-employee-microservice:1.0 shubh1sinha/app-employee-microservice:1.0"
            }
			}
		stage("docke-tag-4"){
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