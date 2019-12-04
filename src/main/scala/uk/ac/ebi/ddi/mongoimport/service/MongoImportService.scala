package uk.ac.ebi.ddi.mongoimport.service

import org.mongodb.scala._
import uk.ac.ebi.ddi.mongoimport.servicetrait.ImportServiceTrait

object MongoImportService extends ImportServiceTrait {

  val mongoClient: MongoClient = MongoClient()

  // get handle to "mydb" database
  val database: MongoDatabase = mongoClient.getDatabase("mydb")

  // get a handle to the "test" collection
  //val collection: MongoCollection[Document] = database.getCollection("test")

  def getDatasetCollection():MongoCollection[Document] = {
    database.getCollection("test")
  }

  def getDatabaseCollection():MongoCollection[Document] = {
    database.getCollection("databases")
  }


  def save(data:String, collection: MongoCollection[Document]): Unit ={
    collection.insertOne(Document.apply(data)).subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println(s"onNext: $result")
      override def onError(e: Throwable): Unit = println(s"onError: $e")
      override def onComplete(): Unit = println("onComplete")
    })
  }

}
