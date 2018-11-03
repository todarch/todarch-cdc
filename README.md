# Todarch Contracts

Contracts are organized in a single place. Under the coordinates of each project, there are contracts of the consumers.

## Consumer

- Find your provider coordinates under src/main/resources/contracts/

- If not found, create and add the a pom.xml containing parts of the provider's pom.xml

```shell
cd to/provider/coordinate/directory
mvn clean install -DskipTests # will install the stubs to your local repo
```

- override `scc.contracts.mode` to LOCAL for local development

