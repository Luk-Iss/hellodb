# Aplikace

Tento projekt představuje jednoduchou Spring Boot aplikaci `hellodb`, která demonstruje připojení k Oracle databázi a vystavuje jednoduchý REST endpoint. Pro spuštění databáze je využíván Docker.

## První spuštění v Bash

Tyto kroky Vás provedou spuštěním aplikace z příkazové řádky. Ujistěte se, že máte nainstalovaný [Maven](https://maven.apache.org/install.html) a [Docker Desktop](https://www.docker.com/products/docker-desktop/).

### 1. **Nastavení proměnné prostředí:**

Nejprve si vytvořte soubor `.env` v kořenovém adresáři projektu s obsahem:
    
```bash
# .env obsahuje
SPRING_DATASOURCE_PASSWORD=vase_silne_heslo # Nahraďte "vase_silne_heslo" vlastním heslem
```

Poté načtěte tuto proměnnou prostředí do aktuální session:
    
```bash
. .env
export SPRING_DATASOURCE_PASSWORD
```

* Důležité: Tímto způsobem se heslo nastaví pouze pro aktuální session. Pro trvalejší řešení zvažte přidání proměnné do vašeho profilu shellu (např. `.bashrc`, `.zshrc`) nebo použití nástrojů pro správu tajemství.

### 2. **Sestavení aplikace:**

Sestavte projekt a stáhněte všechny potřebné závislosti.

```bash
mvn clean install
```

### 3. **Spuštění Oracle databáze v Dockeru:**

Tento krok stáhne a spustí Docker kontejner s Oracle databází.

```bash
mvn docker:start@start-docker-containers
```

* Následující krok (spuštění databáze) může trvat **přibližně 3 minuty**, v závislosti na rychlosti vašeho internetového připojení a výkonu počítače.

* Kontejner je nastaven tak, aby čekal, dokud databáze není plně připravena k použití.

### 4. **Spuštění Spring Boot aplikace:**

Jakmile je databáze spuštěna a připravena, můžete spustit samotnou Spring Boot aplikaci.

```bash
mvn spring-boot:run
```

### 5. **Ověření funkčnosti:**

Otevřete prohlížeč a navštivte následující URL, abyste ověřili, že aplikace funguje správně:

* [http://localhost:8080/hello?name=Gemini](http://localhost:8080/hello?name=Gemini)

Měli byste vidět odpověď "Hello Gemini from Oracle!".

### 6. **Úklid (zastavení a odstranění Docker kontejnerů):**

* Aplikaci ukončíte `Ctrl-C`.

* Po dokončení práce můžete zastavit a odstranit Docker kontejner databáze.

```bash
mvn docker:stop@stop-docker-containers
```

```bash
mvn docker:remove@remove-docker-containers
```

---

## První spuštění v Spring Tool Suite (STS) / IntelliJ IDEA

Tyto instrukce jsou určeny pro vývojáře používající IDE. Návod předpokládá spuštěné IDE a nainstalovaný Git.

### Předpoklady

* Spuštěné [Spring Tool Suite (STS)](https://spring.io/tools) nebo [IntelliJ IDEA](https://www.jetbrains.com/idea/)

* Nainstalovaný [Git](https://git-scm.com/downloads)

### Postup

#### 1. **Klonování repozitáře:**

```bash
git clone [https://github.com/Luk-Iss/hellodb.git](https://github.com/Luk-Iss/hellodb.git)
```

#### 2. **Import projektu do IDE:**

 * **File** -> **Import**

 * Zvolte **Maven** -> **Existing Maven Projects**

 * Klikněte na **Next**

 * Klikněte na tlačítko **Browse...**

 * Vyhledejte složku s naklonovaným `pom.xml` souborem (kořenový adresář `hellodb` projektu).

 * Klikněte na tlačítko **Select Folder** (nebo Open).

 * `pom.xml` by měl být automaticky detekován.

 * Klikněte na **Finish**.

 * Projekt se naimportuje a IDE stáhne potřebné závislosti.

#### 3. **Nastavení proměnné prostředí pro spuštění aplikace:**

Spring Boot aplikace potřebuje heslo k databázi. Toto heslo se načítá z proměnné prostředí `SPRING_DATASOURCE_PASSWORD`.

* Pokud jste tak ještě neučinili, získejte heslo z vašeho `.env` souboru:

```bash
cat .env
```

* V IDE (Spring Tool Suite / IntelliJ IDEA):
 * Pravým tlačítkem klikněte na projekt v Project Exploreru/Navigatoru.
 * Zvolte **Run As** -> **Spring Boot App**. (Při prvním spuštění aplikace pravděpodobně spadne s chybou o chybějícím hesle – to je očekávané).
 * Nyní přejděte do **Run** -> **Run Configurations...**
 * V levém panelu v sekci **Spring Boot App** by se měla objevit konfigurace `hellodb`. Klikněte na ni.
 * Přejděte na záložku **Environment**.
 * Klikněte na tlačítko **Add...**
 * Do pole **Name** zadejte `SPRING_DATASOURCE_PASSWORD`.
 * Do pole **Value** zadejte heslo, které jste získali z `cat .env`.
 * Klikněte na tlačítko **OK**.
 * Klikněte na tlačítko **Apply** pro uložení konfigurace.
 * Klikněte na tlačítko **Run** pro spuštění aplikace.
 * V konzoli by měly přibývat řádky a nakonec by se měla objevit zpráva `Started HellodbApplication`.

#### 4. **Nastavení kódování pro `application.properties` (pouze pro Eclipse/STS):**

* Pokud máte problémy s diakritikou nebo speciálními znaky, ujistěte se, že `application.properties` má nastavené kódování UTF-8.

* Pravým tlačítkem klikněte na soubor `src/main/resources/application.properties`.

* Vyberte **Properties** -> **Resource**.

* Změňte **Text file encoding** na **UTF-8**.

* Klikněte **Apply and Close**.

#### 5. **Spuštění databáze (nutné i pro IDE):**

I při spuštění aplikace z IDE je nutné mít spuštěnou databázi. Spusťte ji pomocí Mavenu z terminálu, jak je popsáno v sekci "První spuštění v Bash - Krok 3".

```bash
mvn docker:start@start-docker-containers
```

#### 6. **Ověření funkčnosti:**

Otevřete prohlížeč a navštivte následující URL:

* [http://localhost:8080/hello?name=Gemini](http://localhost:8080/hello?name=Gemini)

### Úklid po spuštění v IDE

* **Zastavení Spring Boot aplikace:** Po otestování nezapomeňte vypnout Spring Boot aplikaci v STS/IntelliJ IDEA pomocí červeného tlačítka "Stop" v konzoli.

* **Vypnutí databáze:** Vypněte Docker kontejner databáze pomocí Mavenu z terminálu, jak je popsáno v sekci "První spuštění v Bash - Krok 6".

```bash
mvn docker:stop@stop-docker-containers
mvn docker:remove@remove-docker-containers
```

---

# Připojení k Oracle databázi (Volitelné)

Pokud se chcete připojit k běžící databázi přímo (např. pomocí SQL Developer, DBeaver nebo SQLcl), použijte následující připojovací údaje. Databáze je spuštěna uvnitř Docker kontejneru.

* **Host Name:** `localhost`
* **Port:** `1521` (Mapováno z Docker kontejneru 1521:1521)

## Uživatel `HELLO` (aplikace)

-   **Username:** `HELLO`

-   **Password:** Hodnota z `cat .env`

-   **Role:** Default

-   **Service Name:** `FREEPDB1`

## Uživatel `SYS` (administrace)

-   **Username:** `SYS`

-   **Password:** Hodnota z `cat .env`

-   **Role:** `SYSDBA`

-   **Service Name:** `FREEPDB1`

---

## Zjištění IP adresy Docker hosta (pro volání z Docker kontejneru)

Pokud potřebujete volat REST endpoint vaší Spring Boot aplikace **z vnitřku Docker kontejneru Oracle databáze** (např. pro testování `UTL_HTTP`), potřebujete znát IP adresu vašeho hostitelského stroje, na které Spring Boot aplikace běží.

### Pro Windows (s WSL2 a Docker Desktop)

1.  Otevřete **Příkazový řádek (CMD)** nebo **PowerShell**.

2.  Zadejte příkaz:

```bash
ipconfig
```

3.  Vyhledejte IP adresu pod adaptéry `vEthernet (WSL)` nebo podobné, která představuje síťový most pro WSL2/Docker. Často to bývá adresa, která začíná `172.x.x.x`.
    *Příklad:* `IPv4 Address. . . . . . . . . . . : 172.29.0.1`

### Pro WSL (pokud spouštíte Docker v WSL)

1.  Otevřete váš WSL terminál.

2.  Zadejte příkaz:

```bash
ip r | head -1
```

3.  Výstup by měl ukázat výchozí bránu, která je IP adresou vašeho hosta.

*Příklad:* `default via 172.29.0.1 dev eth0 proto kernel`

### Příklad volání z SQLcl/SQL Developer (uvnitř Dockeru, nebo z aplikace)

Jakmile znáte IP adresu hosta (např. `172.29.0.1`), můžete ji použít pro volání externích služeb z databáze:

```sql
SELECT utl_http.request('http://172.29.0.1:8080/hello?name=Gemini') FROM dual;
```

Tento dotaz spustíte přímo v SQLklientu připojeném k databázi (např. SQL Developer) nebo uvnitř Docker kontejneru po spuštění sqlplus