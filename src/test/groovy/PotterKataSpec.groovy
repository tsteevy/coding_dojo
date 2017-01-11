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

    def "Three books without discount cost 24 Dollars"() {
        expect: 24 == PotterKata.getStandardPriceForSet(3)
    }
}
