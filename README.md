![](https://raw.githubusercontent.com/WoofEnterprise/DogOrientedGroomingSystem/master/wiki/dogs-logo.png)

Welcome to the Dog Oriented Grooming System! For a short overview of the system, please visit the [Wiki](https://github.com/WoofEnterprise/DogOrientedGroomingSystem/wiki).

[![Build Status](https://travis-ci.org/WoofEnterprise/DogOrientedGroomingSystem.svg)](https://travis-ci.org/WoofEnterprise/DogOrientedGroomingSystem)


# REST 

The rest interface exists in `dogs-rest` directory, you can run the server by executing:

```
mvn clean install
cd dogs-rest
mvn tomcat7:run
```

Here are some example curl commands you can run:

List all customers
```
curl -i http://localhost:8080/pa165/rest/customers/
```

List one customer
```
curl -i http://localhost:8080/pa165/rest/customers/1
```

Delete one customer
```
curl -X DELETE http://localhost:8080/pa165/rest/customers/
```

Create a customer
```
curl -X POST -i -H "Content-Type: application/json" --data '{"name":"Feri","surname":"Mrkvicka","email":"feri.mrkvicka@neco.com","addressFirstLine":"bla", "addressCity":"Tramtaria","addressCountry":"Narnia","addressPostalCode":"62400"}' http://localhost:8080/pa165/rest/customers/create
```

Update customers email
```
curl -X PATCH -i -H "Content-Type: application/json" --data '{"email":"neco@neco.cz"}' http://localhost:8080/pa165/rest/customers/1
```

# Web 


The web interface exists in `dogs-web` directory, you can run the server by executing:

```
mvn clean install
cd dogs-web
mvn tomcat7:run
```

User credentials:
```
email:    user@user.cz
password: password
```
Admin credentials:
```
email:    admin@admin.cz
password: admin
```
