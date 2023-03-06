{
"apiVersion": "apps/v1",
"kind": "Deployment",
"metadata": {
"name": "hello-world",
"labels": "hello-world-lbl"
},
"specs": {
"replicas": "2",
"selector": {
"matchLabels": "match-label"
},
"template": {
"metadata": {
"labels": "labels"
}
},
"spec": {
"serviceAccountName": "service-name",
"containers": {
"name": "name",
"image": "image-path",
"imagePullPolicy": "policy",
"ports": {
"name": "http",
"containerPort": "80",
"protocol": "TCP"
},
"livenessProbe": {
"httpGet": {
"path": "/",
"port": "http"
}
},
"readinessProbe": {
"httpGet": {
"path": "/",
"port": "http"
}
}
}
}
}
}



### Installing

* Run the following command in the project root for creating Docker build

```
docker build --tag=helm-generator-service-0.0.1:latest .
```
<br/>

To set container name
```
docker run -p8080:8080 helm-generator-service-0.0.1:latest
```
<br/>

Few Docker commands,
$> docker inspect helm-generator-service-0.0.1 <br>
$> docker stop helm-generator-service-0.0.1 <br>
$> docker rm helm-generator-service-0.0.1 <br>
