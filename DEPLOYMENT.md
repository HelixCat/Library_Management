# Library Management System - Deployment Guide

## 🚀 Quick Start

### Prerequisites
- Docker 20.10+
- Docker Compose 2.0+
- 4GB+ RAM available
- Ports available: 8080, 3306, 27017, 6379

### Local Development with Docker Compose

```bash
# Build and start all services
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop all services
docker-compose down

# Stop and remove volumes (clean slate)
docker-compose down -v
```

### Access Points
- **Application**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Health Check**: http://localhost:8080/actuator/health
- **MySQL**: localhost:3306
- **MongoDB**: localhost:27017
- **Redis**: localhost:6379

---

## 🐳 Docker Commands

### Build the Application Image

```bash
# Build the Docker image
docker build -t library-management:latest .

# Build with specific tag
docker build -t library-management:1.0.0 .

# Build without cache
docker build --no-cache -t library-management:latest .
```

### Run Standalone Container

```bash
# Run the application (requires external MySQL, MongoDB, Redis)
docker run -d \
  --name library-app \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/library_db \
  -e SPRING_DATASOURCE_USERNAME=library_user \
  -e SPRING_DATASOURCE_PASSWORD=library_pass \
  -e SPRING_DATA_MONGODB_URI=mongodb://admin:adminpass@host.docker.internal:27017/library_mongo \
  -e SPRING_DATA_REDIS_HOST=host.docker.internal \
  -e SPRING_DATA_REDIS_PASSWORD=redispass \
  library-management:latest

# View logs
docker logs -f library-app

# Stop container
docker stop library-app

# Remove container
docker rm library-app
```

---

## 🏗️ Production Deployment

### 1. Environment Variables

Create a `.env` file for production:

```env
# Database Credentials
MYSQL_ROOT_PASSWORD=<strong-root-password>
MYSQL_DATABASE=library_db
MYSQL_USER=library_user
MYSQL_PASSWORD=<strong-mysql-password>

# MongoDB Credentials
MONGO_ROOT_USERNAME=admin
MONGO_ROOT_PASSWORD=<strong-mongo-password>

# Redis Password
REDIS_PASSWORD=<strong-redis-password>

# Application Settings
SPRING_PROFILES_ACTIVE=prod
JWT_SECRET=<your-jwt-secret-key>
```

### 2. Production Docker Compose

Create `docker-compose.prod.yml`:

```yaml
version: '3.8'

services:
  app:
    image: library-management:latest
    restart: always
    ports:
      - "8080:8080"
    env_file:
      - .env
    deploy:
      resources:
        limits:
          cpus: '2'
          memory: 2G
        reservations:
          cpus: '1'
          memory: 1G
```

### 3. Deploy to Server

```bash
# On your server
git clone <repository-url>
cd Library_Management

# Create .env file with production credentials
nano .env

# Build and deploy
docker-compose -f docker-compose.prod.yml up -d

# Monitor logs
docker-compose -f docker-compose.prod.yml logs -f
```

---

## 🔧 Advanced Configuration

### Custom JVM Options

Modify `JAVA_OPTS` in docker-compose.yml:

```yaml
environment:
  JAVA_OPTS: >-
    -XX:+UseContainerSupport
    -XX:MaxRAMPercentage=75.0
    -Xlog:gc*:file=/app/logs/gc.log
    -XX:+HeapDumpOnOutOfMemoryError
    -XX:HeapDumpPath=/app/logs/heapdump.hprof
```

### Enable Spring Boot Actuator Endpoints

Add to `application.properties`:

```properties
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.health.show-details=always
```

### SSL/TLS Configuration

```yaml
environment:
  SERVER_SSL_ENABLED: true
  SERVER_SSL_KEY_STORE: /app/keystore.p12
  SERVER_SSL_KEY_STORE_PASSWORD: changeit
  SERVER_SSL_KEY_STORE_TYPE: PKCS12
```

---

## 📊 Monitoring & Maintenance

### Health Checks

```bash
# Application health
curl http://localhost:8080/actuator/health

# Database connectivity
docker exec library-mysql mysqladmin ping -h localhost -u root -p

# MongoDB status
docker exec library-mongodb mongosh --eval "db.adminCommand('ping')"

# Redis status
docker exec library-redis redis-cli ping
```

### Backup Databases

```bash
# MySQL backup
docker exec library-mysql mysqldump -u root -p library_db > backup_mysql.sql

# MongoDB backup
docker exec library-mongodb mongodump --uri="mongodb://admin:adminpass@localhost:27017" --out=/backup

# Restore MySQL
docker exec -i library-mysql mysql -u root -p library_db < backup_mysql.sql
```

### View Resource Usage

```bash
# Container stats
docker stats

# Specific container
docker stats library-app
```

---

## 🐛 Troubleshooting

### Application Won't Start

```bash
# Check logs
docker-compose logs app

# Check if databases are healthy
docker-compose ps

# Restart services
docker-compose restart app
```

### Connection Issues

```bash
# Test MySQL connection
docker exec -it library-mysql mysql -u library_user -p library_db

# Test MongoDB connection
docker exec -it library-mongodb mongosh -u admin -p adminpass

# Test Redis connection
docker exec -it library-redis redis-cli -a redispass ping
```

### Clean Restart

```bash
# Stop everything
docker-compose down -v

# Remove images
docker rmi library-management:latest

# Rebuild and start
docker-compose up --build -d
```

---

## 🔐 Security Best Practices

1. **Never commit `.env` files** - Add to `.gitignore`
2. **Use strong passwords** in production
3. **Enable SSL/TLS** for production deployments
4. **Run as non-root user** (already configured in Dockerfile)
5. **Limit container resources** (CPU/Memory)
6. **Regular security updates** - Update base images
7. **Use secrets management** - Docker Swarm secrets or Kubernetes secrets

---

## 📝 Notes

- The Dockerfile uses multi-stage builds for optimal image size
- Alpine Linux base images for minimal footprint
- Health checks configured for all services
- Automatic restart policies for high availability
- Volume persistence for databases
- Network isolation with custom bridge network

## 🆘 Support

For issues or questions, check the application logs:
```bash
docker-compose logs -f app
```
