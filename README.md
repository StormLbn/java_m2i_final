# Pop-corn Mania

This is a training project to learn **Java**, **Spring** and **Angular**. It was done in pair with [guillaume806](https://github.com/guillaume806), as a final project during a training with M2i Formation.

This project is a movies and series community website.

## Features

All users :
- Browse all media, or filtered by type (movies or series) or by category (action, thriller, etc.)
- Search media by title
- View information about a media (release date, synopsis, actors...) on its details page
- Register and login to the application

Registered users :
- Change personal information like birthdate, e-mail, password and favorite media categories (Work in progress)
- View, on the front page and profile, media suggestions based on favorite categories
- Comment and rate media ; the average rate of media is then calculated and displayed on the media

## Technologies

- Java 20
- Spring : JPA, Security (JWT)
- Angular 15
- PostgreSQL

## How to run the app

This project isn't hosted online ; if you want to see what it's like, you will have to install and run the app locally.

### Requirements
- [JDK 20](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Angular CLI](https://github.com/angular/angular-cli) version 15.1.2.
- [PostgreSQL](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
- (**optional**) [TMDB](https://developer.themoviedb.org/docs/getting-started) and [betaseries](https://developers.betaseries.com/docs/members-auth) API keys

### Downloading the project

Start by cloning the repository :
```bash
git clone https://github.com/StormLbn/java_m2i_final.git
```

### Database connection
Use a script editor or terminal connect to PostgreSQL, and create a database with name `popcornmania`.

### Backend configuration and build

In the `filrouge_back` folder :
- Create a file in `filrouge_back\src\main\resources` nammed `application-secrets.properties`, and fill it with your personal data as follow (leave placeholders if you don't have API keys) :

```
api.tmdb.token=xxxxxxxxxxxxxxxxxxxx.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.xxxxxxxxxxxxxxxxx

api.betaseries.key=xxxxxxxxxxxx

spring.datasource.username=xxxxxxxxxxxx
spring.datasource.password=xxxxxxxxxxxx

jwt.secretKey=zermk4jhng6e8zwfhfy7rer8rtj41maperh58gqza598erykt7u41nb61qz87uopueryt45iuk1iym
```

- Build the project :
```bash
mvn clean install
```

*Note : if you used API keys, DB tables are filled with data during the build so it could take a few minutes.*

### Fill database

Database tables are automatically created during the application build.

**If you used TMDB and betaseries API keys**, the tables were also automatically filled with data from the APIs so you have nothing else to do.

**If not**, you have to run the script `media-data.sql` with a script editor or terminal to fill the database.

### Run the app

With a terminal open in the `filrouge_back` folder :
```bash
java -jar target/filrouge_back-0.0.1-SNAPSHOT.jar
```

The server runs on http://localhost:8080 and can be used without frontend for API calls ; routes and examples are listed in the files of the `filrouge_back\api-requests-examples` folder.

With a terminal open in the `filrouge_front` folder :
```bash
npm install
ng serve
```

The Angular app now runs on http://localhost:4200/.

