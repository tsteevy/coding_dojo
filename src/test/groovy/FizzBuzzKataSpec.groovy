import spock.lang.Specification

/**
 * Created by intellimedis on 11.01.17.
 */
class FizzBuzzKataSpec extends Specification{
    def "A number not divisible by 3 or 5 returns the number"() {
        expect: 4 == FizzBuzzKata.getOutputForNumber(4)
    }

    def "A number divisible by 3 and not 5 returns Fizz"() {
        expect: "Fizz" == FizzBuzzKata.getOutputForNumber(21)
    }

    def "A number divisible by 5 and not 3 returns Buzz"() {

    }

    def "A number divisible by 3 AND 5 returns FizzBuzz"() {

    }
}
