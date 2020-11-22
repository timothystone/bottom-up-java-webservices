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

## Lesson 104 - Mark the beans with JAXB Annotations

Jumping right into the lesson, annotations to legacy Beans provide serialization hints to the Java XML Bindings. Apache 
CXF will use these annotations to supply the types for the webservice.

JAXB will perform, through Apache CXF, a significant amount of default behaviors via the annotations. The annotations,
and some of their default behaviors will be noted below.

### JAXB Annotations

**`{@link javax.xml.bind.annotation.XmlType @XmlType}`**

Marks the bean as a complex type for serialization by CXF. The annotation takes an *optional* property of `name`. If not
provided, the type generated is of the classname annotated.

**`{@link javax.xml.bind.annotation.XmlAccessorType @XmlAccessorType}`**

Elements of the generated type will be, by default, determined by the members of the bean and the accessors and 
mutators. Thus, depending on the documentation standards of the developer, team, or organization, `@XmlAccessorType` may
or may not be required.

If used, the annotation notes, how members of the bean will be annotated. The default behavior is 
`XmlAccessType.PUBLIC_MEMBER` and documented as follows:

> Every public **getter/setter pair** and every public **field** will be automatically bound to XML, unless annotated by 
> `XmlTransient`. Fields or getter/setter pairs that are private, protected, or defaulted to package-only access are 
> bound to XML only when they are explicitly annotated by the appropriate JAXB annotations.

The lesson shows the annotation set to `@XmlAccessorType(XmlAccessType.FIELD)`. The documentation notes the following 
behavior for `XmlAccessType.FIELD`:

> Every non static, non transient **field** in a JAXB-bound class will be automatically bound to XML, unless annotated by 
> `XmlTransient`. Getter/setter pairs are bound to XML only when they are explicitly annotated by some of the JAXB 
> annotations.

The **subtle** differences between the default `PUBLIC_MEMBER` and `FIELD` types should be noted and applied specifically 
in each use-case.

**`{@link javax.xml.bind.annotation.XmlElement @XmlElement}`**

The PaymentProcessorRequest bean annotations conclude with using the `@XmlElement` annotation and showing its default
`name` attribute. Each member is annotated and a `name` attribute set. The `name` attribute defaults the field name and 
optional. Documentation standards may ask that this be explicitly set. YMMV.

**`{@link javax.xml.bind.annotation.XmlAttribute @XmlAttribute}`**

Only briefly mentioned in the lesson, is the `@XmlAttribute` annotation. It is possible to mark fields of a bean as 
attributes to the complex types. 

### Lesson Conclusion

The lesson concludes with the remaining beans of the sample project marked with JAXB annotations. `CreditCardInfo` and 
`PaymentProcessorResponse` take advantage of default `XmlAccessType.PUBLIC_MEMBER` behaviors of the `XmlAccessType` 
enumeration.

## Lesson 105 - Mark the endpoint with JAX-WS annotations 

Exposing the webservice endpoints is enable by Apache CXF though the legacy application's interfaces. The `@WebService` 
annotation performs the "heavy-lifting" removing the need to mark implementation classes.

**`{@link javax.ws.WebService @WebService}`**

The only required annotation for creating the JAX-WS endpoint. The annotation takes an optional `name` attribute, 
otherwises uses the class name, e.g., `PaymentProcessor`.

**`{@link javax.jws.WebMethod @WebMethod}`**

Developer, team, or organizational documentation standards may desire explicit use of this annotation on interface's 
methods. YMMV.

**`{@link javax.jws.WebParam @WebParam}`**

Optional. See note on documentation standards. This annotation on the parameters passed to an interface's methods. The
optional `name` attribute can provide a value differing from the interface itself.

**`{@link javax.jws.WebResult @WebResult}`**

Optional. See note on documentation standards. This annotation on the return type of an interface's methods. The
optional `name` attribute can provide a value differing from the interface itself. In the lesson, 
`PaymentProcessorResponse` is set to `response`, e.g., `@WebResult(name = "response")`

### Lesson Conclusion

Lesson 105 completes the annotations of the `PaymentProcessor` as the Web Service to the legacy monolith. The configuration
and exposure of the endpoint is performed in the remaining lessons.

## Lesson 106 - Publish the endpoint

This lesson walks through the refactoring of an earlier lesson's `@Configuration` annotated class. 

**Lesson Notes**

The lesson uses IDE specific refactoring tools to copy a previous configuration class. The following notes will make an
effort to meet the expectations of the lesson, relying less on the IDE.

### Create Configuration Package

`mkdir -p src/main/java/com/anothercaffeinatedday/ws/soap/config`

### Copy an Existing Configuration Class

```shell
cd src/main/java/com/anothercaffeinatedday/ws/soap/config
cp path/to/hellowebservice/src/main/java/package/ws/soap/config/WebServiceConfig.java
```
The resulting file, in this example copied from the Hello Web Service lesson, will need refactoring. The following 
refactoring is performed and documented in the lesson:

1. Verify `package`
1. Remove `import`
1. Set the implementation of the `EndpointImpl` to the `PaymentProcessorImpl`. Recall that CXF allows us to annotate the
interface and not the implementation. CXF performs the heavy-lifting of making the implementation available to the 
endpoint!
1. Set the `publish` value to `paymentProcessor`
1. Add the configuration class to source control, e.g., `git add --all`, and commit.

### Configure Application Context

Update the `application.properties` and set a context path. The lesson uses `/javafirstws`.

The property is Spring Boot version specific. `server.context.path` is deprecated in Spring Boot 2.x, replaced by 
`server.servlet.context-path`.

The `cxt.path` is set to override the default `/${server.servlet.context-path}/services` path provided by CXF, i.e., 
`cxt.path=/` removes the `services` portion of the URL.

## Lesson 107 - Run the application

The application is run from the Eclipse IDE. YMMV. The Maven Spring Boot plugin may be used from the IDE or command line as
follows:

```shell
mvn spring-boot:run
```

Depending on how one configured the `application.properties` and `EndpointImpl#publish` in Lesson 106, the URL may be 
`http://localhost:8080/javafirstws/services/paymentProcessor?wsdl`.

The WSDL is generated by Apache CXF.

## Lesson 108 - Test Using SOAPUI
 
 [SOAP UI](https://www.soapui.org/downloads/soapui/) provides a straight forward way to generate SOAP requests and 
 responses from your Spring Boot Apache CXF JAX-WS services.
 
 The lesson reviews use of SOAP UI to generate stub requests and view responses. 
 
 A review of how the request and response types correlate to JAXB and JAX-WS annotations is presented for review, briefly
 outlining the lifecycle of events in Apache CXF. 
 
 **dateTime element**
 
 The default `dateTime` element expects a specific format, e.g., `2020-11-22+05:00` or date +/- GMT offset. 
 