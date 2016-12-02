package sample._1;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchersTest {

    @SuppressWarnings("unchecked")
    private final List<String> listOfString = mock(List.class);

    @SuppressWarnings("unchecked")
    private final List<Object> listOfObject = mock(List.class);

    @Test
    public void hamcrest_matcher_for_arguments() throws Exception {
        when(listOfString.add(Mockito.argThat(equalTo("example")))).thenReturn(true);
        //when(listOfString.add(Mockito.argThat("example"::equals))).thenReturn(true);

        assertThat("Should not match", !listOfString.add("unknown"));
        assertThat("Should match", listOfString.add("example"));
    }

    @Test
    public void any_matcher() throws Exception {
        when(listOfObject.add(Mockito.any())).thenReturn(true);

        assertThat("should match", listOfObject.add(null));
        assertThat("should match", listOfObject.add("a string"));
        assertThat("should match", listOfObject.add(45));
    }

    @Test
    public void any_string_matcher() throws Exception {
        when(listOfObject.add(anyString())).thenReturn(true);

        assertThat("should match", listOfObject.add(null));
        assertThat("should match", listOfObject.add("any string"));
        assertThat("should match", listOfObject.add(34));
    }

    @Test
    public void any_type_matcher() throws Exception {
        when(listOfObject.add(Mockito.anyInt())).thenReturn(true);

        assertThat("should match", listOfObject.add(null));
        assertThat("should match", listOfObject.add(42));
        assertThat("should match", listOfObject.add(42L));
        assertThat("should match", listOfObject.add("a string"));
    }

    @Test
    public void any_object_matcher() throws Exception {
        when(listOfObject.add(Mockito.anyObject())).thenReturn(true);

        assertThat("should match", listOfObject.add(null));
        assertThat("should match", listOfObject.add(new Object()));
        assertThat("should match", listOfObject.add("a string"));
    }
}
