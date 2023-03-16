package var3.task1

class Fraction(val numerator: Int, val denominator: Int):
  require(denominator != 0, "denominator must be non-zero")

  def output = println(s"$numerator/$denominator")

  def plus(x: Fraction): Fraction = 
    val new_num = 
      if this.denominator == x.denominator then 
        this.numerator + x.numerator 
      else (this.numerator * x.denominator) + (x.numerator * this.denominator)
    val new_denom = 
      if this.denominator == x.denominator then 
        this.denominator
      else
        this.denominator * x.denominator
    Fraction(new_num, new_denom)
  def +(x:Fraction) = plus(x)

  def minus(x: Fraction): Fraction = 
    val new_num = 
      if this.denominator == x.denominator then 
        this.numerator - x.numerator 
      else (this.numerator * x.denominator) - (x.numerator * this.denominator)
    val new_denom = 
      if this.denominator == x.denominator then 
        this.denominator
      else
        this.denominator * x.denominator
    Fraction(new_num, new_denom)
  def -(x:Fraction) = minus(x)

  def mul(x: Fraction): Fraction =
    val new_num = this.numerator * x.numerator
    val new_denom = this.denominator * x.denominator
    Fraction(new_num, new_denom)
  def *(x: Fraction) = mul(x)

  def div(x: Fraction): Fraction =
    val new_num = this.numerator * x.denominator
    val new_denom = this.denominator * x.numerator
    Fraction(new_num, new_denom)
  def /(x: Fraction) = div(x)

  // reduce fraction to its lowest terms
  private val gcd = BigInt(numerator).gcd(BigInt(denominator))
  val reducedNumerator = numerator / gcd.toInt
  val reducedDenominator = denominator / gcd.toInt

  override def equals(x: Any): Boolean = x match
    case that: Fraction => reducedDenominator == that.reducedDenominator && reducedNumerator == that.reducedNumerator
    case _ => false
  
  override def toString(): String = s"$numerator/$denominator"

  override def hashCode(): Int = 31 * (31 + reducedNumerator) + reducedDenominator

def test() =
  val a = Fraction(1, 2)
  val b = Fraction(3, 4)

  println(s"""|Fraction A: $a
              |Fraction B: $b
              |A+B=${a+b}
              |A-B=${a-b}
              |A*B=${a*b}
              |A/B=${a/b}
              |A==B: ${a==b}
              |A==2/4: ${a==Fraction(2,4)}
              |A hashCode: ${a.hashCode()}
              |A output test:""".stripMargin)
  a.output
