package sample;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.VerificationCollector;
import org.mockito.stubbing.Answer2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

    @Rule
    public VerificationCollector collector = MockitoJUnit.collector();

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

    @Test
    public void report_on_all_failed_method_verifications() throws Exception {

        mock.audience();

        verify(mock).stream();
        verify(mock).audience();
        verify(mock).maybe();
    }

    private static abstract class Abstract {

        public Abstract() {

        }
    }

    @Test
    public void spy_on() throws Exception {
        assertThat(Mockito.spy(Abstract.class), notNullValue());
    }

    private static class Builder {
        private List<String> list = new ArrayList<>();

        Builder append(boolean value) {
            list.add(Boolean.toString(value));
            return this;
        }

        Builder append(String value) {
            list.add(value);
            return this;
        }

        String build() {
            return list.stream().collect(Collectors.joining(" "));
        }
    }

    @Mock(answer = Answers.RETURNS_SELF)
    private Builder builder;

    @Test
    public void new_answer_mode_for_builders() throws Exception {
        when(builder.build()).thenReturn("replacement");

        String artifact = builder.append(true).append("second").build();

        verify(builder).append(true);
        verify(builder).append("second");
        assertThat(artifact, equalTo("replacement"));
    }

    private interface Interface{
        void one(String one);

        void two(String one, int two);

        Long oneReturingLong(int one);

        BigDecimal twoReturningBigDecimal(int one, StringBuilder builder);
    }

    @Mock
    private Interface answers;

    @Test
    public void new_strongly_typed_answers() throws Exception {
        when(answers.twoReturningBigDecimal(anyInt(), any())).thenAnswer(answer((Answer2<BigDecimal, Integer, StringBuilder>) (argument0, argument1) -> new BigDecimal(argument0)));

        assertThat(answers.twoReturningBigDecimal(7, null), equalTo(BigDecimal.valueOf(7L)));
    }
}
