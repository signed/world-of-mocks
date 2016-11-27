package sample;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@Ignore
public class TwoDotXTest {

    private interface InterfaceWithDefaultMethod {
        default String hardCodedDefault() {
            return "Hello " + audience();
        }

        String audience();

        Optional<String> maybe();

        Stream<String> stream();
    }

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private InterfaceWithDefaultMethod mock;

    @Test
    public void support_mocking_of_default_implementations_in_interfaces() throws Exception {
        when(mock.audience()).thenReturn("world");
        when(mock.hardCodedDefault()).thenCallRealMethod();

        assertThat(mock.hardCodedDefault(), equalTo("Hello world"));
    }

    @Test
    public void return_default_for_optional() throws Exception {
        assertThat("expected to be empty ", !mock.maybe().isPresent());
    }

    @Test
    public void return_default_for_stream() throws Exception {
        assertThat(mock.stream().count(), equalTo(0L));
    }

    @Test
    public void hint_on_unused_stubbing() throws Exception {
        when(mock.audience()).thenReturn("something");
    }
}
