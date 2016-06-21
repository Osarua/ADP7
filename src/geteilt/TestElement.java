package geteilt;

import org.junit.Test;

public class TestElement {
	@Test
	public void testLog () {
		Element element = new Element ("111",Status.BELEGT);
		element.logHinzu("Hallo");
		element.logHinzu("Hallo");
		System.out.println (element.getLogs());
	}
}
