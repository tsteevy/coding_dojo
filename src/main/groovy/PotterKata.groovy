import java.math.RoundingMode

class PotterKata {

    def BEST_VALUE_FOR_MONEY = 4
    def COUNT_OF_ALL_EDITIONS = 5

    def priceService


    def getBookPricesWithCalculatedDiscounts(booksCurrentlyChosen){
        def differentBookSets = assembleDifferentBookSets(buildFrequencyTable(booksCurrentlyChosen))
        def totalBookPrice = 0.0
        differentBookSets.each { count ->
            totalBookPrice = totalBookPrice + priceService.getStandardPriceForSet(count) *
                    (1.0 - priceService.getDiscountForDifferentBooks(count))
        }

        return totalBookPrice.setScale(2, RoundingMode.HALF_EVEN)
    }



    def assembleDifferentBookSets(frequencyTable) {
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

    def getDifferentGroupCount(frequencyTable){
        def groupCount = 0
        frequencyTable.each { bookId, frequency ->
            if (frequency > 0){
                groupCount++
            }
        }
        return groupCount
    }

    def getTotalBookCount(frequencyTable){
        def totalCount = 0
        frequencyTable.each { k, frequency ->
            totalCount += frequency
        }
        return totalCount
    }

    def buildFrequencyTable(booksCurrentlyChosen) {
        def bookIndicesOfCollection = [0, 1, 2, 3, 4]
        def frequencyTable = [:]
        bookIndicesOfCollection.each{id ->
            frequencyTable.put(id, booksCurrentlyChosen.count(id))
        }

        return frequencyTable
    }

    static void main(String... args) {
        (0..100).each {
            println(FizzBuzzKata.getOutputForNumber(it))
        }
        println(new PotterKata().getBookPricesWithCalculatedDiscounts([0, 0, 1]))
    }
}
