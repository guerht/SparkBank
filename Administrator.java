public final class Administrator {
	private static String adminUsername = "guerht";
	private static int passLength;
	private static char[] adminPassword;
	public static void createPassword(int rand) {
		resetPasswordLength(rand);
		adminPassword = new char[passLength];
		setPassword();
	}
	public static void resetPasswordLength(int rand) {
		passLength = rand;
	}
	public static void setPassword() {
		for(int i = 0; i < adminPassword.length; i++) {
			adminPassword[i] = (char) ((int) (Math.random()*94)+33);
		}
	}
	public static String getUsername() {
		return adminUsername;
	}
	public static char[] getPassword() {
		return adminPassword;
	}
	/*public static String getString() {
		String pass = new String("");
		for(int i = 0; i < adminPassword.length; i++) {
			pass += new Character(adminPassword[i]).toString();
		}
		return pass;
	}*/
}