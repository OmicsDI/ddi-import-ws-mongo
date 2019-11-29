package uk.ac.ebi.ddi.mongoimport.model

case class DatasetPage(totalElements:Int, totalPages:Int, numberOfElements:Int,
sort: Sort, first: Boolean, last: Boolean, size: Int, content: Array[Dataset], number: Int)

case class Sort()

