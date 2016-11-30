package sample._1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JunitRunner {

    @Mock
    private Function<String, String> function;

    @Test
    public void over_specified_test() throws Exception {
        when(function.apply("banana")).thenReturn("bread");
        when(function.apply("sweet")).thenReturn("chilly");

        assertThat(function.apply("sweet"), equalTo("chilly"));
    }
}
