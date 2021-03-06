package BlueBase


import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


/**
 * @author Ben Leggiero
 * @since 2017-08-07
 */
class Number_StringificationKtTest {



    @Test
    fun Float64_toString() {
        fun toString_0FractionDigits(receiver: Float64) = receiver.toString(fractionDigits = 0)
        fun toString_1FractionDigits(receiver: Float64) = receiver.toString(fractionDigits = 1)
        fun toString_2FractionDigits(receiver: Float64) = receiver.toString(fractionDigits = 2)
        fun toString_3FractionDigits(receiver: Float64) = receiver.toString(fractionDigits = 3)


        val zeroFractionDigits = expect("Float64.toString(fractionDigits = 0)", ::toString_0FractionDigits, listOf(
                Assertion.valid(0.0, "0"),
                Assertion.valid(123.456, "123"),
                Assertion.valid(123.45, "123"),
                Assertion.valid(123.4, "123"),
                Assertion.valid(123.5, "124")
        ))
        assertTrue(zeroFractionDigits.wasSuccessful, zeroFractionDigits.message)

        val oneFractionDigit = expect("Float64.toString(fractionDigits = 1)", ::toString_1FractionDigits, listOf(
                Assertion.valid(0.0, "0.0"),
                Assertion.valid(123.456, "123.5"),
                Assertion.valid(123.45, "123.5"),
                Assertion.valid(123.4, "123.4"),
                Assertion.valid(123.5, "123.5")
        ))
        assertTrue(oneFractionDigit.wasSuccessful, oneFractionDigit.message)

        val twoFractionDigits = expect("Float64.toString(fractionDigits = 2)", ::toString_2FractionDigits, listOf(
                Assertion.valid(0.0, "0.00"),
                Assertion.valid(123.456, "123.46"),
                Assertion.valid(123.45, "123.45"),
                Assertion.valid(123.4, "123.40"),
                Assertion.valid(123.5, "123.50")
        ))
        assertTrue(twoFractionDigits.wasSuccessful, twoFractionDigits.message)

        val threeFractionDigits = expect("Float64.toString(fractionDigits = 3)", ::toString_3FractionDigits, listOf(
                Assertion.valid(0.0, "0.000"),
                Assertion.valid(123.456, "123.456"),
                Assertion.valid(123.45, "123.450"),
                Assertion.valid(123.4, "123.400"),
                Assertion.valid(123.5, "123.500")
        ))
        assertTrue(threeFractionDigits.wasSuccessful, threeFractionDigits.message)
    }


    @Test
    fun Int64_toString() {
        fun toString_comma_groupSize3(receiver: Int64): String = receiver.toString(separator = ",", groupSize = 3)
        fun toString_dot_groupSize3(receiver: Int64): String = receiver.toString(separator = ".", groupSize = 3)
        fun toString_comma_groupSize4(receiver: Int64): String = receiver.toString(separator = ",", groupSize = 4)


        val comma_groupSize3 = expect("Int64.toString(separator = \",\", groupSize = 3)", ::toString_comma_groupSize3, listOf(
                Assertion.valid(0L, "0"),
                Assertion.valid(123456L, "123,456"),
                Assertion.valid(12345L, "12,345"),
                Assertion.valid(1234L, "1,234"),
                Assertion.valid(123L, "123")
        ))
        assertTrue(comma_groupSize3.wasSuccessful, comma_groupSize3.message)

        val dot_groupSize3 = expect("Int64.toString(separator = \".\", groupSize = 3)", ::toString_dot_groupSize3, listOf(
                Assertion.valid(0L, "0"),
                Assertion.valid(123456L, "123.456"),
                Assertion.valid(12345L, "12.345"),
                Assertion.valid(1234L, "1.234"),
                Assertion.valid(123L, "123")
        ))
        assertTrue(dot_groupSize3.wasSuccessful, dot_groupSize3.message)

        val comma_groupSize4 = expect("Int64.toString(separator = \",\", groupSize = 4)", ::toString_comma_groupSize4, listOf(
                Assertion.valid(0L, "0"),
                Assertion.valid(123456L, "12,3456"),
                Assertion.valid(12345L, "1,2345"),
                Assertion.valid(1234L, "1234"),
                Assertion.valid(123L, "123")
        ))
        assertTrue(comma_groupSize4.wasSuccessful, comma_groupSize4.message)
    }


    @Test
    fun Int32_toString() {
        fun toString_comma_groupSize3(receiver: Int32): String = receiver.toString(separator = ",", groupSize = 3)
        fun toString_dot_groupSize3(receiver: Int32): String = receiver.toString(separator = ".", groupSize = 3)
        fun toString_comma_groupSize4(receiver: Int32): String = receiver.toString(separator = ",", groupSize = 4)


        val comma_groupSize3 = expect("Int32.toString(separator = \",\", groupSize = 3)", ::toString_comma_groupSize3, listOf(
                Assertion.valid(0, "0"),
                Assertion.valid(123456, "123,456"),
                Assertion.valid(12345, "12,345"),
                Assertion.valid(1234, "1,234"),
                Assertion.valid(123, "123")
        ))
        assertTrue(comma_groupSize3.wasSuccessful, comma_groupSize3.message)

        val dot_groupSize3 = expect("Int32.toString(separator = \".\", groupSize = 3)", ::toString_dot_groupSize3, listOf(
                Assertion.valid(0, "0"),
                Assertion.valid(123456, "123.456"),
                Assertion.valid(12345, "12.345"),
                Assertion.valid(1234, "1.234"),
                Assertion.valid(123, "123")
        ))
        assertTrue(dot_groupSize3.wasSuccessful, dot_groupSize3.message)

        val comma_groupSize4 = expect("Int32.toString(separator = \",\", groupSize = 4)", ::toString_comma_groupSize4, listOf(
                Assertion.valid(0, "0"),
                Assertion.valid(123456, "12,3456"),
                Assertion.valid(12345, "1,2345"),
                Assertion.valid(1234, "1234"),
                Assertion.valid(123, "123")
        ))
        assertTrue(comma_groupSize4.wasSuccessful, comma_groupSize4.message)
    }
}
