package uk.ac.ebi.ddi.mongoimport.model


case class DatasetPage(totalElements:Int, totalPages:Int, numberOfElements:Int,
sort: Sort, first: Boolean, last: Boolean, size: Int, content: Array[Dataset], number: Int)

case class DatasetPageTest(totalElements:Int, totalPages:Int, numberOfElements:Int,
                        first: Boolean, last: Boolean, size: Int, number: Int)

case class Sort()

/*object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val datasetFormat = jsonFormat4(DatasetPageTest)
}*/


//class DatasetDAO extends SalatDAO[Dataset, ObjectId](collection= MongoConnection()("chocolate_base")("chocolate"))
