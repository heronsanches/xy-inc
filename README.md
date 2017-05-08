## Zup's Challenge - Back-End Developer 
* [Challenge pdf file description here](https://github.com/heronsanches/xy-inc/blob/master/docs/Desafio%20Back%20end%20-%202.pdf)

### Environment Development
* Netbeans 8.2
* OpenJDK 1.8
* Apache Maven 3.3.9 
* Fedora 25
* Postgresql 9.5.6
* wildfly 10.1.0
### Test Tools
* Arquillian
 * Please put your wildfly path [here - arquillian.xml](https://github.com/heronsanches/xy-inc/blob/master/xy-inc-zup-rest/src/test/resources/arquillian.xml) and [here - arquillian.xml](https://github.com/heronsanches/xy-inc/blob/master/xy-inc-zup/src/test/resources/arquillian.xml)
```xml
  <property name="jbossHome">/home/heron/programs-libraries/wildfly-test/wildfly-10.1.0.Final</property>
 ```
 
* Junit
### Project Specifications
* [EJB project pom.xml here](https://github.com/heronsanches/xy-inc/blob/master/xy-inc-zup/pom.xml)
* [JAX-RS project pom.xml here](https://github.com/heronsanches/xy-inc/blob/master/xy-inc-zup-rest/pom.xml)
* [REST Class Service here](https://github.com/heronsanches/xy-inc/blob/master/xy-inc-zup-rest/src/main/java/com/xyinc/zup/rest/application/poi/resources/PoiR.java)
### GENERAL INSTRUCTIONS
* create a local database named "xyinc_poi" configured on default port of Postgresql (5432)
* use the [pg_dump file here](https://github.com/heronsanches/xy-inc/blob/master/database-files/pg_dump_ddl) to create the database structure
* create a directory named "/home/files/xyz"
* go to the directory created
* download the following files
  * http://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.zip
  * https://jdbc.postgresql.org/download/postgresql-42.0.0.jar
* extract "wildfly-10.1.0.Final.zip"
* start wildfly
  * wildfly-10.1.0.Final/bin/standalone.sh --server-config=standalone-full.xml
* configure wildlfy, so to do this connect to management console
  * wildfly-10.1.0.Final/bin/jboss-cli.sh --connect
* copy and paste the below block of text, CHANGING --user-name=YOUR_USER_NAME and password='YOUR_PASSWORD' accord to your local postgresql configuration

```javascript
module add --name=org.postgres --resources=/home/files/xyz/postgresql-42.0.0.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
data-source add --jndi-name=java:/PostgresDS-xy-inc-poi --name=PostgresPool-xy-inc-poi --connection-url=jdbc:postgresql://localhost:5432/xyinc_poi --driver-name=postgres --user-name=YOUR_USER_NAME --password='YOUR_PASSWORD' --max-pool-size=99
shutdown
exit

```
* start wildfly with another server configuration, this will be used to run the automated tests into a full wildfly container
  * wildfly-10.1.0.Final/bin/standalone.sh --server-config=standalone.xml
* configure wildlfy, so connect to management console
  * wildfly-10.1.0.Final/bin/jboss-cli.sh --connect
* copy and paste the below block of text, CHANGING --user-name=YOUR_USER_NAME and password='YOUR_PASSWORD' accord to your local postgresql configuration

```javascript
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
data-source add --jndi-name=java:/PostgresDS-xy-inc-poi --name=PostgresPool-xy-inc-poi --connection-url=jdbc:postgresql://localhost:5432/xyinc_poi --driver-name=postgres --user-name=YOUR_USER_NAME --password='YOUR_PASSWORD' --max-pool-size=99
/socket-binding-group=standard-sockets/socket-binding=http/:write-attribute(name=port, value=${jboss.http.port:8082})
/socket-binding-group=standard-sockets/socket-binding=https/:write-attribute(name=port, value=${jboss.https.port:8053})
/socket-binding-group=standard-sockets/socket-binding=management-http/:write-attribute(name=port, value=${jboss.management.http.port:9985})
/socket-binding-group=standard-sockets/socket-binding=ajp/:write-attribute(name=port, value=${jboss.ajp.port:8019})
shutdown
exit

```
* Start wildfly on this specified server configuration
  * wildfly-10.1.0.Final/bin/standalone.sh --server-config=standalone-full.xml
* clone the repository
  * git clone https://github.com/heronsanches/xy-inc.git
* To run the application with automated tests delete "-Dmaven.test.skip=true" from the following commands
* go to "xy-inc-zup" folder and run the following command
  * mvn clean install -Dmaven.test.skip=true wildfly:deploy
* go to "xy-inc-zup-rest" folder
  * mvn clean -Dmaven.test.skip=true wildfly:deploy
### REST Services
1. http://localhost:8080/ws.api/poi/all   (GET)
  * It lists all POI (Point of Interesting)
  * Output: JSON
  * Return example:
```javascript
{"status":"OK","pois":[{"x":27,"y":12,"name":"Lanchonete"},{"x":31,"y":18,"name":"Posto"}]}
```
2. http://localhost:8080/ws.api/poi   (POST)
  * It inserts a POI
  * Input: JSON
  * Output: JSON
  * Input example:
```javascript
{"x":43, "y":5, "name":"Empresa de Tecnologia"}
```
  * Output example:
```javascript
{"status":"OK","pois":[{"x":43,"y":5,"name":"Empresa de Tecnologia"}]}
```
3. http://localhost:8080/ws.api/poi/all/by-distance-range?x=20&y=10&distance=10   (GET)
  * Get POIs into a specified distance
  * Output: JSON
  * Output example:
```javascript
{"status":"OK","pois":[{"x":27,"y":12,"name":"Lanchonete"},{"x":15,"y":12,"name":"Joalheria"},{"x":12,"y":8,"name":"Pub"},{"x":23,"y":6,"name":"Supermercado"}]}
```
