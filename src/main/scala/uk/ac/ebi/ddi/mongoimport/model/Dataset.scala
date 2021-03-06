package uk.ac.ebi.ddi.mongoimport.model

case class Dataset (var _class:Option[String] = Some("uk.ac.ebi.ddi.service.db.model.dataset.Dataset"),
                     additional: Map[String, Set[String]],
                   dates: Map[String, Set[String]],
                   accession:String, var database:String,
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

