# Marvels API Service

![Marvels](https://www.brandsoftheworld.com/sites/default/files/styles/logo-thumbnail/public/0017/0599/brand.gif?itok=RyK-r4A4)

Marvels API Service is a secure backend RESTful API service for Avengers and Xmen Mission center.
Check all endpoints contract at Swagger-ui.

# Features

  - Secured with Sessions. Each API endpoint is secured with checks for open session.
  - Single entry point (API) to open a session with password.
  - Passwords stored as hash maps with random salt to to beat rainbow table attack
  - All submitted HTML files sanitized first then stored and  served.


Also:
  - Single sign on and redirection for members of both Xmen and Avenger.
  - Squad Todo integration for members of both Xmen and Avenger.
  - Personal Todo safe with you on Xmen or Avenger mission Control.
  - DC's Batman as the gatekeeper on API for snoopers ;) :P !

### Infrastructure and Design

                                              |---------|
            In memory database H2 <---------->|         |  Marvels API service server
                                              |         |
                                 __________   |---------|   __________
                              /                                          \
                            /  Login, register, bio and populate, create  \
                          /                 todo API calls                 \
                        /                                                   \
                    |---------|                                         |---------|
                    |         |  Avenger Misson                         |         |  Xmen Misson
                    |---------| Control Frontend                        |---------| Control Frontend

The Marvels API service provides API endpoints for the frontends to hit for all tasks. The Xmen and Avenger Mission Control Frontend server serves the html files.

### Setup and Installation

Clone the Marvels API service (This).
```sh
$ git clone https://github.com/DonHarishAxe/MarvelAPIService
```
and Clone Xmen and Avenger Mission Control Server.
```sh
$ git clone https://github.com/DonHarishAxe/Marvel
```
Go to Marvles API service folder and download dependencies
```sh
$ cd marlvelsAPIService
$ mnv clean install
```
Import the project in any IDE and Run AppDemo.java

To check the endpoints documentation go to
```sh
http://localhost:9000/swagger-ui.html
```
Got to the Mission Control Frontend folder
```sh
$ cd ../Marvel
````
Start up the Frontend services for Avengers and Xmen
```sh
$ cd Avenger
$ python -m SimpleHTTPServer 8000
$ cd ../Xmen
$ python -m SimpleHTTPServer 9005
```
To view Avenger Mission control go to
```sh
http://0.0.0.0:8000/
```
To view Xmen Mission control go to
```sh
http://0.0.0.0:9005/
```

### Tech

 - Java with SpringBoot and Maven
 - Bcrypt
 - H2 in memory database
 - Swagger UI
 - Jetty server
 - html, css (BootStrap), javascript/jquery
 - python SimpleHTTPServer


# ---------------------------------------------------------------------
# Marvels Assemble !!
### Calling Team - Winter Soldier
# ---------------------------------------------------------------------
