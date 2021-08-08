def call(Map config) {
	sh "docker build -t config.dockerAccountName/myjavaapp:${env.BUILD_ID} ."
	sh "docker tag rathinamtrainers/myjavaapp:${env.BUILD_ID} rathinamtrainers/myjavaapp:latest"
	withCredentials([string(credentialsId: 'DOCKER_HUB_CREDENTIALS', variable: 'DOCKER_HUB_PASSWORD')]) {
	    sh "docker login -u rathinamtrainers -p ${DOCKER_HUB_PASSWORD} docker.io"
	}
	sh "docker push rathinamtrainers/myjavaapp:${env.BUILD_ID}"
	sh "docker push rathinamtrainers/myjavaapp:latest"
	sh 'docker logout docker.io'
	sh "docker image rm  rathinamtrainers/myjavaapp:${env.BUILD_ID}"
	sh "docker image rm  rathinamtrainers/myjavaapp:latest"
}

