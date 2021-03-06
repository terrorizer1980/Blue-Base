package BlueBase

/**
 * @author Ben Leggiero
 * @since 2017-09-16
 */

interface Cloneable<out Self: Cloneable<Self>> {
    fun clone(): Self
}
