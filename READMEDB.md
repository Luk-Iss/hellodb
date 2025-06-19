# Sql
Mimo git je .env ve stejném adresáři jako docker-compose.yaml
Obsahuje:
```
ORACLE_PASSWORD=##ZdeHesloOracle##
APP_USER_PASSWORD=##ZdeHesloABO3##
```

## Oddělený start
```
docker-compose up -d; docker-compose logs -f
# Tyto 2 kroky trvají asi 4 minuty
# Končí pár vteřin po: The following output is now from the alert_FREE.log file:
docker-compose logs --no-color > docker-compose.log
vimdiff docker-compose.log  $(ls -t docker-compose.*.log | head -n 1)
mv docker-compose.log docker-compose.$(date +"%y%m%d%H%M%S").log
```

## Vypínání
```
docker-compose stop
docker-compose down
docker-compose down --rmi -all
```

### Připojení
## FREEPDB1_ABO3
- Username: ABO3
- Password: `tail -1 .env`
- Role: Default
- Host Name: localhost
- Port: 1522
- Service Name: FREEPDB1
## FREEPDB1_SYS
- Username: SYS
- Password: `head -1 .env`
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
