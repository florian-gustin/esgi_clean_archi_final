#/bin/bash
rm -rf ~/.consoleagenda
mkdir ~/.consoleagenda
cp -rf .consoleagenda/ ~/.consoleagenda


mvn exec:java -Dexec.mainClass="org.example.App" -Dexec.args="add -c 'myTask'"
mvn exec:java -Dexec.mainClass="org.example.App" -Dexec.args="update ab29a0b5-8340-483b-9bf1-d2e92cf7a841 -c 'myTask updated'"
mvn exec:java -Dexec.mainClass="org.example.App" -Dexec.args="add-sub ab29a0b5-8340-483b-9bf1-d2e92cf7a841 -c 'mySubTask'"
mvn exec:java -Dexec.mainClass="org.example.App" -Dexec.args="remove 513af87a-6636-43f7-83fd-eceadaa7a94d"
mvn exec:java -Dexec.mainClass="org.example.App" -Dexec.args="update abdcfd38-4fe5-4bc7-90cf-64488192a3a2 -c 'myTask updated' -d '2023-02-19'"
mvn exec:java -Dexec.mainClass="org.example.App" -Dexec.args="list"

