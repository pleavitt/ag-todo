# AG Todo REST API

This application is a REST API for creating, updating and retrieving single todo tasks. (/todo)
There is also an endpoint for validating a query string to check if it has matching brackets (tasks/validateBrackets)

Tech:
* Spring Boot 2

## Purpose of this application

This app was built for the primary purpose as a for a prospective employer.

## Demo
[Here](https://thawing-garden-78853.herokuapp.com) is a link to a demo of the API

## Current Issues/Outstanding Tasks

- Using the /tasks/validateBrackets endpoint, the server sends an exception when square brackets are sent in the query string.
Will try downgrading TomCat to a previous version as this appears to be a security fix in recent versions.
