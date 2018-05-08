#!/bin/bash
## Prepare database and user
psql postgres -f./create_db.sql
## Create tables
psql ticket_tracker -f ./create_tables.sql
## Insert data
psql ticket_tracker -f ./insert_data.sql