package uqbar.android.mvc.binding;

public class ProgramException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProgramException(Exception e) {
		super(e);
	}

	public ProgramException(String message) {
		super(message);
	}

	public ProgramException(String message, Exception e) {
		super(message, e);
	}

	public static ProgramException wrap(Exception e) {
		return new ProgramException(e);
	}

	public static void assertNotNull(Object o, String message) {
		if (o == null) {
			throw new ProgramException(message);
		}
	}

}
