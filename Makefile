project=indigital
app_name=challange
app_version=1.0
TOKEN=$(shell date +'%y-%m-%d_%H-%M-%S')
#build_opts=--no-cache

build:
	@docker build ${build_opts} --tag $(project)/$(app_name):$(app_version) --tag $(project)/$(app_name):latest .

deploy:
	gcloud app deploy app.yaml -v v1
run:
	make build
	docker-compose -f docker-compose/docker-compose.yml up -d

local-develop-run:
	docker-compose -f docker-compose/docker-compose.yml up -d
	mvn spring-boot:run

test-run:
	mvn test