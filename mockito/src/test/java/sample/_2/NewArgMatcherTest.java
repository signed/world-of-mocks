package sample._2;

import org.junit.Test;
import org.mockito.Mockito;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class NewArgMatcherTest {

    private interface Sample {
        String call(Iterable<String> parameters);
    }

    @Test
    public void can_match_iterable() throws Exception {
        Sample sample = mock(Sample.class);
        //Mockito.when(sample.call(any())).thenReturn("any");
        //Mockito.when(sample.call(anyIterable())).thenReturn("match");

        assertThat(sample.call(null), equalTo("any"));
        assertThat(sample.call(asList("one", "two")), equalTo("match"));
    }
}
