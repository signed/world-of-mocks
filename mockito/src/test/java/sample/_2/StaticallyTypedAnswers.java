package sample._2;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

    private interface Interface {
        Long oneReturningLong(int argument);
    }

    private Interface answers = mock(Interface.class);

    @Test
    public void new_strongly_typed_answers() throws Exception {
        Answer<Long> answer = invocation -> 42L + (int) invocation.getArgument(0);
        //answer = AdditionalAnswers.answer((Answer1<Long, Integer>) argument -> 42L + argument);
        when(answers.oneReturningLong(anyInt())).thenAnswer(answer);

        assertThat(answers.oneReturningLong(5), equalTo(47L));
    }

}
