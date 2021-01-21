package uk.ac.ebi.ddi.mongoimport.model

import org.bson.types.ObjectId

/*information about all databases to retrieve datasets */
case class Database(var _id:Option[String], repository:String,
orcidName:String, urlTemplate:String, accessionPrefix:Array[String],
title:String, imgAlt:String, sourceUrl:String, image:Array[String],
source:String, description:String, domain:String, icon:String, databaseName:String)

case class Similars(_class:String, accession: String, database: String, similars: List[DatasetRef])

case class DatasetRef(relationType: String , similarDataset: SimilarDataset)

case class SimilarDataset($ref: String, $id: ObjectId)
