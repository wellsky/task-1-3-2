fun main() {
    println(calculateCommission("Vk Pay", 1000_00, 100000_00))
    println(calculateCommission("Visa", 1000_00, 100000_00))
    println(calculateCommission("Mastercard", 1000_00, 100000_00))
}

fun calculateCommission(cardType: String = "Vk Pay", summ: Int, currentMonthSumm: Int = 0): Int {
    var commission: Double = 0.0

    when {
        (cardType == "Vk Pay") -> {
           // Ничего не меняем
        }

        ((cardType == "Visa") || (cardType == "Мир")) -> {
            val commissionSize = 0.75 // В процентах
            val minimumCommission = 35_00.0 // 35 руб. в копейках

            commission = summ * commissionSize / 100 // Размер комиссии
            if (commission < minimumCommission) commission = minimumCommission; // Если комиссия меньше минимума
        }

        ((cardType == "Mastercard") || (cardType == "Maestro")) -> {
            val minFreeSumm = 300_00.0 // Минимальная сумма перевода без комиссии
            val maxMonthFree = 75000_00.0 // Максимум в месяц без комиссии

            if ((summ < minFreeSumm) || ((currentMonthSumm + summ) > maxMonthFree)) {
                val commissionSize = 0.60  // В процентах
                val additionalCommission = 20_00.0 // 20 руб. в копейках

                commission = summ * commissionSize / 100 + additionalCommission// Размер комиссии
            }
        }

        else -> {
            println("Неизвестный типа платежа")
        }
    }

    if (commission > summ) {
        commission = summ.toDouble()
    }

    return commission.toInt()
}