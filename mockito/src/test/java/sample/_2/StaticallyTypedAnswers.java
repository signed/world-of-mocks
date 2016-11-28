package sample._2;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.stubbing.Answer1;
import org.mockito.stubbing.Answer2;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StaticallyTypedAnswers {

    private interface Interface{
        void one(String one);

        void two(String one, int two);

        Long oneReturningLong(int one);

        BigDecimal twoReturningBigDecimal(int one, StringBuilder builder);
    }

    private Interface answers = mock(Interface.class);

    @Test
    public void new_strongly_typed_answers() throws Exception {
        when(answers.oneReturningLong(anyInt())).thenAnswer(AdditionalAnswers.answer((Answer1<Long, Integer>) argument0 -> 42L + argument0));
        assertThat(answers.oneReturningLong(5), equalTo(47L));

        when(answers.twoReturningBigDecimal(anyInt(), any())).thenAnswer(AdditionalAnswers.answer((Answer2<BigDecimal, Integer, StringBuilder>) (argument0, argument1) -> new BigDecimal(argument0)));
        assertThat(answers.twoReturningBigDecimal(7, null), equalTo(BigDecimal.valueOf(7L)));

        doAnswer(AdditionalAnswers.answerVoid((String arg) -> {throw new RuntimeException(arg);})).when(answers).one("message");
        try{
            answers.one("message");
            Assert.fail("expected exceptions");
        }catch (RuntimeException ex){
            assertThat(ex.getMessage(), equalTo("message"));
        }
    }

}
