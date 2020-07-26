@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import javax.print.attribute.IntegerSyntax
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum: Double = 0.0
    if (v.isNotEmpty()) {
        for (element in v) {
            sum += sqr(element)
        }
    }
    return sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var sum: Double = 0.0
    return if (list.isNotEmpty()) {
        for (element in list) {
            sum += element
        }
        sum / list.size
    } else {
        0.0
    }
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    var sum: Double = 0.0
    return if (list.isNotEmpty()) {
        for (element in list) {
            sum += element
        }
        for (i in list.indices) {
            list[i] = list[i] - (sum / list.size)
        }
        list
    } else {
        list
    }
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var C: Int = 0
    return if (a.isEmpty() && b.isEmpty()) {
        0
    } else {
        for (i in a.indices) {
            C += a[i] * b[i]
        }
        C
    }
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var polynom: Int = 0
    if (p.isEmpty()) {
        return 0
    } else if (p.size == 1) {
        return p[0]
    } else {
        for (i in p.indices) {
            polynom += p[i] * x.toDouble().pow(i.toDouble()).toInt()
        }
        return polynom
    }
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum: Int = 0
    return if (list.isEmpty()) {
        list
    } else {
        for (i in list.size - 1 downTo 1) {
            for (i in i downTo 0) {
                sum += list[i]
            }
            list[i] = sum
            sum = 0
        }
        list
    }
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var del: Int = 2
    var temp: Int = n
    for (i in del..n) {
        if (temp % del == 0) {
            result.add(del)
            temp /= del
            del = 2
        }
        del++
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val result = factorize(n)
    var str: String = ""
    for (i in 0..result.size - 2) {
        str += result[i].toString() + "*"
    }
    return str + result[result.size - 1].toString()
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    return if (n == 0) listOf(0)
    else {
        var temp = n
        var result = listOf<Int>()
        while (temp > 0) {
            result += temp % base
            temp /= base
        }
        result.reversed()
    }
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var str: String = ""
    val result = convert(n, base)
    val alphabet = StringBuilder("abcdefghijklmnopqrstuvwxyz")
    for (i in result.indices) {
        if (result[i] >= 10) {
            str += alphabet[result[i] - 10]
        } else {
            str += result[i].toString()
        }
    }
    return str
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var digit: Int = 0
    for (i in digits.indices) {
        digit += digits[i] * Math.pow(base.toDouble(), (digits.size - 1 - i).toDouble()).toInt()
    }
    return digit
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val list = mutableListOf<Int>()
    val alphabet = StringBuilder("abcdefghijklmnopqrstuvwxyz")
    for (i in str.indices) {
        if (str[i].toInt() in 48..57) {
            list.add(Integer.parseInt(str[i].toString()))
        } else {
            for (j in alphabet.indices) {
                if (str[i] == alphabet[j]) {
                    list.add(j + 10)
                }
            }
        }
    }
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val romanDigitList = listOf<Int>(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    val romanAlpList = listOf<String>("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    var str: String = ""
    var temp: Int = n
    while (temp != 0) {
        if (temp >= 1000) {
            str += romanAlpList[romanAlpList.size - 1]
            temp -= romanDigitList[romanDigitList.size - 1]
        } else {
            for (j in romanDigitList.indices) {
                if (temp <= romanDigitList[j]) {
                    if (temp == romanDigitList[j]) {
                        str += romanAlpList[j]
                        temp -= romanDigitList[j]
                        break
                    } else {
                        str += romanAlpList[j - 1]
                        temp -= romanDigitList[j - 1]
                        break
                    }
                }
            }
        }
    }
    return str
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val words = mapOf<Int, String>(
        1 to "один",
        2 to "два",
        3 to "три",
        4 to "четыре",
        5 to "пять",
        6 to "шесть",
        7 to "семь",
        8 to "восемь",
        9 to "девять",
        10 to "десять",
        11 to "одиннадцать",
        12 to "двенадцать",
        13 to "тринадцать",
        14 to "четырнадцать",
        15 to "пятнадцать",
        16 to "шестнадцать",
        17 to "семнадцать",
        18 to "восемнадцать",
        19 to "девятнадцать",
        20 to "двадцать",
        30 to "тридцать",
        40 to "сорок",
        50 to "пятьдесят",
        60 to "шестьдесят",
        70 to "семьдесят",
        80 to "восемьдесят",
        90 to "девяносто",
        100 to "сто",
        200 to "двести",
        300 to "триста",
        400 to "четыреста",
        500 to "пятьсот",
        600 to "шестьсот",
        700 to "семьсот",
        800 to "восемьсот",
        900 to "девятьсот"
    )
    val resultPart2 = mutableListOf<String>()
    val resultPart1 = mutableListOf<String>()
    var nPart1 = n / 1000
    var nPart2 = n % 1000
    var i = 10
    while (nPart2 > 0) {
        if (nPart2 % i == 0) {
            i *= 10
            continue
        }
        if ((i == 10) && (nPart2 % 100 in 11..19)) i = 100
        resultPart2.add(0, words[nPart2 % i]!!)
        nPart2 -= nPart2 % i
    }
    if (nPart1 > 0) {
        i = 10
        while (nPart1 > 0) {
            if (nPart1 % i == 0) {
                i *= 10
                continue
            }
            if ((i == 10) && (nPart1 % 100 in 11..19)) i = 100
            resultPart1.add(0, words[nPart1 % i]!!)
            nPart1 -= nPart1 % i
        }
        nPart1 = n / 1000
        when {
            (nPart1 % 10 == 1) && (nPart1 % 100 != 11) -> {
                resultPart1.removeAt(resultPart1.size - 1)
                resultPart1.add("одна тысяча")
            }
            (nPart1 % 10 == 2) && (nPart1 % 100 != 12) -> {
                resultPart1.removeAt(resultPart1.size - 1)
                resultPart1.add("две тысячи")
            }
            (nPart1 % 10 == 3) && (nPart1 % 100 != 13) -> {
                resultPart1.removeAt(resultPart1.size - 1)
                resultPart1.add("три тысячи")
            }
            (nPart1 % 10 == 4) && (nPart1 % 100 != 14) -> {
                resultPart1.removeAt(resultPart1.size - 1)
                resultPart1.add("четыре тысячи")
            }
            else -> {
                resultPart1.add("тысяч")
            }
        }
    }
    return (resultPart1 + resultPart2).joinToString(separator = " ")
}
