package adapter

interface USD {
    fun getPrice(): String
}

interface RUB {
    fun getPrice(): String
}

class PriceInUSD(private val price: Double) : USD {
    override fun getPrice() = "$$price"
}

class PriceInRUB(private val price: Double) : RUB {
    override fun getPrice() = "${price}rub."
}

class RUBToUSDAdapter(private val priceInRUB: RUB) : USD {
    override fun getPrice(): String {
        val inUSD = (priceInRUB.getPrice()).dropLast(4).toDouble() / 70
        return String.format("$%.2f", inUSD)
    }
}

fun main() {
    val priceInRUB = PriceInRUB(2000.0)
    val priceInUSD = RUBToUSDAdapter(priceInRUB)
    println(priceInUSD.getPrice())
}
