package sample._2;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class UnusedStubbingTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private List<String> list;

    @Test
    public void hint_on_unused_stubbing() throws Exception {
        when(list.get(anyInt())).thenReturn("something");
        when(list.add(anyString())).thenReturn(true);
    }

    @Test
    public void do_not_hint_on_unused_stubbing_for_failing_tests() throws Exception {
        when(list.get(anyInt())).thenReturn("something");

        Assert.fail();
    }
}
