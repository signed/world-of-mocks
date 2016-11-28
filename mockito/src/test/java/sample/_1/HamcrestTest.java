package sample._1;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HamcrestTest {

    @SuppressWarnings("unchecked")
    private final List<String> list = mock(List.class);

    @Test
    public void name() throws Exception {
        when(list.add(Mockito.argThat(equalTo("example")))).thenReturn(true);

        assertThat("Should not match", !list.add("unknown"));
        assertThat("Should match", list.add("example"));
    }
}
