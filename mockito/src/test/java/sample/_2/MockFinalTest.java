package sample._2;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockFinalTest {

    private static final class Final {
    }

    @Test
    public void experimental_support_for_stubbing_final_classes() throws Exception {
        assertThat(mock(Final.class), notNullValue());
    }

    private static class WithFinalMethod {
        public final String echo(String input) {
            return input;
        }
    }

    @Test
    public void experimental_support_for_stubbing_final_methods() throws Exception {
        WithFinalMethod mock = Mockito.mock(WithFinalMethod.class);
        when(mock.echo(anyString())).thenReturn("give feedback");

        assertThat(mock.echo("argument"), equalTo("give feedback"));
    }
}
