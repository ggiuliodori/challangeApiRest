project=indigital
app_name=challange
app_version=1.0
TOKEN=$(shell date +'%y-%m-%d_%H-%M-%S')
#build_opts=--no-cache

build:
	mvn clean install -DskipTests

build-image:
	mvn clean install -DskipTests
	@docker build ${build_opts} --tag $(project)/$(app_name):$(app_version) --tag $(project)/$(app_name):latest .

deploy:
	gcloud app deploy

run-local:
	make build-image
	docker-compose -f docker-compose/docker-compose.yml up -d

test-run:
	mvn test

logs:
	docker-compose -f docker-compose/docker-compose.yml logs -f service-challange

restart:
	docker-compose -f docker-compose/docker-compose.yml stop service-challange
	docker-compose -f docker-compose/docker-compose.yml rm -f service-challange
	make run-local

cloud-logs:
	gcloud app logs tail -s default