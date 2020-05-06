package uk.ac.ebi.ddi.mongoimport.service

import net.liftweb.json.Serialization.write
import net.liftweb.json._
import sttp.client._
import uk.ac.ebi.ddi.mongoimport.model.{Database, DatasetPage}
import uk.ac.ebi.ddi.mongoimport.servicetrait.WSTrait
import sttp.model.Uri


class OmicsWebService extends WSTrait{
  implicit val formats = DefaultFormats

  def saveOmicsDataset(): Unit ={
      val pageData = getJsonDatasetByDB(0, 20, "Pride")
      for(i <- 0 to getDatasetPageCount(0, 20, pageData)){
      insertPageDataset(i, 20, pageData)
    }
  }

  def saveOmicsDatabase(): Unit ={
    val db = getJsonDatabase()
    for(i <- 0 to db.length -1 )
      MongoImportService.save(write(db(i)), MongoConnection.getDatabaseCollection())
  }

  //getJsonDataset(start, size)
  def getDatasetPageCount(start:Int = 0, size:Int = 20, page:DatasetPage): Int ={
    val data = page
    data.totalPages
  }

  def insertPageDataset(start:Int=0, size: Int=20, data: DatasetPage): Unit ={
    println(data.content(0).accession)
    for( i <- 0 to size -1 ){
      data.content(i).database = "PrideTest"
      MongoImportService.save(write(data.content(i)),MongoConnection.getDatasetCollection())
    }
  }

  def getJsonDataset(start:Int, size:Int ): DatasetPage ={
    getResponse[DatasetPage](uri"https://www.omicsdi.org:443/ws/dataset/getDatasetPage?start=$start&size=$size")
  }

  def getJsonDatabase(): Array[Database] ={
    getResponse[Array[Database]](uri"https://www.omicsdi.org:443/ws/database/all")
  }

  def getJsonDatasetByDB(start:Int, size:Int, database: String ): DatasetPage ={
    getResponse[DatasetPage](uri"http://localhost:8080/dataset/getDatasetByDB?database=$database&start=$start&size=$size")
  }

  def getResponse[T: Manifest](url:Uri): T ={
    val request = basicRequest.get(
      url)
    implicit val backend = HttpURLConnectionBackend()
    val response = request.send()
    implicit val formats = DefaultFormats
    val json = parse(response.body.getOrElse("{}"))
    val data = json.extract[T]
    data
  }
}

object OmicsWebService{

  def main(args:Array[String]): Unit ={
    new OmicsWebService().saveOmicsDataset()
    //new OmicsWebService().saveOmicsDatabase()
  }
}
