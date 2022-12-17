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

        // stage("docker-build app-admin-microservice"){
        //     steps{
		// 	    dir('app-admin-microservice') {
		// 		    sh "cd"
        //             sh "pwd"
        //             sh "sudo docker build -t shubh1sinha/app-admin-microservice:5.0 ."
        //         }
		// 	    dir('employee-pipeline') {
		// 		    sh "cd"
        //             sh "pwd"
        //         }
        //     }
        // }

        // stage("docker-build app-employee-microservice"){
        //     steps{
		// 	    dir('app-employee-microservice') {
		// 		    sh "cd"
        //             sh "pwd"
        //             sh "sudo docker build -t shubh1sinha/app-employee-microservice:5.0 ."
        //         }
		// 	    dir('employee-pipeline') {
		// 		    sh "cd"
        //             sh "pwd"
        //         }
        //     }
        // }

        // stage("docker-build app-management-service"){
        //     steps{
		// 	    dir('app-management-service') {
		// 		    sh "cd"
        //             sh "pwd"
        //             sh "sudo docker build -t shubh1sinha/app-management-service:5.0 ."
        //         }
		// 	    dir('employee-pipeline') {
		// 		    sh "cd"
        //             sh "pwd"
        //         }
        //     }
        // }

        stage("docker-push all images"){
            steps{
                sh "sudo docker push shubh1sinha/app-admin-microservice:1.0"
                sh "sudo docker push shubh1sinha/app-employee-microservice:1.0"
                sh "sudo docker push shubh1sinha/app-management-service:1.0"
            }
        }
		
        stage("helm-chart"){
            steps{
                        sh 'pwd'
                        sh 'cp -R helm/* .'
						sh 'ls -ltr'
                        sh 'pwd'
                        sh '/usr/local/bin/helm upgrade --install admin-app admin'
                        sh '/usr/local/bin/helm upgrade --install employee-app employee'
                        sh '/usr/local/bin/helm upgrade --install eureka-app eureka'
                        sh '/usr/local/bin/helm upgrade --install management-app management'
            }
        }

        // stage("helm-mongo-chart"){
        //     steps{
        //         sh "helm install mongodb stable/mongodb "
        //     }
        // }

        stage("Kubernetes-check-pods"){
            steps{
                sh 'kubectl get pods'
                sh 'kubectl get svc'
            }
        }

    }

}