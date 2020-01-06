package uk.ac.ebi.ddi.mongoimport.service

import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
import uk.ac.ebi.ddi.mongoimport.servicetrait.ConnectionTrait

object MongoConnection extends ConnectionTrait{

  val mongoClient: MongoClient = MongoClient("mongodb://ddi_user:tDzDd81J@mongos-hxvm-001.ebi.ac.uk:27017,mongos-hxvm-001.ebi.ac.uk:27017,mongos-hxvm-001.ebi.ac.uk:27017/ddi_db?authSource=admin")

  // get handle to "mydb" database
  val database: MongoDatabase = mongoClient.getDatabase("ddi_db")

  // get a handle to the "test" collection
  //val collection: MongoCollection[Document] = database.getCollection("test")

  def getDatasetCollection():MongoCollection[Document] = {
    database.getCollection("datasets.dataset")
  }

  def getDatabaseCollection():MongoCollection[Document] = {
    database.getCollection("databases")
  }
}
