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

        return output
    }

    static isNumberFizz(number) {
        return (number % 3 == 0)
    }
}
