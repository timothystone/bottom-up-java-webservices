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

Bharath reviews a Payment Gateway Application that a financial institution has published for retailers, e.g., Amazon. The gateway applies the standard n-tier model for web applications using the following:

1. Web Layer
1. Business Layer
1. Data Access Layer

This Payment Gateway as designed only supports Amazon. If the financial institution desired to support additional retailers, e.g., EBay, et al., the application would have to be rewritten for each retailer or stood up individually for each retailer.

Exposing a "Payment Gateway Web Service" in the Business Layer via JAXB annotations addresses this brute force approach. The new web service will expose the business logic in a way that any retailer could use the payment gateway.

**Lesson Notes**

An introduction to traditional web service n-tier design with an overview of extracting a SOAP service from existing web services.

