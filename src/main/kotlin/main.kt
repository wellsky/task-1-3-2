fun main() {
    println(calculateCommission("Vk Pay", 1000_00, 100000_00))
    println(calculateCommission("Visa", 1000_00, 100000_00))
    println(calculateCommission("Mastercard", 1000_00, 100000_00))
}

fun calculateCommission(cardType: String = "Vk Pay", summ: Int, currentMonthSumm: Int = 0): Int {
    var commission: Double = when (cardType) {
        "Vk Pay" -> 0.0
        "Visa", "Мир" -> calculateVisaCommission(summ, currentMonthSumm)
        "Mastercard", "Maestro" -> calculateMaestroCommission(summ, currentMonthSumm)
        else -> {
            // Выбросить Exception, но мы их не проходили
            println("Неизвестный типа платежа")
            0.0
        }
    }

    if (commission > summ) {
        commission = summ.toDouble()
    }

    return commission.toInt()
}

fun calculateVisaCommission(summ: Int, currentMonthSumm: Int = 0):Double {
    val commissionSize = 0.75 // В процентах
    val minimumCommission = 35_00.0 // 35 руб. в копейках

    var commission = summ * commissionSize / 100 // Размер комиссии
    if (commission < minimumCommission) commission = minimumCommission; // Если комиссия меньше минимум

    return commission
}

fun calculateMaestroCommission(summ: Int, currentMonthSumm: Int = 0):Double {
    val minFreeSumm = 300_00.0 // Минимальная сумма перевода без комиссии
    val maxMonthFree = 75000_00.0 // Максимум в месяц без комиссии
    var commission = 0.0

    if ((summ < minFreeSumm) || ((currentMonthSumm + summ) > maxMonthFree)) {
        val commissionSize = 0.60  // В процентах
        val additionalCommission = 20_00.0 // 20 руб. в копейках

        commission = summ * commissionSize / 100 + additionalCommission// Размер комиссии
    }

    return commission
}