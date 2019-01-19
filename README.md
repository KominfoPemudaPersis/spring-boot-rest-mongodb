# spring-boot-rest-mongodb

#### REST API: Java Spring Boot and MongoDB

Creating a RESTful web service using the Spring Boot framework and MongoDB

#### Why Spring Boot?

The Java Spring Boot framework provides a powerful set of tools for web development on both the front-end and back-end. It has built-in configuration for security and database access, as well as simple request mappings. This framework is highly configurable and makes web development extremely simple.

#### Why MongoDB?

Mongo is quickly growing in popularity among NoSQL databases. Read more about NoSQL and its benefits here. MongoDB, specifically, is perfect for developing a REST API with Spring Boot for a couple of key reasons:

* Data is stored in MongoDB as JSON
* Spring Boot provides a powerful MongoDB connector

#### Tools Used in this Tutorial

1. Java
2. Spring Boot
3. Maven
4. MongoDB
5. Postman

#### Step 1: Creating a Database

This tutorial assumes that you already have an accessible instance of MongoDB that you can connect to. For more information on getting started with MongoDB, visit their online tutorial.

Let us start by creating a test database. I will call mine “halaqohIT” using the following command in the MongoDB shell, or through a database manager like MongoDB Compass:

```
use halaqohIT;
```

This will create a new database in MongoDB that we can use for our tutorial.

#### Step 2: Adding a MongoDB Collection and Data

In MongoDB, collections are analogous to tables in a relational database — they hold the data that we will be querying using our REST API. I will be creating a sample collection that will hold data about different types of pets. Let’s create the collection with the following command:
```
db.createCollection(“pets”);
```
Once the collection is created, we need to add some data! This collection will hold the names, breeds, and species of various pets, so a single document would be formatted as follows:
```
{
  “name” : “Spot”,
  “species” : “dog”,
  “breed” : “pitbull”
}
```
We can add data to the collection with the following command:
```
db.pets.insertMany([
  {
    “name” : “Spot”,
    “species” : “dog”,
    “breed” : “pitbull”
  },
  {
    “name” : “Daisy”,
    “species” : “cat”,
    “breed” : “calico”
  },
  {
    “name” : “Bella”,
    “species” : “dog”,
    “breed” : “australian shepard”
  }
]);
```
Querying the collection with `db.pets.find({});` reveals that MongoDB automatically assigns a unique _id field to each document to make it easier to find and edit documents.

#### Using Spring Initializr to Create a Project

Spring offers a tool called the Spring Initializr to help jump-start your Spring Boot project. Access the Initializr at start.spring.io, and enter the following information:

* Group: This is the package name

    Example: com.kominfo.halaqohit

* Artifact: This is the project name

    Example: rest.api.pets

* Dependencies: These are the features that will be added to your project (remember, you can always add these later).

    Our tutorial will use the “Web” and “MongoDB” dependencies

Then click `Generate Project` to download a .zip file with the basic project structure.

![Spring Initializr](img/start.png "Spring Initializr")

#### Adding Model to Spring Boot Project

With the project downloaded from the Spring Initializr, you can now open it in your favorite IDE to begin editing the project.
First, we will want to add a pets model, so that Spring will know what kind of data the database will return.
We can do this by creating a new folder in `src/main/java/[package name]/` called “models”. In the new “models” folder,
we can create a file called Pets.java. This Java class will hold the basic structure of a document in the “pets” collection,
so the class looks as follows:

```
package com.kominfo.halaqohit.springbootdatajpamongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pets {
    @Id
    public String id;

    public String name;
    public String species;
    public String breed;

    // Constructors
    public Pets() {
    }

    public Pets(String id, String name, String species, String breed) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
```

