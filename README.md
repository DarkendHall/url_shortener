<div align="center">
<div>
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

<h3>Environment variables:</h3>

| Environment variable | Description                                                                            | Default  |
|----------------------|----------------------------------------------------------------------------------------|----------|
| DB_URL               | The URL/IP address of the MySQL server<br/> Example: <code>localhost:3306/db</code>    | N/A      |
| DB_USERNAME          | Username of the database user                                                          | root     |
| DB_PASSWORD          | Password of the database user                                                          | password |
| HOST_URL             | The URL of the server(used for location headers)<br> Example: <code>example.com</code> | N/A      |

</div>

</div>

<div>
    <h3>Run command:</h3>

<code>docker run --name url_shortener_spring -d -p 8080:8080 -e DB_URL=localhost:3306/db -e HOST_URL=localhost:8080
ghcr.io/darkendhall/url_shortener:spring-boot</code>
</div>

<div>
<h3>Spring Boot Specifics:</h3>

To package to a .jar file run the following command if you <b><u>HAVE</u></b> Maven installed on your machine: <code>mvn
-B package</code>
and then run <code>docker build -t image-name:version .</code>

To package to a .jar file run the following command if you <b><u>DO NOT HAVE</u></b> Maven
installed on your machine: <code>./mvnw -B package</code> and then run <code>docker build -t image-name:version .</code>

</div>

</div>
