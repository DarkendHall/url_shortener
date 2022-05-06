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

<h3>Environment variables:</h3>

| Environment variable | Description                                                                            | Default  |
|----------------------|----------------------------------------------------------------------------------------|----------|
| DB_URL               | The URL/IP address of the MySQL server<br/> Example: <code>localhost:3306/db</code>    | N/A      |
| DB_USERNAME          | Username of the database user                                                          | root     |
| DB_PASSWORD          | Password of the database user                                                          | password |
| HOST_URL             | The URL of the server(used for location headers)<br> Example: <code>example.com</code> | N/A      |

</div>

</div>
