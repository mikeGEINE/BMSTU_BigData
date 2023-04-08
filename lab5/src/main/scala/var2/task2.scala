package var2.task2

val rand = scala.util.Random

import scala.util.{Try, Success, Failure}
import java.io.{FileNotFoundException, IOException, PrintWriter}

class InvalidAuthorsException(msg: String) extends Exception(msg)

class Book(
  private var _id: Int,
  private var _title: String,
  private var _authors: List[String],
  private var _publisher: String,
  private var _year: Int,
  private var _pages: Int,
  private var _price: Int,
  private var _cover: String
):
  require(_authors.length>=1, "There must be at least 1 author of a book!")

  def id = _id
  def id_=(id:Int) = _id = id

  def title = _title
  def title_=(title:String) = _title = title

  def authors = _authors
  def authors_=(authors:List[String]) = 
    if(_authors.length>=1)
      throw new InvalidAuthorsException("There must be at least 1 author of a book!")
    _authors = authors

  def publisher = _publisher
  def publisher_=(publisher:String) = _publisher = publisher

  def year = _year
  def year_=(year:Int) = _year = year

  def pages = _pages
  def pages_=(pages:Int) = _pages = pages

  def price = _price
  def price_=(price:Int) = _price = price

  def cover = _cover
  def cover_=(id:String) = _cover = cover

  override def toString(): String = s"""|Book №$id: \'$title\'
                                        |By ${authors.mkString(", ")}
                                        |Pub: $publisher, $year
                                        |Pages: $pages; Cover: $cover
                                        |Price: $price""".stripMargin

def randBook(id: Int = rand.nextInt(1000), 
            authors: List[String] = List(rand.alphanumeric.take(rand.nextInt(15)).mkString),
            publisher: String = rand.alphanumeric.take(rand.nextInt(15)).mkString) =
  Book(
    _id = id,
    _title = rand.alphanumeric.take(rand.nextInt(20)).mkString,
    _authors = authors,
    _publisher = publisher,
    _year = rand.between(1500,2023),
    _pages = rand.between(10, 1500),
    _price = rand.between(10, 50000),
    _cover = if rand.nextInt(20)>9 then "Soft" else "Hard"
  )

// implicit def listOfBooksToBookList(l: List[Book]):BookList = new BookList(l)

class BookList(val lst: List[Book]):
  def byAuthor(name: String) =
    BookList(lst.filter(book => book.authors.contains(name)))

  def byPub(pub: String) =
    BookList(lst.filter(book => book.publisher.equals(pub)))

  def publishedAfter(year: Int) =
    BookList(lst.filter(_.year > year))

  override def toString: String = lst.mkString("\n------\n")

  def apply(i: Int) = lst.apply(i)

object BookList: 
  def readFromFile(filename: String): Try[BookList] = 
    Try {
      val source = scala.io.Source.fromFile(filename)
      val lines = source.getLines().toList
      source.close()
      val books = lines.map(line =>
        val fields = line.split(";")
        val id = fields(0).toInt
        val title = fields(1)
        val publisher = fields(2)
        val year = fields(3).toInt
        val pages = fields(4).toInt
        val price = fields(5).toInt
        val cover = fields(6)
        val authors = fields.drop(7).toList
        Book(id, title, authors, publisher, year, pages, price, cover))
      new BookList(books)
    }.recoverWith {
      case e: FileNotFoundException => Failure(new IOException("File not found", e))
      case e: IOException => Failure(new IOException("Error reading file", e))
      case e: Exception => Failure(e)
    }

  def writeToFile(filename: String, books: BookList): Try[Unit] = 
    Try {
      val writer = new PrintWriter(filename)
      for (book <- books.lst) {
        val line = List(
          book.id.toString,
          book.title,
          book.publisher,
          book.year.toString,
          book.pages.toString,
          book.price.toString,
          book.cover
        ).mkString(";") + ";" + book.authors.mkString(";")
        writer.println(line)
      }
      writer.close()
    }.recoverWith {
      case e: IOException => Failure(new IOException("Error writing file", e))
      case e: Exception => Failure(e)
    }

def randBookList(n: Int = 5, 
  auth_pool: List[String] = List.fill(5){rand.alphanumeric.take(rand.nextInt(15)).mkString}, 
  pub_pool: List[String] = List.fill(2){rand.alphanumeric.take(rand.nextInt(15)).mkString}) 
  =
  BookList(List.tabulate(n){i => randBook(id = i,
                    authors = List.fill(rand.between(1, auth_pool.length)){auth_pool(rand.nextInt(auth_pool.length))},
                    publisher = pub_pool(rand.nextInt(pub_pool.length)))})


def test() = 
  val auth_pool = List.fill(5){rand.alphanumeric.take(rand.nextInt(15)).mkString}
  val pub_pool = List.fill(2){rand.alphanumeric.take(rand.nextInt(15)).mkString}
  val lst:BookList = randBookList(7, auth_pool = auth_pool, pub_pool = pub_pool)
  println(s"""|Books: 
              |$lst
              |~~~~~~~
              |Books by ${auth_pool(2)}:
              |${lst.byAuthor(auth_pool(2))}
              |~~~~~~~
              |Published by ${pub_pool(0)}:
              |${lst.byPub(pub_pool(0))}
              |~~~~~~~
              |Published after 1800:
              |${lst.publishedAfter(1800)}""".stripMargin)