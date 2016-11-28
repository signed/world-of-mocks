package sample;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Ignore
public class TwoDotXTest {

    private static abstract class Abstract {

        public Abstract() {

        }
    }

    @Test
    public void spy_on() throws Exception {
        assertThat(Mockito.spy(Abstract.class), notNullValue());
    }

}
