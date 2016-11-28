package sample;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@Ignore
public class TwoDotXTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private List<String> list;

    @Test
    public void hint_on_unused_stubbing() throws Exception {
        when(list.get(anyInt())).thenReturn("something");
        when(list.add(anyString())).thenReturn(true);
    }

    private static abstract class Abstract {

        public Abstract() {

        }
    }

    @Test
    public void spy_on() throws Exception {
        assertThat(Mockito.spy(Abstract.class), notNullValue());
    }

}
