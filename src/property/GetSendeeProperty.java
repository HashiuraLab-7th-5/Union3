package property;

import java.io.File;
import java.io.IOException;

public class GetSendeeProperty extends ViolationProperty{

	private static GetSendeeProperty singleton = new GetSendeeProperty();
	private final String file = "ErrorCode_Sendee";

	public static ViolationProperty getInstance() {
		return singleton;
	}

	public GetSendeeProperty() {
		fileName = this.file;
	}

	@Override
	void next(ViolationPropertyContext context) {
		// TODO 自動生成されたメソッド・スタブ
		context.changeState(GetSenderProperty.getInstance());
	}

}
