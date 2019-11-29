package uk.ac.ebi.ddi.mongoimport.model

case class Dataset (additional: Map[String, Set[String]],
                   dates: Map[String, Set[String]],
                   accession:String,
                   database:String,
                   scores:Scores,
                   allSecondaryAccessions:Array[String],
                   crossReferences: Map[String, Set[String]],
                   currentStatus:String,
                   filePath:String,
                   initHashCode:Int,
                   claimable:Boolean,
                   files: Map[String, Set[String]],
                   description:String,
                   name:String//,
                   //id:ObjectId
)

case class Scores()
case class Entry()

//class DatasetDAO extends SalatDAO[Dataset, ObjectId](collection= MongoConnection()("chocolate_base")("chocolate"))
