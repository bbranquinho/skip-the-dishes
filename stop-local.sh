#!/bin/bash

docker-compose -f docker-compose-local.yml stop

docker-compose -f docker-compose-local.yml rm -f
