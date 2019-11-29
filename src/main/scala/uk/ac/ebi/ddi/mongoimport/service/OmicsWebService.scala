package uk.ac.ebi.ddi.mongoimport.service

import net.liftweb.json.Serialization.write
import net.liftweb.json._
import sttp.client._
import uk.ac.ebi.ddi.mongoimport.model.DatasetPage
import uk.ac.ebi.ddi.mongoimport.servicetrait.WSTrait


class OmicsWebService extends WSTrait{

  def getOmicsDataset(): Unit ={
      for(i <- 0 to getPageCount()){
      insertPageDataset(i)
    }
  }

  def getPageCount(start:Int = 0, size:Int = 20): Int ={
    val data = getJsonData(start, size)
    data.totalPages
  }

  def insertPageDataset(start:Int=0, size: Int=20): Unit ={
    val data = getJsonData(start, size)
    println(data.content(0).accession)
    implicit val formats = DefaultFormats
    for( i <- 0 to size -1 ){
      MongoImportService.save(write(data.content(i)))
    }
  }

  def getJsonData(start:Int, size:Int ): DatasetPage ={
    val request = basicRequest.get(
      uri"https://www.omicsdi.org:443/ws/dataset/getDatasetPage?start=$start&size=$size")

    implicit val backend = HttpURLConnectionBackend()
    val response = request.send()

    implicit val formats = DefaultFormats
    val json = parse(response.body.getOrElse("{}"))
    val data = json.extract[DatasetPage]
    data
  }
}

object OmicsWebService{

  def main(args:Array[String]): Unit ={
  new OmicsWebService().getOmicsDataset()
}
}
