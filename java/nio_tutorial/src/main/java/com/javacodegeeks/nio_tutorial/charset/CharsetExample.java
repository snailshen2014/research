package com.javacodegeeks.nio_tutorial.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public final class CharsetExample {

    private CharsetExample() {
	throw new IllegalStateException("Instantiation not allowed");
    }
    
    public static void main(final String [] args) {
	final Charset defaultCharset = Charset.defaultCharset();
	final String text = "Lorem ipsum";
			
	final ByteBuffer bufferA = ByteBuffer.wrap(text.getBytes());	
	final ByteBuffer bufferB = defaultCharset.encode(text);
			
	final String a = new String(bufferA.array());
	final CharBuffer charBufferB = defaultCharset.decode(bufferB);
			
	System.out.println(a);
	System.out.println(new String(charBufferB.array()));
    }    
}
