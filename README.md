<div align="center">
<h1>URL Shortener</h1> 
<p>This repository contains code to shorten URLs, it is a part of the final examination of the course "Webservices".<br>
There are currently three branches which contain three different versions of the application.
</p>
<div style="display: inline-block; text-align: left">
    <ul>
        <li>The main branch contains a Spring Boot version</li>
        <li>The quarkus branch contains a Quarkus version(Native image)</li>
        <li>The micronaut branch contains a Micronaut version(Native Image + Consul Service Discovery)</li>
    </ul>
</div>


---

<div align="center" style="display: inline-block;">

### Environment variables:

| Environment variable | Description                                                                         | Default   |
|----------------------|-------------------------------------------------------------------------------------|-----------|
| DB_URL               | The URL/IP address of the MySQL server<br/> Example: <code>localhost:3306/db</code> | N/A       |
| DB_USERNAME          | Username of the database user                                                       | root      |
| DB_PASSWORD          | Password of the database user                                                       | password  |

</div>

---

<div align="center" style="display: inline-block;">
    <h2>Micronaut Specifics</h2>
</div>

The default value of the netty server is <code>8080</code>.<br>
To allow for several instances on the same machine you need to set the SERVER_PORT environment variable to <code>
-1</code>(random port)

### Environment Variables

<div style="display: inline-block;">

| Environment variable | Description                             | Default   |
|----------------------|-----------------------------------------|-----------|
| CONSUL_HOST          | The URL/IP address of the Consul server | localhost |
| CONSUL_PORT          | The port of the Consul server           | 8500      |
| SERVER_PORT          | The port of the server                  | 8080      |

</div>
<div>
To package to native image and create a Docker image run the following command if you <u><b>HAVE</b></u> Maven installed
on your machine: <br> `mvn -B package -Dpackaging=docker-native -Pgraalvm --file pom.xml`

To package to native image and create a Docker image run the following command if you <u><b>DO NOT HAVE</b></u> Maven
installed on your machine: <br> `./mvnw -B package -Dpackaging=docker-native -Pgraalvm --file pom.xml`


</div>

</div>
