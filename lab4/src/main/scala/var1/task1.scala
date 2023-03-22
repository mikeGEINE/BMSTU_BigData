package var1.task1

import com.github.nscala_time.time.Imports._

class ArtExhibition {
  // Internal class for storing painting information
  class Painting(val title: String, val artist: String, val startExhibition: DateTime, val endExhibition: DateTime):
    override def toString(): String = 
      s"""|Painting '$title', by $artist.
      |Exhibited from ${startExhibition.toString("YYYY-MM-dd")} till ${endExhibition.toString("YYYY-MM-dd")}""".stripMargin

  // Collection to store paintings
  private var paintings = List[Painting]()

  // Method for adding a painting to the collection
  def newPainting(title: String, artist: String, startExhibition: DateTime, endExhibition: DateTime): Painting = {
    val painting = new Painting(title, artist, startExhibition, endExhibition)
    paintings = painting :: paintings
    painting
  }

  // Method for retrieving all paintings in the collection
  def getPaintings(): List[Painting] = paintings

  override def toString(): String = paintings.mkString("\n------\n")
}

def test() =
  val exhibition = new ArtExhibition()

  def newPaintingInDates(title: String, artist: String, exhibition: ArtExhibition = exhibition) =
    val start = DateTime.parse("2023-03-21")
    val end = start + 2.months
    exhibition.newPainting(title, artist, start, end)

  newPaintingInDates("Diana Bathing","Jean-Baptiste Camille Corot")
  newPaintingInDates("Blue Dancers", "Edgar Degas")
  newPaintingInDates("White Water Lilies", "Claude Monet")

  val msg =
    s"""|Currently exibited:
        |$exhibition""".stripMargin
  
  println(msg)