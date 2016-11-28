package sample._2;

import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockBuilderTest {

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

    @Test
    public void new_answer_mode_for_builders() throws Exception {
        Builder builder = mock(Builder.class/*, Answers.RETURNS_SELF*/);
        when(builder.build()).thenReturn("replacement");

        String artifact = builder.append(true).append("second").build();

        verify(builder).append(true);
        verify(builder).append("second");
        assertThat(artifact, equalTo("replacement"));
    }

}
