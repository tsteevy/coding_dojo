import java.math.RoundingMode

class PotterKata {

    static PRICE_FOR_ONE_BOOK = 8.0
    static BEST_VALUE_FOR_MONEY = 4
    static COUNT_OF_ALL_EDITIONS = 5
    static DISCOUNT_FOR_BOOKCOUNT = [
            2: 0.05,
            3: 0.1,
            4: 0.2,
            5: 0.25
    ]

    static getBookPricesWithCalculatedDiscounts(booksCurrentlyChosen){
        def differentBookSets = assembleDifferentBookSets(buildFrequencyTable(booksCurrentlyChosen))
        def totalBookPrice = 0.0
        differentBookSets.each { count ->
            totalBookPrice = totalBookPrice + getStandardPriceForSet(count) *
                    (1.0 - getDiscountForDifferentBooks(count))
        }

        return totalBookPrice.setScale(2, RoundingMode.HALF_EVEN)
    }

    static getStandardPriceForSet(differentBookCount) {
        return differentBookCount * PRICE_FOR_ONE_BOOK
    }

    static getDiscountForDifferentBooks(differentBookCount) {
        return DISCOUNT_FOR_BOOKCOUNT.get(differentBookCount) ?: 0.0
    }

    static List<Integer> assembleDifferentBookSets(frequencyTable) {
        def remainingBookCount = getTotalBookCount(frequencyTable)
        def differentBookSets = []
        while (remainingBookCount > 0) {
            def differentBooks = 0

            frequencyTable.each { bookId, frequency ->
                if (frequency > 0 && remainingBookCount > 0) {
                    frequencyTable.put(bookId, frequency - 1)
                    remainingBookCount--
                    differentBooks++
                }

                if (remainingBookCount == BEST_VALUE_FOR_MONEY &&
                        remainingBookCount == getDifferentGroupCount(frequencyTable) &&
                        (bookId > COUNT_OF_ALL_EDITIONS - BEST_VALUE_FOR_MONEY)){
                    differentBookSets.add(remainingBookCount)
                    remainingBookCount = 0
                }
            }

            differentBookSets.add(differentBooks)
        }

        return differentBookSets
    }

    static getDifferentGroupCount(frequencyTable){
        def groupCount = 0
        frequencyTable.each { bookId, frequency ->
            if (frequency > 0){
                groupCount++
            }
        }
        return groupCount
    }

    static getTotalBookCount(frequencyTable){
        def totalCount = 0
        frequencyTable.each { k, frequency ->
            totalCount += frequency
        }
        return totalCount
    }

    static buildFrequencyTable(booksCurrentlyChosen) {
        def bookIndicesOfCollection = [0, 1, 2, 3, 4]
        def frequencyTable = [:]
        bookIndicesOfCollection.each{id ->
            frequencyTable.put(id, booksCurrentlyChosen.count(id))
        }

        return frequencyTable
    }

    static void main(String... args) {
        println(getBookPricesWithCalculatedDiscounts([0, 0, 1]))
    }
}
