package org.misalen.mq;

import java.io.Serializable;

public class Message  implements Serializable{
	public String a;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [a=" + a + "]";
	}

}
