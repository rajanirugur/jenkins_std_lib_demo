def call(Map config) {
	sh "docker build -t ${config.dockerAccountName}/${config.dockerImageName}:${env.BUILD_ID} ."
	sh "docker tag ${config.dockerAccountName}/myjavaapp:${env.BUILD_ID} ${config.dockerAccountName}/myjavaapp:latest"
	withCredentials([string(credentialsId: 'DOCKER_HUB_CREDENTIALS', variable: 'DOCKER_HUB_PASSWORD')]) {
	    sh "docker login -u ${config.dockerAccountName} -p ${DOCKER_HUB_PASSWORD} docker.io"
	}
	sh "docker push ${config.dockerAccountName}/myjavaapp:${env.BUILD_ID}"
	sh "docker push ${config.dockerAccountName}/myjavaapp:latest"
	sh 'docker logout docker.io'
	sh "docker image rm  ${config.dockerAccountName}/myjavaapp:${env.BUILD_ID}"
	sh "docker image rm  ${config.dockerAccountName}/myjavaapp:latest"
}

