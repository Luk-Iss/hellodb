# Aplikace

## Start pomocí docker-compose - jednoduchý, ale nanic

```bash
git clone https://github.com/Luk-Iss/hellodb.git
```

```bash
cd hellodb
```

.env

```env
    SPRING_DATASOURCE_PASSWORD=your_secure_password
```

Až tento příkaz skončí, lze jít na další krok

```bash
date +"%T" && docker-compose up --build -d && while ! docker-compose logs 2>&1 | grep -q "DATABASE IS READY TO USE!"; do echo "nedb ..."; sleep 5; done && while ! docker-compose logs 2>&1 | grep -q "DATABASE IS READY TO USE!"; do echo "neapp ..."; sleep 5; done && date +"%T" && echo "Databáze a aplikace jsou připravené k použití!"
```

* [http://localhost:8080/hello?name=Gemini](http://localhost:8080/hello?name=Gemini)

Úklid

```bash
docker-compose down
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

```bash
date +"%T" && docker-compose up -d --no-deps oracle_db && while ! docker-compose logs 2>&1 | grep -q "DATABASE IS READY TO USE!"; do echo "nedb ..."; sleep 5; done && date +"%T" && echo "Db ready!"
```

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

```bash
docker-compose down
```

## Kompost (někdy nazýváno užitečné příkazy)

Toto jen databáze

```bash
    docker-compose up -d --no-deps oracle_db
```

Toto db i app

```bash
    docker-compose up --build -d
```

Tento příkaz lze ukončit Ctrl-c a vše běží dál

```bash
    docker-compose logs -f
```

V logu musí být řetězce:

* Started HellodbApplication
* DATABASE IS READY TO USE!

```
docker-compose logs --no-color > docker-compose.log
vimdiff docker-compose.log  $(ls -t docker-compose.*.log | head -n 1)
mv docker-compose.log docker-compose.$(date +"%y%m%d%H%M%S").log
```

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
