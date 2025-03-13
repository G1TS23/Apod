# Astronomy Picture of the Day (APOD)

This API is a Gradle project using [Nasa's APOD API](https://github.com/nasa/apod-api).

## Table of contents

1. [Getting Started](#getting_started)
2. [Docs](#docs)
   * [Roles](#roles)
   * [API collection](#collection)
3. [Author](#author)

### Getting started <a name="getting_started"></a>

1. Clone the project

```bash
git clone https://github.com/G1TS23/Apod.git
```

2. `cd` into the new directory

```bash
cd apod
```

3. Rename `application.properties.example` (in /src/main/resources/) to `application.properties`

4. Replace information in `application.properties` such as :

* Your Database information
```
5 # Database
6 spring.datasource.url=jdbc:mysql://localhost:3306/apod
7 spring.datasource.username=REPLACE_WITHI_YOUR_DB_USERNAME
8 spring.datasource.password=REPLACE_WITHI_YOUR_DB_PASSWORD
```
* Your own JWT Secret
```
24 # Security
25 jwt.secret=REPLACE_WITH_YOUR_OWN_JWT_SECRET
```
* Your own NASA API Key from [https://api.nasa.gov](https://api.nasa.gov)
```
41 #API
42 api.url=https://api.nasa.gov/planetary/apod
43 api.key=REPLACE_WITH_YOUR_OWN_API_KEY
```

5. Run your mysql server and create a schema named `apod`

6. Run the application

### Docs <a name="docs"></a>

#### Roles <a name="roles"></a>

There's three different roles :

* ADMIN has access to a simple CRUD for stored Apod
* USER can call two endpoints that bounce to Nasa's API :
    * **/api** : the picture of day
    * **/api/range?from=2025-02-01&to=2025-02-03** : pictures or videos within a time range)
* SCRAPPER has almost the same endpoints but that stores object in database :
    * **/api/scrap/today**
    * **/api/scrap/range?from=2025-02-11&to=2025-02-14**

#### API collection <a name="collection"></a>

In root directory there's a collection ``Apod_Bruno_Collection.json`` you can import in [Bruno](https://www.usebruno.com)
or [Postman](https://www.postman.com) to test the API. In order to store the userToken after login you to have to add
this post-request script :

* Bruno :

```javascript
var jsonData = res.getBody();
bru.setEnvVar("userToken", jsonData.token);
```

* Postman :

```javascript
var jsonData = pm.response.json();
pm.environment.set("userToken", jsonData.token);
```

###### Folders

* API : only for user role, bounce request from NASA's API
* CRUD : only for admin role, manipulate database
* SCRAP : only for scrapper role, retrieve data from NASA's API and store it in database
* USER : log in, register, view credentials and log out

### Author <a name="author"></a>

* Olivier Falahi (based on code by [Simon Bédard](https://github.com/JavaKhanStudio/Spring_Exemple_Security))

