# Backend Implementation

## Technologies
1. Sprint boot
2. Google Guava for Event Bus when autopilot is enabled

## How to build
1. Use the build.sh script. It uses maven to generate a executable jar.
```
.\build.sh
```

2. Optionally, you can use maven directly from source directory.
```
mvn clean package

```
## How to run
The backend uses the following runtime properties:
1. server.port: default to 8080
2. player.name: default to Default User
3. hostname: default to localhost

1. Use the script run.sh after building the project.
```
.\run.sh
```

2. Optionally, you can use java directly to run the executable jar:
```
java -Dsever.port=8081 -Dhostname=127.0.0.1 -Dplayer.name="Manoel Menezes" -jar ./target/spaceship-0.0.1-SNAPSHOT.jar
```

## Improvements
1. Redis for game and player data store.
2. Queue (for instance SQS or RabbitMQ) for Autopilot instead of Guava Event Bus