<div align="center">
<h1>URL Shortener</h1> 
<p>This repository contains code to shorten URLs, it is a part of the final examination of the course "Webservices".<br>
There are currently four branches which contain three different versions of the application.
</p>
<div style="display: inline-block; text-align: left">
    <ul>
        <li>The main branch contains a Micronaut version(Native Image + Consul Service Discovery + Consul Config)</li>
        <li>The quarkus branch contains a Quarkus version(Native image), development of this version has stopped.</li>
        <li>The spring-boot branch contains a Spring Boot version, development of this version has stopped.</li>
        <li>The micronaut branch is no longer active. Development of this version has moved to the main branch, but this branch is kept for documentation</li>
    </ul>
</div>

---
<div>
<h3>Spring Boot Specifics:</h3>
<h3>Environment variables:</h3>
<div style="display: inline-block">

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

<code>docker run --name url_shortener_spring -d -p 8080:8080 -e DB_URL=localhost:3306/db -e DB_USERNAME=root -e
DB_PASSWORD=password -e HOST_URL=localhost:8080 ghcr.io/darkendhall/url_shortener:spring-boot</code>
</div>

---

<div>

To package to a .jar file run the following command if you <b><u>HAVE</u></b> Maven installed on your machine: <code>mvn
-B package</code>
and then run <code>docker build -t image-name:version .</code> to put it inside a docker image.

To package to a .jar file run the following command if you <b><u>DO NOT HAVE</u></b> Maven
installed on your machine: <code>./mvnw -B package</code> and then run <code>docker build -t image-name:version .</code>
to put it inside a docker image

</div>
</div>
