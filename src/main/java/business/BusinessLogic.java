package business;

import javafx.util.Callback;

public class BusinessLogic {
    private Callback<Result, Void> resultCallback;

    public void onSuccess(Callback<Result, Void> resultCallback) {

        this.resultCallback = resultCallback;
    }

    public void runLogic() {
        resultCallback.call(new Result("Everything was fine"));
    }
}
