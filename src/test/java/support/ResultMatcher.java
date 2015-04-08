package support;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import business.Result;

public class ResultMatcher extends TypeSafeDiagnosingMatcher<Result> {

    private final Matcher<String> messageMatcher;

    public ResultMatcher(Matcher<String> messageMatcher) {
        this.messageMatcher = messageMatcher;
    }

    public static Matcher<Result> message(Matcher<String> messageMatcher) {
        return new ResultMatcher(messageMatcher);
    }

    @Override
    protected boolean matchesSafely(Result item, Description mismatchDescription) {
        boolean messageMatches = messageMatcher.matches(item.message);
        if (!messageMatches) {
            messageMatcher.describeMismatch(item.message, mismatchDescription);
        }
        return messageMatches;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("result with message ").appendDescriptionOf(messageMatcher);
    }


}
