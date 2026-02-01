package lab03;

class Fraction(n: Int, d: Int) : Comparable<Fraction> {

    val num: Int
    val den: Int

    constructor(n: Int, d: Int, sign: Int) : this(n * sign, d)

    init {
        require(d != 0)
        var nn = n
        var dd = d
        if (dd < 0) {
            nn = -nn
            dd = -dd
        }
        val g = gcd(kotlin.math.abs(nn), dd)
        num = nn / g
        den = dd / g
    }

    private fun gcd(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val t = x % y
            x = y
            y = t
        }
        return if (x == 0) 1 else x
    }

    override fun toString(): String = "$num/$den"

    override fun compareTo(other: Fraction): Int {
        val left = num.toLong() * other.den.toLong()
        val right = other.num.toLong() * den.toLong()
        return left.compareTo(right)
    }

    fun negate(): Fraction = Fraction(-num, den)

    fun add(other: Fraction): Fraction =
        Fraction(num * other.den + other.num * den, den * other.den)

    operator fun plus(other: Fraction): Fraction = add(other)

    fun subtr(other: Fraction): Fraction =
        Fraction(num * other.den - other.num * den, den * other.den)

    operator fun minus(other: Fraction): Fraction = subtr(other)

    fun mult(other: Fraction): Fraction =
        Fraction(num * other.num, den * other.den)

    operator fun times(other: Fraction): Fraction = mult(other)

    fun div(other: Fraction): Fraction {
        require(other.num != 0)
        return Fraction(num * other.den, den * other.num)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraction) return false
        return num == other.num && den == other.den
    }

    override fun hashCode(): Int = 31 * num + den
}


