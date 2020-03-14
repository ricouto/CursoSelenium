package areaEstudoAutomacao;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestMessageBuilder {

    @Test
    public void testNameMkyong() {

        MessageBuilder obj = new MessageBuilder();
        assertEquals("Hello mkyong", obj.getMessage("mkyong"));

    }

}