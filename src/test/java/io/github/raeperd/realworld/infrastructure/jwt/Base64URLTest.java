package io.github.raeperd.realworld.infrastructure.jwt;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Random;

import static io.github.raeperd.realworld.infrastructure.jwt.Base64URL.base64URLFromString;
import static io.github.raeperd.realworld.infrastructure.jwt.Base64URL.stringFromBase64URL;
import static org.assertj.core.api.Assertions.assertThat;

class Base64URLTest {

    private final static String RAW_STRING = "something";
    private final static String ENCODED_STRING = "c29tZXRoaW5n";

    @Test
    void when_encode_return_expected_string() {
        assertThat(base64URLFromString(RAW_STRING)).isEqualTo(ENCODED_STRING);
    }

    @Test
    void when_decode_return_expected_string() {
        assertThat(stringFromBase64URL(ENCODED_STRING)).isEqualTo(RAW_STRING);
    }

    @Test
    void when_encode_and_then_decode_expect_same() {
        final var rawString = generateRandomString();

        final var encodedString = base64URLFromString(rawString);
        assertThat(stringFromBase64URL(encodedString)).isEqualTo(rawString);
    }

    private String generateRandomString() {
        final var bytes = new byte[7];
        new Random().nextBytes(bytes);
        // return new String(bytes);
        String randomString
            = new String(bytes, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {

            char ch = randomString.charAt(k);

            if (((ch >= 'a' && ch <= 'z')
                 || (ch >= 'A' && ch <= 'Z')
                 || (ch >= '0' && ch <= '9'))
                ) {
                r.append(ch);
            }
        }

        // return the resultant string
        return r.toString();
    }

}
