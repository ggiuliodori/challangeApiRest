build:
	mvn clean install -DskipTests

local-develop-run:
	docker-compose -f docker-compose/docker-compose.yml up -d
	mvn spring-boot:run

test-run:
	mvn test