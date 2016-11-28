package sample;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer1;
import org.mockito.stubbing.Answer2;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

    private static abstract class Abstract {

        public Abstract() {

        }
    }

    @Test
    public void spy_on() throws Exception {
        assertThat(Mockito.spy(Abstract.class), notNullValue());
    }

    @Test
    public void describe_what_just_went_wrong() throws Exception {
        Runnable runnable = mock(Runnable.class);

        verify(runnable, description("kernel panic - this just ended the world")).run();
    }

    @Test
    public void experimental_support_for_stubbing_final_classes() throws Exception {
        assertThat(mock(StringBuilder.class), notNullValue());
    }
}
