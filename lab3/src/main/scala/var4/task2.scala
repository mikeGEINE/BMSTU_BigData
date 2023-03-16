package var4.task2

import var2.task2.*

import scala.collection.mutable

class Reader(val name: String) {
  private var _blacklisted: Boolean = false
  def blacklisted: Boolean = _blacklisted
  def blacklist(): Unit = _blacklisted = true
}

class Order(val reader: Reader, val book: Book)

class Library(val catalog: BookList) {
  private val _orders: mutable.Set[Order] = mutable.Set.empty
  private val _lentBooks: mutable.Map[Book, Reader] = mutable.Map.empty

  def orderBook(reader: Reader, book: Book): Unit = {
    if (catalog.lst.contains(book)) {
      _orders.addOne(new Order(reader, book))
      println(s"${reader.name} has ordered ${book.title}")
    } else {
      println(s"Sorry, ${book.title} is not in our catalog")
    }
  }

  def lendBook(book: Book, reader: Reader): Unit = {
    if (_lentBooks.contains(book)) {
      println(s"Sorry, ${book.title} is already lent to ${_lentBooks(book).name}")
    } else {
      val order = _orders.find(_.book == book)
      order match {
        case Some(o) =>
          if (o.reader == reader) {
            _lentBooks.put(book, reader)
            _orders.remove(o)
            println(s"${book.title} has been lent to ${reader.name}")
          } else {
            println(s"Sorry, ${book.title} has been ordered by ${o.reader.name}")
          }
        case None => println(s"Sorry, ${book.title} has not been ordered")
      }
    }
  }

  def returnBook(book: Book, reader: Reader): Unit = {
    if (_lentBooks.contains(book) && _lentBooks(book) == reader) {
      _lentBooks.remove(book)
      println(s"${book.title} has been returned by ${reader.name}")
    } else {
      println(s"${reader.name} did not borrow ${book.title}")
    }
  }

  def blacklistReader(reader: Reader): Unit = {
    reader.blacklist()
    println(s"${reader.name} has been blacklisted")
  }
}

def test() =
  // create some books
  val books = randBookList(10)

  // create a library
  val library= new Library(books)

  // create some readers
  val reader1 = new Reader("John Doe")
  val reader2 = new Reader("Jane Smith")

  // reader 1 places an order for a book
  library.orderBook(reader1, books(3))

  // reader 2 places an order for a book
  library.orderBook(reader2, books(3))

  library.orderBook(reader2, randBook())

  library.lendBook(books(3),reader1)
  library.lendBook(books(3),reader2)
  library.lendBook(books(4),reader2)

  // reader 1 returns the book
  library.returnBook(books(3), reader1)
  library.returnBook(books(3), reader2)

  library.blacklistReader(reader2)
