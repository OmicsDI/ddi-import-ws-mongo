package uk.ac.ebi.ddi.mongoimport.model

/*information about all databases to retrieve datasets */
case class Database(repository:String,
orcidName:String, urlTemplate:String, accessionPrefix:Array[String],
title:String, imgAlt:String, sourceUrl:String, image:Array[String],
source:String, description:String, domain:String, icon:String, databaseName:String)
