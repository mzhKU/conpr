import java.util.concurrent.Executors

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.io.{Codec, Source}
import scala.util.{Failure, Success}

import scala.concurrent.duration._

object MyFuture extends App {
    
    implicit val executionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(2))
    
    def loadURL(url: String): String = {
        Source.fromURL(url)(Codec.UTF8).getLines().mkString
    }
    
    def contentGrouped(content: String): Map[String, Array[String]] = {
        content.split("\\W").groupBy(x => x)
    }
    
    def indexContent(content: String): Map[String, Int] = {
        content.split("\\W").groupBy(x => x).view.mapValues(_.size).toMap
    }
    
    val futureContent: Future[String]                     = Future.apply(loadURL("https://www.20min.ch"))
    val futureGroup:   Future[Map[String, Array[String]]] = futureContent.map(contentGrouped)
    val futureIndex:   Future[Map[String, Int]] = futureContent.map(indexContent)
    
    futureIndex.onComplete({
        case Success(m) => println(m)
        case Failure(t) => println("Fail: " + t)
    })
    
    val result = Await.result(futureIndex, 20.seconds)
    
}