CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "com.github.lukiss" AS
public class HelloOracle {
    public static String sayHello(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Ahoj neznámý."; // Případ pro prázdné jméno
        } else {
            return "Ahoj " + name + ".";
        }
    }
}
/
