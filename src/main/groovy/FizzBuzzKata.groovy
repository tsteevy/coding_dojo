/**
 * Created by intellimedis on 11.01.17.
 */
class FizzBuzzKata {

    static getOutputForNumber(number) {
        def output = ""

        if (!(isNumberFizz(number) || isNumberBuzz(number))){
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
        return (number % 3 == 0 || number.toString().contains('3'))
    }

    static isNumberBuzz(number) {
        return (number % 5 == 0 || number.toString().contains('5'))
    }
}
