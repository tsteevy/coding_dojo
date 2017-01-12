class PriceService {
    def DISCOUNT_FOR_BOOKCOUNT = [
            2: 0.05,
            3: 0.1,
            4: 0.2,
            5: 0.25
    ]

    def PRICE_FOR_ONE_BOOK = 8.0


    def getStandardPriceForSet(differentBookCount) {
        return differentBookCount * PRICE_FOR_ONE_BOOK
    }

    def getDiscountForDifferentBooks(differentBookCount) {
        return DISCOUNT_FOR_BOOKCOUNT.get(differentBookCount) ?: 0.0
    }
}
