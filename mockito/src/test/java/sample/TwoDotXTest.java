package sample;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public class TwoDotXTest {

    private interface InterfaceWithDefaultMethod {
        default String hardCodedDefault() {
            return "Hello " + audience();
        }

        String audience();
    }

    @Test
    public void support_mocking_of_default_implementations_in_interfaces() throws Exception {
        InterfaceWithDefaultMethod mock = mock(InterfaceWithDefaultMethod.class);
        when(mock.audience()).thenReturn("world");
        given(mock.hardCodedDefault()).willCallRealMethod();

        assertThat(mock.hardCodedDefault(), equalTo("Hello world"));
    }
}
