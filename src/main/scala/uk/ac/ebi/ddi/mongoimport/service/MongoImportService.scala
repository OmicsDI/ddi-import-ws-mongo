package uk.ac.ebi.ddi.mongoimport.service

//import com.mongodb.{BasicDBObject, DB, DBObject}
//import com.mongodb.casbah.{MongoClient, MongoClientURI, WriteConcern}
import org.mongodb.scala._
import uk.ac.ebi.ddi.mongoimport.servicetrait.ImportServiceTrait

object MongoImportService extends ImportServiceTrait {
  val mongoClient: MongoClient = MongoClient()

  // get handle to "mydb" database
  val database: MongoDatabase = mongoClient.getDatabase("mydb")

  // get a handle to the "test" collection
  val collection: MongoCollection[Document] = database.getCollection("test")

  def save(data:String): Unit ={

    collection.insertOne(Document.apply(data)).subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println(s"onNext: $result")
      override def onError(e: Throwable): Unit = println(s"onError: $e")
      override def onComplete(): Unit = println("onComplete")
    })

  }
}
