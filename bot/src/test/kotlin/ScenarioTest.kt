import bot.MyBotTest
import com.justai.jaicf.test.ScenarioTest
import com.justai.lessons.scenario.testScenario
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class MyScenarioTest : MyBotTest() {

    @BeforeEach
    fun setContext() {
        withBotContext {
            session["flag"] = true
        }
    }

    @Test
    fun `should activate state Start by query start`() {
        query("/start") goesToState "/Start"
    }

    @Test
    fun `should end with state Order by query start`() {
        query("/start") endsWithState "/Order"
    }

    @Test
    fun `should go to Agree from Order by intent yes`() {
        withCurrentState("/Order")
        intent("yes") goesToState "/Order/Agree"
    }

    @Test
    fun `should end with Agree from Order by intent yes`() {
        withCurrentState("/Order")
        intent("yes") endsWithState "/Order/Agree"
    }

    @Test
    fun `should go to Disagree from Order by intent no`() {
        withCurrentState("/Order")
        intent("no") goesToState "/Order/Disagree"
    }

    @Test
    fun `should end with Disagree from Order by intent no`() {
        withCurrentState("/Order")
        intent("no") endsWithState "/Order/Disagree"
    }

    @Test
    fun `should activate agree from Order by query yes`() {
        withCurrentState("/Order")
        query("да") goesToState "/Order/Agree"
        query("согласен") goesToState "/Order/Agree"
    }

    @Test
    fun `check scenario`() {
        withCurrentState("/Order")
        intent("check") endsWithState ("/Order/Check/byTrue")

/*        withBotContext {
            session["flag"] = false
        }
        intent("check") endsWithState ("/Order/Check/byFalse")*/
    }
}

