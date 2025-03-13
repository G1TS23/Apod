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

<pre>
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512" height="12"><!--!Font Awesome Free 6.7.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.--><path d="M64 464c-8.8 0-16-7.2-16-16L48 64c0-8.8 7.2-16 16-16l160 0 0 80c0 17.7 14.3 32 32 32l80 0 0 288c0 8.8-7.2 16-16 16L64 464zM64 0C28.7 0 0 28.7 0 64L0 448c0 35.3 28.7 64 64 64l256 0c35.3 0 64-28.7 64-64l0-293.5c0-17-6.7-33.3-18.7-45.3L274.7 18.7C262.7 6.7 246.5 0 229.5 0L64 0zm97 289c9.4-9.4 9.4-24.6 0-33.9s-24.6-9.4-33.9 0L79 303c-9.4 9.4-9.4 24.6 0 33.9l48 48c9.4 9.4 24.6 9.4 33.9 0s9.4-24.6 0-33.9l-31-31 31-31zM257 255c-9.4-9.4-24.6-9.4-33.9 0s-9.4 24.6 0 33.9l31 31-31 31c-9.4 9.4-9.4 24.6 0 33.9s24.6 9.4 33.9 0l48-48c9.4-9.4 9.4-24.6 0-33.9l-48-48z"/></svg> application.properties

5 # Database
6 spring.datasource.url=jdbc:mysql://localhost:3306/apod
7 spring.datasource.username=REPLACE_WITHI_YOUR_DB_USERNAME
8 spring.datasource.password=REPLACE_WITHI_YOUR_DB_PASSWORD
</pre>

* Your own JWT Secret

<pre>
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512" height="12"><!--!Font Awesome Free 6.7.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.--><path d="M64 464c-8.8 0-16-7.2-16-16L48 64c0-8.8 7.2-16 16-16l160 0 0 80c0 17.7 14.3 32 32 32l80 0 0 288c0 8.8-7.2 16-16 16L64 464zM64 0C28.7 0 0 28.7 0 64L0 448c0 35.3 28.7 64 64 64l256 0c35.3 0 64-28.7 64-64l0-293.5c0-17-6.7-33.3-18.7-45.3L274.7 18.7C262.7 6.7 246.5 0 229.5 0L64 0zm97 289c9.4-9.4 9.4-24.6 0-33.9s-24.6-9.4-33.9 0L79 303c-9.4 9.4-9.4 24.6 0 33.9l48 48c9.4 9.4 24.6 9.4 33.9 0s9.4-24.6 0-33.9l-31-31 31-31zM257 255c-9.4-9.4-24.6-9.4-33.9 0s-9.4 24.6 0 33.9l31 31-31 31c-9.4 9.4-9.4 24.6 0 33.9s24.6 9.4 33.9 0l48-48c9.4-9.4 9.4-24.6 0-33.9l-48-48z"/></svg> application.properties

24 # Security
25 jwt.secret=REPLACE_WITH_YOUR_OWN_JWT_SECRET
</pre>
* Your own NASA API Key from [https://api.nasa.gov](https://api.nasa.gov)

<pre>
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512" height="12"><!--!Font Awesome Free 6.7.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.--><path d="M64 464c-8.8 0-16-7.2-16-16L48 64c0-8.8 7.2-16 16-16l160 0 0 80c0 17.7 14.3 32 32 32l80 0 0 288c0 8.8-7.2 16-16 16L64 464zM64 0C28.7 0 0 28.7 0 64L0 448c0 35.3 28.7 64 64 64l256 0c35.3 0 64-28.7 64-64l0-293.5c0-17-6.7-33.3-18.7-45.3L274.7 18.7C262.7 6.7 246.5 0 229.5 0L64 0zm97 289c9.4-9.4 9.4-24.6 0-33.9s-24.6-9.4-33.9 0L79 303c-9.4 9.4-9.4 24.6 0 33.9l48 48c9.4 9.4 24.6 9.4 33.9 0s9.4-24.6 0-33.9l-31-31 31-31zM257 255c-9.4-9.4-24.6-9.4-33.9 0s-9.4 24.6 0 33.9l31 31-31 31c-9.4 9.4-9.4 24.6 0 33.9s24.6 9.4 33.9 0l48-48c9.4-9.4 9.4-24.6 0-33.9l-48-48z"/></svg> application.properties

41 #API
42 api.url=https://api.nasa.gov/planetary/apod
43 api.key=REPLACE_WITH_YOUR_OWN_API_KEY
</pre>

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

