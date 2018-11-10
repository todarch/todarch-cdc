# Todarch Contracts

Contracts are organized in a single place. Under the coordinates of each project, there are contracts of the consumers.

## Consumer

- Find your provider coordinates under `src/main/resources/contracts/`

- If not found, create and add the a pom.xml containing parts of the provider's pom.xml

```shell
cd to/provider/coordinate/directory
mvn clean install
```

## Provider

- create the JAR that contains all contracts in `src/main/resources/contracts/`

```shell
cd todarch-cdc
mvn clean install
```

- now you can go back and work on the provider side, provider will pull in the JAR with all contracts and generate verification tests.

## Documentation

- [Spring Cloud Contract Flow](http://cloud-samples.spring.io/spring-cloud-contract-samples/tutorials/contracts_external.html)

- check out the content of contracts JAR

```shell
jar tf ~/.m2/repository/com/todarch/todarch-cdc/1.0-SNAPSHOT/todarch-cdc-1.0-SNAPSHOT.jar
```

* Do not use exact string value for `errorMessage` for provider side verification. It is enough to agree on `errorCode`, it does not worth to break tests when error message string is changed.

```shell
body(
    errorCode: "USER4",
    errorMessage: $(c("Account is not active."), p(anyNonBlankString()))
)
```
