build:
	mvn clean install -DskipTests
	docker build -t challange:latest .

run:
	make build
	docker-compose -f docker-compose/docker-compose.yml up -d

local-develop-run:
	docker-compose -f docker-compose/docker-compose.yml up -d
	mvn spring-boot:run

test-run:
	mvn test