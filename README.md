# Bottom Up, or Java First, SOAP Web Services

## Introduction

Udemy's Bharath Thippireddy walks us though the creation of a SOAP Web Service from a Legacy Application written in Java. In ten lessons this Section will present an existing "Payment Gateway Application" and extract a SOAP endpoint for client development.

## Lessons

This repository will follow along each step. Each branch will reflect the completed portion, including the assignment in
Lesson 109. `master` will reflect the full working project.

### Repository Notes

Bharath uses Eclipse throughout his course. I'm neither a fan nor user of Eclipse. This repository and the instructions 
that follow will use [JetBrains's IntelliJ IDEA](https://www.jetbrains.com/idea/download/) 2020.2.3 (or a version similar). 
[NetBeans 12](https://netbeans.apache.org/download/nb120/nb120.html) proved to be similarly capable, but the Spring Boot
Initializr integration was missing at the time of the project creation. Netbeans 12 Maven integration for Spring Boot 
archetypes may have proven useful, but not tested. The [Spring Boot Initializr](https://start.spring.io/) website is a 
great alternative for tools lacking direct integration.

### Overview

Each step will be as detailed as necessary to reflect the work performed on the branch and consumed by `master`.

1. Lesson 102 — Payment Gateway Legacy Application
1. Lesson 103 — Import the Legacy Project
1. Lesson 104 — Mark the beans with JAXB Annotations
1. Lesson 105 - Mark the endpoint with JAX-WS annotations 
1. Lesson 106 - Publish the endpoint
1. Lesson 107 - Run the application
1. Lesson 108 - Test Using SOAPUI
1. Lesson 109 - SOAPUI Assignment
1. Lesson 110 - Section Summary

## Lesson 102 - Payment Gateway Legacy Application

Bharath reviews a Payment Gateway Application that a financial institution has published for retailers, e.g., Amazon. 
The gateway applies the standard n-tier model for web applications using the following:

1. Web Layer
1. Business Layer
1. Data Access Layer

This Payment Gateway as designed only supports Amazon. If the financial institution desired to support additional 
retailers, e.g., EBay, et al., the application would have to be rewritten for each retailer or stood up individually for
each retailer.

Exposing a "Payment Gateway Web Service" in the Business Layer via JAXB annotations addresses this brute force approach.
The new web service will expose the business logic in a way that any retailer could use the payment gateway.

**Lesson Notes**

An introduction to traditional web service n-tier design with an overview of extracting a SOAP service from existing web
services.

## Lesson 103 - Import the Legacy Project

Bharath reviews the steps for developing a new SOAP web service and extending an existing service. The steps in this 
section will follow:

1. Create the project
1. Create the endpoint
1. Mark with JAXB annotations
1. Mark with JAX-WS annotations
1. Create the config class
1. Run the application

The first two steps are provided by Bharath and Udemy as a downloadable resource. This Maven project will be imported 
into the IDE. This importing is IDE specific and fairly direct in most leveraging the underlying Maven architecture.

**Lesson Note**

Importing the project skips Step 1 and 2, Create the project and Create the endpoint. The project is imported below and 
the remaining steps performed in the following lessons.

**Importing the Project**

As shown in the commit history, the sample project provided in the Udemy course is imported. A number of immediate 
changes supporting current Spring Boot and Java versions are made as follows:

*General Clean up*

Remove `.DS_Store`. Provide `.gitignore` 

*Java 15*

The `<java.version>` property is updated to `15`. This necessitates updates to Spring Boot and Apache CXF versions.

*Spring Boot*

Updated to `2.3.5.RELEASE` and reflected in dependencies.

*Apache CXF*

As of November 2020, Apache CXF is at version `3.4.1`. This is reflected in a new property, `cxf.version` and applied to
the project as needed.

*Package Refactoring*

For my personal mental context, the packages are refactored for my development domain, `com.anothercaffeinatedday`.

*mvn clean package*

Test the changes, performing a build and running the test suite provided in with Java 15.

### Legacy Application Tour

The lesson concludes with a tour of the legacy application. The commit history will show a number of refactorings 
performed, reflecting almost 20 years in Credit Card acquisition and servicing.

 

