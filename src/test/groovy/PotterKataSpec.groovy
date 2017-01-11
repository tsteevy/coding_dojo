import spock.lang.*

class PotterKataSpec extends Specification{

    def "Empty shopping basket does not cost anything"() {
        given: def booksInBasket = []
        when: def totalCost = PotterKata.getBookPricesWithCalculatedDiscounts(booksInBasket)
        then: totalCost == 0
    }

    def "Adding multiple books is represented accordingly in a frequency table"() {
        given: def booksInBasket = [1, 1, 1, 1]
        when: def frequencyTable = PotterKata.buildFrequencyTable(booksInBasket)
        then: frequencyTable.get(1) == 4
    }

    def "Three books without discount cost 24 EUR"() {
        expect: 24 == PotterKata.getStandardPriceForSet(3)
    }

    def "One book of each is exactly one collection containing 5 books"() {
        expect: [5] == PotterKata.assembleDifferentBookSets([0: 1,
                                                             1: 1,
                                                             2: 1,
                                                             3: 1,
                                                             4: 1])
    }

    def "With one collection of 3 and one collection of 4 books, the price is 51.20 EUR"() {
        expect: 51.20 == PotterKata.getBookPricesWithCalculatedDiscounts([0, 0, 1, 1, 2, 2, 3, 4])
    }

    def "Asking for a discount for books which do not exists returns a discount of 0"() {
        expect: 0 == PotterKata.getDiscountForDifferentBooks(17)
    }

    def "Discount for one single book equals 0"() {
        expect: 0 == PotterKata.getDiscountForDifferentBooks(1)
    }

    def "Two sets of 3 are combined as sets of 3 as well" () {
        given: def frequencyOfBooks = [0: 2, 1: 2, 2: 2]
        when: def assembledSets = PotterKata.assembleDifferentBookSets(frequencyOfBooks)
        assembledSets.sort()
        then: [3, 3] == assembledSets
    }

    def "Two books of the same edition and one other result in a set of one and one set of two books" () {
        given: def frequencyOfBooks = [0: 2, 1: 1]
        when: def assembledSets = PotterKata.assembleDifferentBookSets(frequencyOfBooks)
        assembledSets.sort()
        then: [1, 2] == assembledSets
    }

    def "Two sets of 3 is equal to 6 books in total"() {
        given: def frequencyOfBooks = [0: 2, 1: 2, 2: 2]
        when: def totalCount = PotterKata.getTotalBookCount(frequencyOfBooks)
        then: 6 == totalCount
    }


}
