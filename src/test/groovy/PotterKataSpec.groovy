import spock.lang.*

class PotterKataSpec extends Specification{

    def priceService
    def potterKata

    def setup(){
        potterKata = new PotterKata()
        potterKata.priceService = Mock(PriceService)
    }

    def "Empty shopping basket does not cost anything"() {
        given: def booksInBasket = []
        when: def totalCost = potterKata.getBookPricesWithCalculatedDiscounts(booksInBasket)
        then: totalCost == 0
    }

    def "Adding multiple books is represented accordingly in a frequency table"() {
        given: def booksInBasket = [1, 1, 1, 1]
        when: def frequencyTable = potterKata.buildFrequencyTable(booksInBasket)
        then: frequencyTable.get(1) == 4
    }

    def "Three books without discount cost 24 EUR"() {
        given:
        priceService = new PriceService()
        expect: 24 == priceService.getStandardPriceForSet(3)
    }

    def "One book of each is exactly one collection containing 5 books"() {
        expect: [5] == potterKata.assembleDifferentBookSets([0: 1,
                                                             1: 1,
                                                             2: 1,
                                                             3: 1,
                                                             4: 1])
    }

    def "With one collection of 3 and one collection of 4 books, the price is 51.20 EUR"() {
        potterKata.priceService = Mock(PriceService)
        potterKata.priceService.getStandardPriceForSet(3) >> 3 * 8
        potterKata.priceService.getStandardPriceForSet(4) >> 4 * 8
        potterKata.priceService.getDiscountForDifferentBooks(3) >> 0.10
        potterKata.priceService.getDiscountForDifferentBooks(4) >> 0.20
        expect: 51.20 == potterKata.getBookPricesWithCalculatedDiscounts([0, 0, 1, 1, 2, 2, 3, 4])
    }

    def "Asking for a discount for books which do not exists returns a discount of 0"() {
        given:
        priceService = new PriceService()
        expect: 0 == priceService.getDiscountForDifferentBooks(17)
    }

    def "Discount for one single book equals 0"() {
        given:
        priceService = new PriceService()
        expect: 0 == priceService.getDiscountForDifferentBooks(1)
    }

    def "Two sets of 3 are combined as sets of 3 as well" () {
        given: def frequencyOfBooks = [0: 2, 1: 2, 2: 2]
        when: def assembledSets = potterKata.assembleDifferentBookSets(frequencyOfBooks)
        assembledSets.sort()
        then: [3, 3] == assembledSets
    }

    def "Two books of the same edition and one other result in a set of one and one set of two books" () {
        given: def frequencyOfBooks = [0: 2, 1: 1]
        when: def assembledSets = potterKata.assembleDifferentBookSets(frequencyOfBooks)
        assembledSets.sort()
        then: [1, 2] == assembledSets
    }

    def "Two sets of 3 is equal to 6 books in total"() {
        given: def frequencyOfBooks = [0: 2, 1: 2, 2: 2]
        when: def totalCount = potterKata.getTotalBookCount(frequencyOfBooks)
        then: 6 == totalCount
    }


}
