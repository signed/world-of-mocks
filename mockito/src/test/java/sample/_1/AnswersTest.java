package sample._1;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnswersTest {

    @SuppressWarnings("unchecked")
    private final List<String> list = mock(List.class);

    @Test
    public void answer_depending_on_the_argument_passed() throws Exception {
        when(list.add(anyString())).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                invocation.getArgumentAt(0, String.class);
                return true;
            }
        });
    }
}
