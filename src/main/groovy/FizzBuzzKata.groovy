/**
 * Created by intellimedis on 11.01.17.
 */
class FizzBuzzKata {

    static getOutputForNumber(number) {
        def output = ""

        if (!(number % 3 == 0 || number % 5 == 0)){
            output = number
        }

        if (isNumberFizz(number)) {
            output = "Fizz"
        }

        if (isNumberBuzz(number)) {
            output += "Buzz"
        }

        return output
    }

    static isNumberFizz(number) {
        return (number % 3 == 0)
    }

    static isNumberBuzz(number) {
        return (number % 5 == 0)
    }
}
