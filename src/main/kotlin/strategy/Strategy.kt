package strategy

interface SortedStrategy {
    fun sort(numbers: Array<Int>)
}

class BubbleSortStrategy : SortedStrategy {
    override fun sort(numbers: Array<Int>) {
        println("Numbers are sorted using Bubble sort.")
        var sorted = false
        while (!sorted) {
            sorted = true
            for (i in 1 until numbers.size) {
                val previous = numbers[i - 1]
                val current = numbers[i]
                if (previous > current) {
                    numbers[i - 1] = numbers[i]
                    numbers[i] = previous
                    sorted = false
                }
            }
        }
        println(numbers.joinToString())
    }
}

class MergeSortStrategy : SortedStrategy {
    override fun sort(numbers: Array<Int>) {
        println("Numbers are sorted using Merge sort.")
        println(mergeSort(numbers, 0, numbers.lastIndex).joinToString())
        numbers.sort()
    }

    private fun mergeSort(array: Array<Int>, start: Int, end: Int): Array<Int> {
        if (start == end) return array
        val mid = (start + end) / 2

        mergeSort(array, start, mid)
        mergeSort(array, mid + 1, end)

        merge(array, start, mid, end)
        return array
    }

    private fun merge(array: Array<Int>, start: Int, mid: Int, end: Int) {
        val left = array.sliceArray(start..mid)
        val right = array.sliceArray(mid + 1..end)
        var i = 0
        var j = 0
        for (k in start..end) {
            if (i == left.size) {
                array[k] = right[j]
                j++
            } else if (j == right.size) {
                array[k] = left[i]
                i++
            } else {
                if (left[i] > right[j]) {
                    array[k] = right[j]
                    j++
                } else {
                    array[k] = left[i]
                    i++
                }
            }
        }
    }
}

fun main() {
    val numbers = arrayOf(10, 27, 12, 96, 25, 2, 3, 12)
    sort(BubbleSortStrategy(), numbers)
    sort(MergeSortStrategy(), numbers)
}

fun sort(sortedStrategy: SortedStrategy, numbers: Array<Int>) {
    sortedStrategy.sort(numbers)
}