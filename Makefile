SHELL := $(shell which bash)
.DEFAULT_GOAL := help

help: ## This help.
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

GRDL_EXEC=./gradlew

test:
	$(GRDL_EXEC) clean test
