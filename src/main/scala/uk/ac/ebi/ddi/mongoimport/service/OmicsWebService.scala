package uk.ac.ebi.ddi.mongoimport.service

import net.liftweb.json.Serialization.write
import net.liftweb.json._
import sttp.client._
import uk.ac.ebi.ddi.mongoimport.model.{Database, DatasetPage}
import uk.ac.ebi.ddi.mongoimport.servicetrait.WSTrait
import sttp.model.Uri


class OmicsWebService extends WSTrait{

  def getOmicsDataset(): Unit ={
      for(i <- 0 to getDatasetPageCount()){
      insertPageDataset(i)
    }
  }

  def getDatasetPageCount(start:Int = 0, size:Int = 20): Int ={
    val data = getJsonDataset(start, size)
    data.totalPages
  }

  def insertPageDataset(start:Int=0, size: Int=20): Unit ={
    val data = getJsonDataset(start, size)
    println(data.content(0).accession)
    implicit val formats = DefaultFormats
    for( i <- 0 to size -1 ){
      MongoImportService.save(write(data.content(i)))
    }
  }

  def getJsonDataset(start:Int, size:Int ): DatasetPage ={
    getResponse[DatasetPage](uri"https://www.omicsdi.org:443/ws/dataset/getDatasetPage?start=$start&size=$size")
    /*val request = basicRequest.get(
      uri"https://www.omicsdi.org:443/ws/dataset/getDatasetPage?start=$start&size=$size")

    implicit val backend = HttpURLConnectionBackend()
    val response = request.send()

    implicit val formats = DefaultFormats
    val json = parse(response.body.getOrElse("{}"))
    val data = json.extract[DatasetPage]
    data*/
  }

  def getJsonDatabase(start:Int, size:Int ): Database ={
    getResponse[Database](uri"https://www.omicsdi.org:443/ws/database/all")
    /*val request = basicRequest.get(
      uri"https://www.omicsdi.org:443/ws/database/all")

    implicit val backend = HttpURLConnectionBackend()
    val response = request.send()

    implicit val formats = DefaultFormats
    val json = parse(response.body.getOrElse("{}"))
    val data = json.extract[Database]
    data*/
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
    new OmicsWebService().getOmicsDataset()
  }
}
