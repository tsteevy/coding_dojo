class PotterKata {

    static PRICE_FOR_ONE_BOOK = 8.0f

    static price (List<Integer> shoppingCartContents){
        shoppingCartContents.sort()
        def differentBookSets = assembleSets(buildFrequencyTable(shoppingCartContents))

        def totalPrice = 0
        differentBookSets.each {count ->
            totalPrice += getPriceForSet(count) * (1.0f - discountForSet(count))
        }

        return totalPrice
    }

    static float getPriceForSet(Integer differentBookCount) {
        return differentBookCount * PRICE_FOR_ONE_BOOK
    }

    static float discountForSet(int differentBookCount){
        switch (differentBookCount){
            case 2:
                return 0.05f
            case 3:
                return 0.1f
            case 4:
                return 0.2f
            case 5:
                return 0.25f

        }
        return 0.0f
    }

    static List<Integer> assembleSets(frequencyTable){
        def stillAtLeastOneBook = true
        def differentBookSets = []
        while (stillAtLeastOneBook) {
            stillAtLeastOneBook = false
            def differentBooks = 0

            frequencyTable.each { bookId, frequency ->
                if (frequency > 0) {
                    stillAtLeastOneBook = true
                    frequencyTable.put(bookId, frequency - 1)
                    differentBooks++
                }
            }

            if (differentBooks > 0) {
                differentBookSets.add(differentBooks)
            }
        }

        return differentBookSets
    }

    static buildFrequencyTable(List<Integer> shoppingCartContents) {
        def fullCollection = [0, 1, 2, 3, 4]
        def frequencyTable = [:]
        fullCollection.each{id ->
            frequencyTable.put(id, shoppingCartContents.count(id))
        }

        return frequencyTable
    }

    static void main(String... args) {
        println(price([0, 1, 1, 2, 3, 4]))
    }
}
