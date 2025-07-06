# Aplikace

## První start v bash

```bash
# .env obsahuje 
#SPRING_DATASOURCE_PASSWORD=vygenerovaneheslo
. .env
export SPRING_DATASOURCE_PASSWORD
```

```bash
mvn clean install
```

```bash
mvn docker:start@start-docker-containers
```

Následující krok trvá asi 3 minuty

```bash
mvn spring-boot:run
```

* [http://localhost:8080/hello?name=Gemini](http://localhost:8080/hello?name=Gemini)

```bash
mvn docker:stop@stop-docker-containers
```

```bash
mvn docker:remove@remove-docker-containers
```


## První start v STS

### Předpoklady

```bash
git clone https://github.com/Luk-Iss/hellodb.git
```

* Spuštěné STS

### Postup

* File -> Import
* Chose Maven -> Existing
* Click Next
* Click Browse button
* Search a folder with checkouted pom.xml file
* Click Select button
* The pom.xml should be detected automaticaly
* Click Finish
* Projekt se natáhne
* Pravé tlačítko na projekt a Run as -> Spring Boot App
* Pokud spadne, že nemá heslo (asi musí), tak 
* POZOR! application.properties je nutné nastavit na UTF-8 vždy znovu

```bash
cat .env
```

Spustit db (asi 3 minuty)

jako pro mvn


* Run -> Run Configurations
* Spring Boot App -> už by tam mělo být hellodb (po prvním pokusu o spuštění)
* Záložka Environment
* Tlačítko Add
* Name SPRING_DATASOURCE_PASSWORD
* Value (hodnota z cat .env)
* Tlačítko OK
* Tlačítko Apply
* Tlačítko Run
* V Console by měly přibývat řádky, až tam skočí Started HellodbApplication

* [http://localhost:8080/hello?name=Gemini](http://localhost:8080/hello?name=Gemini)

### Úklid

* Po otestování nezapomenout vypnout v STS pomocí stop button

* Vypnout db jako pro mvn

# Připojení

## FREEPDB1_ABO3

- Username: ABO3
- Password: `cat .env`
- Role: Default
- Host Name: localhost
- Port: 1522
- Service Name: FREEPDB1

## FREEPDB1_SYS

- Username: SYS
- Password: `cat .env`
- Role: SYSDBA
- Host Name: localhost
- Port: 1522
- Service Name: FREEPDB1

## Zjištění IP adresy

- Spustit cmd ve windows
- ipconfig
- Najít IP adresu vEthernet
- 172.29.0.1
- Připojení do Dockeru
- docker exec -it oracle_db bash
- Ve WSL najdu takto
- ip r | head -1
- default via 172.29.0.1 dev eth0 proto kernel
- SELECT utl_http.request('http://172.29.0.1:8080/hello?name=Gemini') FROM dual;