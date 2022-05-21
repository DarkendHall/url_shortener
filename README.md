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
<div align="left">
<h3>Example JSON body</h3>

```json
{
  "url": "http://google.com"
}
```

<p>The link needs to have http:// or https:// in the beginning, www is optional</p>

</div>



---

<div align="center" style="display: inline-block;">
    <h2>Micronaut Specifics</h2>

### Environment Variables

<div style="display: inline-block;">

| Environment variable | Description                             | Default   |
|----------------------|-----------------------------------------|-----------|
| CONSUL_HOST          | The URL/IP address of the Consul server | localhost |
| CONSUL_PORT          | The port of the Consul server           | 8500      |

</div>

### Example Configuration:

Should be placed in k/v store in config/url-shortener and in yaml format. <br>
The reason why it's not url_shortener is because consul doesn't appear to support "_" in the name of services.

<div align="left">

```yml
micronaut:
  server:
    port: 8080
datasources:
  default:
    url: jdbc:mysql://<database_url>
    username: <username> # This is stored as clear text, will probably not be updated to be stored as a secret at this time.
    password: <password> # This is stored as clear text, will probably not be updated to be stored as a secret at this time.
    dialect: MYSQL
jpa.default.properties.hibernate.hbm2ddl.auto: update
netty:
  default:
    allocator:
      max-order: 3
```

</div>
    <div>
        <p>To package to native image and create a Docker image run the following command if you <u><b>HAVE</b></u> Maven installed on your machine: <br> <code>mvn -B package -Dpackaging=docker-native -Pgraalvm --file pom.xml</code></p>
        <p>To package to native image and create a Docker image run the following command if you <u><b>DO NOT HAVE</b></u> Maven installed on your machine: <br> <code>./mvnw -B package -Dpackaging=docker-native -Pgraalvm --file pom.xml</code></p>
        <h4>Run command:</h4>
        <div>
            <code>docker run --name url_shortener -d -p 8080:8080 -e CONSUL_HOST=localhost -e CONSUL_PORT=8500
            ghcr.io/darkendhall/url_shortener:micronaut</code>
        </div>
    </div>
</div>
</div>
