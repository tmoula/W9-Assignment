# Week 9 – Microservice Configuration & Logging Assignment

This project implements a **Spring Boot microservice** deployed on **Kubernetes**.  
It demonstrates the use of **ConfigMaps**, **logging to System.out**, and a **Web UI** that displays environment configurations and Fibonacci sequence results.

---

## 🧱 1. Features Implemented

- `/config` route returns all environment variables visible to the container, including the `DATA_MICROSERVICE` key from the ConfigMap.
- `/fib?length=N` route returns a JSON array of the first N Fibonacci numbers.
- Both routes print their data to the console (`System.out`) for logging.
- ConfigMap injected into the pod using environment variables.
- Web UI (`index.html`) dynamically displays:
  - Environment configuration (`/config`)
  - Fibonacci results (`/fib?length=10`)
- Three replicas of the microservice running under one Kubernetes Deployment.
- Application exposed via NodePort service for external access.

---

## ⚙️ 2. Technologies Used

- **Java 21 / Spring Boot**
- **Gradle**
- **Docker**
- **Kubernetes (minikube / Docker Desktop)**
- **ConfigMap + Deployment + Service (YAML manifests)**

---

## 🏗️ 3. Build the Container Image

From the project root:

```
./gradlew clean bootJar
docker build -t tahamoula/my-spring-app:v1 .
docker push tahamoula/my-spring-app:v1
````

## 4. Deploy to Kubernetes

```
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

```

Verify deployment and ConfigMap:

```
kubectl get services,deployments,pods,configmaps
```


## 5. Access the Application
Once deployed, access it via NodePort:

```
curl http://localhost:32123/config
curl "http://localhost:32123/fib?length=10"
```
or open on the browser

```
http://localhost:32123/
```
