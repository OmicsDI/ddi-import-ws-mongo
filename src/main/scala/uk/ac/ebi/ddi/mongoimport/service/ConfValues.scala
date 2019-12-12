package uk.ac.ebi.ddi.mongoimport.service

import com.typesafe.config.{Config, ConfigFactory}

object ConfValues {

  def getConfiguration(): Config ={
    val conf: Config = ConfigFactory.load()
    conf
  }

}
