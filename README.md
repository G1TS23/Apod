# Astronomy Picture of the Day (APOD)

This API is a Gradle project that extends [Nasa's APOD API](https://github.com/nasa/apod-api).

## Table of contents

1. [Getting Started](#getting_started)
2. [Docs](#docs)
3. [Author](#author)
   &nbsp;

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
* Your own JWT Secret
* Your own NASA API Key from [https://api.nasa.gov](https://api.nasa.gov)


5. Run your mysql server and create a schema named `apod`


6. Run the application
   &nbsp;

### Docs <a name="docs"></a>

#### Roles

There's three different roles :

* ADMIN has access to a simple CRUD for stored Apod
* USER can call two endpoints that bounce to Nasa's API :
    * **/api** : the picture of day
    * **/api/range?from=2025-02-01&to=2025-02-03** : pictures or videos within a time range)
* SCRAPPER has almost the same endpoints but that stores object in database :
    * **/api/scrap/today**
    * **/api/scrap/range?from=2025-02-11&to=2025-02-14**

#### API collection

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
&nbsp;
### Author <a name="author"></a>

* Olivier Falahi (based on code by [Simon Bédard](https://github.com/JavaKhanStudio/Spring_Exemple_Security))

