@file:Suppress("unused")

package org.bh.tools.base.math

import org.bh.tools.base.abstraction.*
import org.bh.tools.base.math.RoundingDirection.*
import org.bh.tools.base.math.RoundingThreshold.halfway
import org.bh.tools.base.math.RoundingThreshold.integer
import java.lang.Math.floor
import java.math.*
import java.math.RoundingMode.*

/*
 * For using fractions easier
 *
 * @author Kyli Rouge
 * @since 2017-01-27.
 */

// TODO: data class FractionNumeratorAndDenominator(val numerator: Integer, val denominator: Integer)
// TODO: val Fraction.numeratorAndDenominator: FractionNumeratorAndDenominator by lazy { ... }


/** Determines whether this [Fraction] has any values after the radix point */
val Fraction.hasFractionComponent: Boolean get() {
    return this != floor(this)
}


/**
 * Finds and returns the values before and after the radix point (integer and fractional part)
 */
val Fraction.components: RadixNumberParts get() {
    val iPart = integerValue
    val fPart = this - iPart
    return RadixNumberParts(iPart, fPart)
}



/**
 * Represents a fractional number's integer and fraction parts
 *
 * See also: https://en.wikipedia.org/wiki/Radix_point
 */
data class RadixNumberParts(
        /** The part of a number before the radix point */
        val integerPart: Integer,
        /** The part of a number after the radix point */
        val fractionPart: Fraction)


/**
 * Returns the rounded version of this fraction.
 * If `this` `isNaN()` or `isInfinity()`, it's returned immediately without changing the value.
 *
 * @param direction The direction to round
 * @param threshold      Describes at what part of the number the rounding should occur
 *
 * @return This number rounded, or unchanged if it's already an integer, infinite, or Not a Number.
 */
fun Fraction.rounded(direction: RoundingDirection = RoundingDirection.default,
                     threshold: RoundingThreshold = RoundingThreshold.default): Fraction =
        if (isNaN() || isInfinite() || !hasFractionComponent) this
        else BigDecimal(this, MathContext.DECIMAL64).setScale(0, RoundingMode(this, direction, threshold)).fractionValue


inline val Fraction.roundedInt8Value: Int8 get() = rounded().int8Value
inline val Fraction.roundedInt16Value: Int16 get() = rounded().int16Value
inline val Fraction.roundedInt32Value: Int32 get() = rounded().int32Value
inline val Fraction.roundedInt64Value: Int64 get() = rounded().int64Value
inline val Fraction.roundedIntegerValue: Integer get() = roundedInt64Value


/**
 * Finds the [java.math.RoundingMode] appropriate the given [Fraction], [RoundingDirection], and [RoundingThreshold]
 *
 * @param fraction The fraction for which the rounding mode will be used
 * @param direction _optional_ - The direction in which rounding will occur.
 *                  Defaults to [default][RoundingDirection.default]
 * @param threshold _optional_ - The part of the number that will trigger a different result.
 *                  Defaults to [default][RoundingThreshold.default]
 */
fun RoundingMode(fraction: Fraction,
                 direction: RoundingDirection = RoundingDirection.default,
                 threshold: RoundingThreshold = RoundingThreshold.default
): RoundingMode = when (threshold) {
    halfway -> when (direction) {
        up -> if (fraction > 0) HALF_UP else HALF_DOWN
        down -> if (fraction > 0) HALF_DOWN else HALF_UP
        awayFromZero -> HALF_UP
        towardZero -> HALF_DOWN
    }
    integer -> when (direction) {
        up -> CEILING
        down -> FLOOR
        awayFromZero -> if (fraction > 0) CEILING else FLOOR
        towardZero -> if (fraction > 0) FLOOR else CEILING
    }
}



/**
 * Describes at what part of the number rounding should occur
 */
enum class RoundingThreshold {
    /**
     * Rounding is triggered at the halfway mark. `x.0 - x.4999...` is treated differently than `x.5000...1 - x.999...`.
     * To specify the behavior of `x.5`, see [RoundingDirection].
     */
    halfway,

    /**
     * Rounding is triggered at the integer mark. `x.0 - x.4999...` is treated the same as `x.5 - x.999...`.
     */
    integer;

    companion object {
        /**
         * The default rounding threshold: [halfway]
         */
        val default = halfway
    }
}



/** Represents the direction to round a fraction if it is not already an integer */
enum class RoundingDirection {
    /**
     * If the number is not already an integer, increase its value to the next-highest integer
     */
    up,

    /**
     * If the number is not already an integer, decrease its value to the next-lowest integer
     */
    down,

    /**
     * If the number is not already an integer, increase its value to the next-highest integer if it is positive, else
     * decrease its value to the next-lowest integer if it is negative
     */
    awayFromZero,

    /**
     * If the number is not already an integer, decrease its value to the next-lowest integer if it is positive, else
     * increase its value to the next-highest integer if it is negative
     */
    towardZero;

    companion object {
        /**
         * The default rounding direction: [away from zero][awayFromZero]
         */
        val default = awayFromZero
    }
}



/** Returns `true` iff this is a native fraction and [isNaN()][java.lang.Double.isNaN] */
inline val Number.isNaN: Boolean get() = isNativeFraction && fractionValue.isNaN()

/** Returns `true` iff this is a native fraction and [isInfinite()][Double.isInfinite()] */
inline val Number.isInfinite: Boolean get() = isNativeFraction && fractionValue.isInfinite()

/** Returns `true` iff this float [isInfinite()] */
inline val Float32.isInfinite: Boolean get() = isInfinite()

/** Returns `true` iff this double [isInfinite()] */
inline val Float64.isInfinite: Boolean get() = isInfinite()

/** Returns `true` iff [isInfinite] and is less than `0.0` */
inline val Number.isNegativeInfinity: Boolean get() = isInfinite && fractionValue < 0.0

/** Returns `true` iff [isInfinite] and is greater than `0.0` */
inline val Number.isPositiveInfinity: Boolean get() = isInfinite && fractionValue > 0.0

/**
 * ∞ as an IEEE 32-bit float
 *
 * @see Float.POSITIVE_INFINITY
 */
inline val Float.Companion.infinity get() = POSITIVE_INFINITY

/**
 * ∞ as an IEEE 64-bit float
 *
 * @see Double.POSITIVE_INFINITY
 */
inline val Double.Companion.infinity get() = POSITIVE_INFINITY

/**
 * Not-A-Number as an IEEE 32-bit float
 *
 * @see Float.NaN
 */
inline val Float.Companion.nan get() = NaN

/**
 * Not-A-Number as an IEEE 64-bit float
 *
 * @see Double.NaN
 */
inline val Double.Companion.nan get() = NaN