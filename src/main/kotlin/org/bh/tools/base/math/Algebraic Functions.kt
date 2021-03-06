@file:Suppress("unused")

package BlueBase

/*
 * Assists in doing algebra
 *
 * @author Ben Leggiero
 * @since 2017-03-20
 */


/**
 * Finds the greatest common divisor of [a] and [b]. The methodology changes depending on the size of the numbers.
 *
 * To explicitly specify the methodology used, use [greatestCommonDivisor_euclid] or [greatestCommonDivisor_binaryMethod]
 */
fun greatestCommonDivisor(a: Integer, b: Integer) =
        when (abs(max(a, b))) {
            in (0..100_000) -> greatestCommonDivisor_euclid(a, b)
            else -> greatestCommonDivisor_binaryMethod(a, b)
        }


/**
 * Finds the greatest common divisor of [a] and [b] using [Euclid's Algorithm](https://en.wikipedia.org/wiki/Greatest_common_divisor#Using_Euclid.27s_algorithm)
 */
fun greatestCommonDivisor_euclid(a: Integer, b: Integer): Integer =
        when (b) {
            0L -> a
            else -> greatestCommonDivisor_euclid(b, a % b)
        }


/**
 * Finds the greatest common divisor of [a] and [b] using the [binary method](https://en.wikipedia.org/wiki/Greatest_common_divisor#Binary_method)
 */
fun greatestCommonDivisor_binaryMethod(a: Integer, b: Integer): Integer {
    var aVar: Integer = a
    var bVar: Integer = b

    var d: Integer = 0
    while (aVar.isEven and bVar.isEven) {
        aVar /= 2
        bVar /= 2
        d += 1
    }
    while (aVar != bVar) {
        if (aVar.isEven) {
            aVar /= 2
        } else if (bVar.isEven) {
            bVar /= 2
        } else if (aVar > bVar) {
            aVar = (aVar - bVar)/2
        } else {
            bVar = (bVar - aVar)/2
        }
    }
    val g = aVar
    return g * 2L.toThePowerOf(d)
}


/**
 * Finds the greatest common divisor of `this` and [other]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Integer.greatestCommonDivisorWith(other: Integer) = greatestCommonDivisor(this, other)


/**
 * Indicates whether the given two integers are [coprime](https://en.wikipedia.org/wiki/Coprime).
 */
@Suppress("NOTHING_TO_INLINE")
inline fun isCoprime(a: Integer, b: Integer): Boolean = greatestCommonDivisor(a, b) == 1L


/**
 * Indicates whether this and the given integer are [coprime](https://en.wikipedia.org/wiki/Coprime).
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Integer.isCoprimeWith(other: Integer): Boolean = isCoprime(this, other)


/**
 * Finds the number of digits in this integer, not including a `-` sign
 */
@JvmOverloads
fun Int64.numberOfDigits(radix: Int = 10): Int8 = when (this) {
    Int64.min -> 19
    else -> absoluteValue.toString(radix).length.int8Value
}


/**
 * Finds the number of digits in this integer, not including a `-` sign
 */
@JvmOverloads
fun Int32.numberOfDigits(radix: Int = 10): Int8 = when (this) {
    Int32.min -> 10
    else -> absoluteValue.toString(radix).length.int8Value
}


/**
 * Finds the number of digits in this integer, not including a `-` sign
 */
@JvmOverloads
fun Int16.numberOfDigits(radix: Int = 10): Int8 = when (this) {
    Int16.min -> 5
    else -> absoluteValue.toString(radix).length.int8Value
}


/**
 * Finds the number of digits in this integer, not including a `-` sign
 */
@JvmOverloads
fun Int8.numberOfDigits(radix: Int = 10): Int8 = when (this) {
    Int8.min -> 3
    else -> absoluteValue.toString(radix).length.int8Value
}


/**
 * Indicates whether this is a power of 10. That is, 1 one followed by 0 or more zeroes.
 * This does not care about negatives.
 */
val Integer.isPowerOf10: Boolean get() =
        when (abs(this)) {
            1L, 10L, 100L, 1_000L, 10_000L, 100_000L, 1_000_000L, 10_000_000L, 100_000_000L, 1_000_000_000L,
            10_000_000_000, 100_000_000_000, 1_000_000_000_000, 10_000_000_000_000, 100_000_000_000_000,
            1_000_000_000_000_000, 10_000_000_000_000_000, 100_000_000_000_000_000, 1_000_000_000_000_000_000 -> true
            else -> false
        }

