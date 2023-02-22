package bmstu.general

import scala.util.chaining._

// a pipe operator from OCaml PL
extension [A,B](a:A)
  infix def |>(f: A => B): B = a.pipe(f)