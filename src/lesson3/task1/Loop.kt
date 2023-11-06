@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var digit: Int = n
    var k: Int = 0
    return if (digit == 0) {
        1
    } else {
        while (digit != 0) {
            digit /= 10
            k++
        }
        k
    }
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fibN: Int = 1
    var fibN1: Int = 1
    var fibN2: Int = 0
    if (n in 1..2) {
        return 1
    }
    for (n in 3..n) {
        fibN2 = fibN + fibN1
        fibN = fibN1
        fibN1 = fibN2
    }
    return fibN2
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var numM = m
    var numN = n
    val multiplication = (numM * numN)
    while ((numM != 0) && (numN != 0)) {
        if (numM > numN) numM %= numN
        else numN %= numM
    }
    val count = numM + numN
    return multiplication / count
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var k: Int = 2
    while (n % k != 0) {
        k++
    }
    return k
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var k: Int = n - 1
    while (n % k != 0) {
        k--
    }
    return k
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var del: Int = 2
    var count: Int = 1
    val max: Int = if (m >= n) {
        m
    } else n

    while (count == 1 && del < max) {
        if (m % del == 0 && n % del == 0) {
            count++
        }
        del++
    }
    return count == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val left: Int = sqrt(m.toDouble()).toInt()
    val right: Int = sqrt(n.toDouble()).toInt()
    for (i in left..right) {
        if (sqr(i) in m..n) {
            return true
        }
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var digit: Int = x
    var steps: Int = 0
    while (digit != 1) {
        if (digit % 2 == 0) {
            digit /= 2
            steps++
        } else {
            digit = 3 * digit + 1
            steps++
        }
    }
    return steps
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var number = 1
    var digit = 3
    val xx = x % (2 * Math.PI)
    val absEps = abs(eps)
    var sin = xx
    var currentExp = xx
    var part = xx
    while (abs(part) >= absEps) {
        currentExp *= xx * xx
        if (number % 2 != 0) part = -1 * currentExp / factorial(digit)
        else part = currentExp / factorial(digit)
        sin += part
        number += 1
        digit += 2
    }
    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var cos = 1.0
    var part: Double
    var number = 1
    var digit = 2
    val xx = x % (2 * Math.PI)
    val absEps = abs(eps)
    var currentExp = 1.0
    do {
        currentExp *= xx * xx
        if (number % 2 != 0) part = -1 * currentExp / factorial(digit)
        else part = currentExp / factorial(digit)
        cos += part
        number += 1
        digit += 2
    } while (abs(part) >= absEps)
    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var revert = 0
    var temp = n
    if (n < 0) {
        temp *= -1
    }
    while (temp > 0) {
        revert = revert * 10 + temp % 10
        temp /= 10
    }
    return if (n < 0)
        revert * -1
    else revert
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n) && n >= 0

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val lastDigit: Int = n % 10
    var digit: Int = (n - lastDigit) / 10
    if (n in -9..9) {
        return false
    }
    if (n < 0) {
        return true
    }
    if (digitNumber(n) == 2) {
        return lastDigit != digit
    } else {
        var penultimateDigit: Int = digit % 10
        digit = (digit - penultimateDigit) / 10
        while (digit != 0) {
            if (lastDigit != penultimateDigit) {
                return true
            }
            penultimateDigit = digit % 10
            digit = (digit - penultimateDigit) / 10
        }
    }
    return false
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var digit = 0
    var number = 1
    var square = 1
    while (digit != n) {
        square = number * number
        digit += digitNumber(square)
        while (n < digit) {
            square /= 10
            digit--
        }
        number++
    }
    return square % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var digit = 0
    var number = 1
    var fib = 1
    while (n != digit) {
        fib = fib(number)
        digit += digitNumber(fib)
        while (n < digit) {
            fib /= 10
            digit--
        }
        number++
    }
    return fib % 10
}