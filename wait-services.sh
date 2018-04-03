#!/usr/bin/env bash
set -e
cd /home/application
./wait-for-it.sh "postgres:5432" -- echo "postgres is up"
./wait-for-it.sh "postgres-security:5432" -- echo "postgres-security is up"
./wait-for-it.sh "event-store:2113" -- echo "event-store 2113 is up"
./wait-for-it.sh "event-store:1113" -- echo "event-store 1113 is up"
