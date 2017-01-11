import spock.lang.Specification

class FizzBuzzKataSpec extends Specification{
    def "A number not divisible by 3 or 5 returns the number"() {
        expect: 4 == FizzBuzzKata.getOutputForNumber(4)
    }

    def "A number divisible by 3 and not 5 returns Fizz"() {
        expect: "Fizz" == FizzBuzzKata.getOutputForNumber(21)
    }

    def "A number divisible by 5 and not 3 returns Buzz"() {
        expect: "Buzz" == FizzBuzzKata.getOutputForNumber(25)
    }

    def "A number divisible by 3 AND 5 returns FizzBuzz"() {
        expect: "FizzBuzz" == FizzBuzzKata.getOutputForNumber(15)
    }

    def "If a number contains a 3 return Fizz"() {
        expect: "Fizz" == FizzBuzzKata.getOutputForNumber(32)
    }

    def "If a number contains a 5 return Buzz"() {
        expect: "Buzz" == FizzBuzzKata.getOutputForNumber(52)
    }

    def "If a number contains a 3 and 5 return FizzBuzz"() {
        expect: "FizzBuzz" == FizzBuzzKata.getOutputForNumber(53)
    }
}
