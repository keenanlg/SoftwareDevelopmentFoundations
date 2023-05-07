default: cpsc2150/extendedConnectX/models/*.java cpsc2150/extendedConnectX/GameScreen.java
	javac cpsc2150/extendedConnectX/models/*.java cpsc2150/extendedConnectX/GameScreen.java

run: cpsc2150/extendedConnectX/models/*.class cpsc2150/extendedConnectX/GameScreen.java
	java cpsc2150.extendedConnectX.GameScreen

test: cpsc2150/extendedConnectX/models/*.java
    javac -Xlint -cp .:/usr/share/java/junit4.jar cpsc2150/extendedConnectX/models/*.java

testGB: cpsc2150/extendedConnectX/models/*.class
    java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.models.TestGameBoard

testGBmem: cpsc2150/extendedConnectX/models/*.class
    java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.models.TestGameBoardMem

clean: 
	rm -f cpsc2150/extendedConnectX/models/*.class
	rm -f cpsc2150/extendedConnectX/*.class