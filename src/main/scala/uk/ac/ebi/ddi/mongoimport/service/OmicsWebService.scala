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

  def getPageCount(): Int ={
    val request = basicRequest.get(
      uri"https://www.omicsdi.org:443/ws/dataset/getDatasetPage?start=0&size=1")
    implicit val backend = HttpURLConnectionBackend()

    val response = request.send()
    implicit val formats = DefaultFormats
    val json = parse(response.body.getOrElse("{}"))
    val data = json.extract[DatasetPage]
    data.totalPages
  }

  def insertPageDataset(start:Int=0, size: Int=20): Unit ={

    val request = basicRequest.get(
      uri"https://www.omicsdi.org:443/ws/dataset/getDatasetPage?start=$start&size=$size")

    implicit val backend = HttpURLConnectionBackend()
    val response = request.send()

    implicit val formats = DefaultFormats
    val json = parse(response.body.getOrElse("{}"))
    val data = json.extract[DatasetPage]
    println(data.content(0).accession)
    for( i <- 0 to size -1 ){
      MongoImportService.save(write(data.content(i)))
    }
  }
}

object OmicsWebService{

  def main(args:Array[String]): Unit ={
  new OmicsWebService().getOmicsDataset()
}
}
