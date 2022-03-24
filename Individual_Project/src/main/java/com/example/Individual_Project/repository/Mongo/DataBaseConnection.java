package com.example.Individual_Project.repository.Mongo;

/*import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;*/
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class DataBaseConnection {

   /* ConnectionString connectionString = new ConnectionString("mongodb+srv://Lars:Munten100Lars@cluster0.zc4r1.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = (MongoClient) MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");*/

}
