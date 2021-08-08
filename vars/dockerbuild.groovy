def call(String dockerAccountName, String repoName, String dockerHubCredentialId, String dockerHubPasswordVariableName) {
  sh "docker build -t ${dockerAcccountName}/${repoName}:${env.BUILD_ID} ."
  sh "docker tag ${dockerAcccountName}/${repoName}:${env.BUILD_ID} ${dockerAcccountName}/${repoName}:latest"
  withCredentials([string(credentialsId: '${dockerHubCredentialId}', variable: '${dockerHubPasswordVariableName}')]) {
    sh "docker login -u ${dockerAcccountName} -p ${DOCKER_HUB_PASSWORD} docker.io"
  }
  sh "docker push ${dockerAcccountName}/${repoName}:${env.BUILD_ID}"
  sh "docker push ${dockerAcccountName}/${repoName}:latest"
  sh 'docker logout docker.io'
  sh "docker image rm  ${dockerAcccountName}/${repoName}:${env.BUILD_ID}"
  sh "docker image rm  ${dockerAcccountName}/${repoName}:latest"
}
