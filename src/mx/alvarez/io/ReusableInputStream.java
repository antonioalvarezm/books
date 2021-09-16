package mx.alvarez.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ReusableInputStream extends InputStream {

	private InputStream is;
	private ByteArrayOutputStream output;
	private ByteBuffer buffer;

	public ReusableInputStream() {
	}

	public ReusableInputStream(InputStream is) throws IOException {
		this.is = is;
		this.output = new ByteArrayOutputStream(is.available());
	}

	@Override
	public int read() throws IOException {
		byte[] b = new byte[1];
		read(b, 0, 1);
		return b[0];
	}

	public int read(byte[] bytes) throws IOException {
		return read(bytes, 0, bytes.length);
	}

	public int read(byte[] bytes, int offset, int length) throws IOException {
		if (buffer == null) {
			int read = is.read(bytes, offset, length);

			if (read <= 0) {
				is.close();
				is = null;
				buffer = ByteBuffer.wrap(output.toByteArray());
				output = null;
				return -1;
			} else {
				output.write(bytes, offset, read);
				return read;
			}
		} else {
			int read = Math.min(length, buffer.remaining());

			if (read <= 0) {
				buffer.flip();
				return -1;
			} else {
				buffer.get(bytes, offset, read);
				return read;
			}
		}
	}

}
