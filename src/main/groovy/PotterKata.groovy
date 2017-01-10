class PotterKata {

    static PRICE_FOR_ONE_BOOK = 8

    static getBookPricesWithCalculatedDiscounts(List<Integer> booksCurrentlyChosen){
        def differentBookSets = assembleDifferentBooksets(buildFrequencyTable(booksCurrentlyChosen))
        def totalBookPrice = 0
        differentBookSets.each {count ->
            totalBookPrice += getStandardPriceForSet(count) * (1.0 - getDiscountForDifferentBooks(count))
        }

        return totalBookPrice
    }

    static float getStandardPriceForSet(Integer differentBookCount) {
        return differentBookCount * PRICE_FOR_ONE_BOOK
    }

    static float getDiscountForDifferentBooks(int differentBookCount){
        switch (differentBookCount){
            case 2:
                return 0.05
            case 3:
                return 0.1
            case 4:
                return 0.2
            case 5:
                return 0.25
        }
        return 0.0
    }

    static List<Integer> assembleDifferentBooksets(frequencyTable){
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

                if (remainingBookCount == 4 && remainingBookCount == getDifferentGroupCount(frequencyTable)){
                    differentBookSets.add(remainingBookCount)
                    remainingBookCount = 0
                }
            }

            differentBookSets.add(differentBooks)
        }

        return differentBookSets
    }

    static Integer getDifferentGroupCount(frequencyTable){
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

    static buildFrequencyTable(List<Integer> booksCurrentlyChosen) {
        def bookIndicesOfCollection = [0, 1, 2, 3, 4]
        def frequencyTable = [:]
        bookIndicesOfCollection.each{id ->
            frequencyTable.put(id, booksCurrentlyChosen.count(id))
        }

        return frequencyTable
    }

    static void main(String... args) {
        println(getBookPricesWithCalculatedDiscounts([0, 0, 1, 1, 2, 2, 3, 4]))
    }
}
