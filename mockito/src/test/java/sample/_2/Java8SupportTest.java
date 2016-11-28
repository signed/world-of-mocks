package sample._2;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class Java8SupportTest {

    private interface Java8 {
        default String hardCodedDefault() {
            return "Hello " + audience();
        }

        String audience();

        Optional<String> maybe();

        Stream<String> stream();
    }

    private final Java8 java8 = Mockito.mock(Java8.class);

    @Test
    public void support_mocking_of_default_implementations_in_interfaces() throws Exception {
        when(java8.audience()).thenReturn("world");
        when(java8.hardCodedDefault()).thenCallRealMethod();

        assertThat(java8.hardCodedDefault(), equalTo("Hello world"));
    }

    @Test
    public void return_default_for_optional() throws Exception {
        assertThat("expected to be empty ", !java8.maybe().isPresent());
    }

    @Test
    public void return_default_for_stream() throws Exception {
        assertThat(java8.stream().count(), equalTo(0L));
    }
}
