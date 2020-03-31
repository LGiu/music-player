#Music Player with microservice architecture in Spring Boot, Apache Kafka and MongoDB

An application to register and listen music based on microservice architecture in Spring Boot, Apache Kafka and MongoDB.

The objective of this project is not to seek the best organization or performance, but to create a functional example of how to use Spring Boot, Apache Kafka and MongoDB in a simple microservice architecture.
__________

#Features
- Register music band
- Register music
- Listener music
- Database persistence in NOSQL
- Independent submission and persistence process

__________

#Requirements
- Java 11
- Apache Kafka
- MongoDB

__________

#Initial setup instructions
1. Create MongoDB database with name 'musicplayer';
2. Start all modules;
3. Open Apache Kafka and change the 'max-request-size' of the 'requesttopicmusic_upload' and 'replytopicmusic_upload' topics to 50mb.

__________

#Use instructions
- Request 1

Customer

Type: Post

Url: localhost:8090/customer

Json Example: { "name": "Detonautas", "country": "Brazil", "musicStyle": "Rock" }

- Request 2

Music Upload

Type: Post

Url: localhost:8094/music-upload

Form Data Example: 
    
    file: (Select Music File) 
    idCustomer: (Id Customer)
    
- Request 3

Note: Network streaming software, such as VLC Media Player, is required for this request.

Music Listener

Type: GET

Url: localhost:8097/music-listener/323a5cf0-6893-4c9b-97cd-f96a7934a69f

