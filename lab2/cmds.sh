javac -cp resources/Pokemon.jar:. sources/Main.java sources/pokemons/*.java sources/moves/*.java
java -cp resources/Pokemon.jar:. sources/Main
jar cfm lab2.jar resources/MANIFEST.MF sources/Main.class sources/moves/*.class sources/pokemons/*.class
rm -r sources/*.class
rm -r sources/moves/*.class
rm -r sources/pokemons/*.class
java -jar lab2.jar