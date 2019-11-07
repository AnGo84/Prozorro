# Prozorro
Build status: [![build_status](https://travis-ci.com/AnGo84/Prozorro.svg?branch=master)](https://travis-ci.com/AnGo84/Prozorro.svg)
- - -
Desktop application for reading data from the site Prozorro.ua for importing into the database.

**To using necessary:**

- IDE
- JDK 1.8 or later
- Maven
- Oracle(11.2.0) or MySQL database

### Warning
First of all, you need to install oracle lib to your maven repository.
In the project used library for working with Oracle 11.0.2- ojdbc6.jar. It attached to the project: from root **'lib/ojdbc6.jar'**.
For instaling to Maven:
`mvn install:install-file -Dfile='YOUR_PATH'/ojdbc6.jar -DgroupId=com.cmabreu -DartifactId=mylocal-lib -Dversion=1.5 -Dpackaging=jar -DgeneratePom=true`

If you do not going to work with Oracle DB, you can remove oracle dependency from **pom.xml**:
```
<dependency>
	<groupId>com.oracle</groupId>
    <artifactId>ojdbc6</artifactId>
    <version>${oracle.version}</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/lib/ojdbc6.jar</systemPath>
</dependency>
```

**To build the project use:**
`mvn clean jfx:jar`

Right **jar** file will be in directory **'Project_name/target/jfx/app/'**

## Information and data sources

Prozorro tutorial with API:

- [Tenders' information](http://api-docs.openprocurement.org/uk_UA/latest/standard/index.html)
- [Contracts' information](http://contracting.api-docs.openprocurement.org/uk/latest/tutorial.html)
- [Plans' information](http://planning.api-docs.openprocurement.org/uk/latest/standard/contract.html)

Developed and tested for API version 2.4.
For getting information from beginning use start pages:
- https://public.api.openprocurement.org/api/API_VERSION/tenders
- https://public.api.openprocurement.org/api/API_VERSION/plans
- https://public.api.openprocurement.org/api/API_VERSION/contracts

Information about the tender can be obtained by reference https://public.api.openprocurement.org/api/API_VERSION/tenders/Tender_ID

For example, information for tender with id **'55e20be09f3544deace53f5a9019f800'** can be gettin by link [https://public.api.openprocurement.org/api/2.4/tenders/55e20be09f3544deace53f5a9019f800](https://public.api.openprocurement.org/api/2.4/tenders/55e20be09f3544deace53f5a9019f800)

## Currency rates source

For getting currencies rates' information used NBU API [https://bank.gov.ua/control/uk/publish/article?art_id=38441973](https://bank.gov.ua/control/uk/publish/article?art_id=38441973)

## Helpful information
For work with JSON was used some online resources

###### Create classes from JSON:
- http://www.jsonschema2pojo.org/

###### Work with JSON online:
- https://jsoneditoronline.org/
- http://jsonparseronline.com/