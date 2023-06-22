def buildImage(){
    sh "docker build . -f CI_CD/Dockerfile -t techwithnc/betterhrapp:$APP_VERSION"
}
def pushImage(){
    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                        sh "docker push techwithnc/betterhrapp:$APP_VERSION"
                    }
}
def deployImage(){
    def shellcmd = "bash scripts.sh ${APP_VERSION}"
    def svr = "techwithnc@192.168.20.234"
    sshagent(['ubt07']){
        sh "scp ./CI_CD/scripts.sh ${svr}:/home/techwithnc "
        sh "scp ./CI_CD/docker-compose.yml ${svr}:/home/techwithnc "
        sh "ssh -o StrictHostKeyChecking=no ${svr} ${shellcmd}"
    }
}
return this