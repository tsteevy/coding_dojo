class PotterKataTest extends GroovyTestCase {

    static def testBasics() {
        assert 0 == PotterKata.getBookPricesWithCalculatedDiscounts([])
        assert 8 == PotterKata.getBookPricesWithCalculatedDiscounts([0])
        assert 8 == PotterKata.getBookPricesWithCalculatedDiscounts([1])
        assert 8 == PotterKata.getBookPricesWithCalculatedDiscounts([2])
        assert 8 == PotterKata.getBookPricesWithCalculatedDiscounts([3])
        assert 8 == PotterKata.getBookPricesWithCalculatedDiscounts([4])
        assert 8 * 2 == PotterKata.getBookPricesWithCalculatedDiscounts([0, 0])
        assert 8 * 3 == PotterKata.getBookPricesWithCalculatedDiscounts([1, 1, 1])
    }

    static def testSimpleDiscounts() {
        assert 8 * 2 * 0.95 == PotterKata.getBookPricesWithCalculatedDiscounts([0, 1])
        assert 8 * 3 * 0.9 == PotterKata.getBookPricesWithCalculatedDiscounts([0, 2, 4])
        assert 8 * 4 * 0.8 == PotterKata.getBookPricesWithCalculatedDiscounts([0, 1, 2, 4])
        assert 8 * 5 * 0.75 == PotterKata.getBookPricesWithCalculatedDiscounts([0, 1, 2, 3, 4])
    }

    static testSeveralDiscounts() {
        assert 8 + (8 * 2 * 0.95) == PotterKata.getBookPricesWithCalculatedDiscounts([0, 0, 1])
        assert 2 * (8 * 2 * 0.95) == PotterKata.getBookPricesWithCalculatedDiscounts([0, 0, 1, 1])
        assert (8 * 4 * 0.8) + (8 * 2 * 0.95) == PotterKata.getBookPricesWithCalculatedDiscounts([0, 0, 1, 2, 2, 3])
        assert 8 + (8 * 5 * 0.75) == PotterKata.getBookPricesWithCalculatedDiscounts([0, 1, 1, 2, 3, 4])
    }

    static testEdgeCases() {
        assert 2 * (8 * 4 * 0.8) == PotterKata.getBookPricesWithCalculatedDiscounts([0, 0, 1, 1, 2, 2, 3, 4])
        assert 3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8) == PotterKata.getBookPricesWithCalculatedDiscounts([0, 0, 0, 0, 0,
                1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4])
    }

}
