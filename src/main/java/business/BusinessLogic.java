package business;

import java.util.function.Function;

public class BusinessLogic {
    private Function<Result, Void> resultCallback;

    public void onSuccess(Function<Result, Void> resultCallback) {
        this.resultCallback = resultCallback;
    }

    public void runLogic() {
        resultCallback.apply(new Result("Everything was fine"));
    }
}
