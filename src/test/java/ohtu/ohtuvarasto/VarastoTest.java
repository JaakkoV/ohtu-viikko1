package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriVirheellinen() {
        Varasto tmp = new Varasto(-1.0);
        assertEquals(0.0, tmp.getTilavuus(), 0.1);
    }

    @Test
    public void kuormitettuKonstruktori() {
        Varasto tmp = new Varasto(100.0, 10.0);
        assertEquals(100.0, tmp.getTilavuus(), vertailuTarkkuus);
        Varasto tmp2 = new Varasto(-1.0, 100.0);
        assertEquals(0.0, tmp2.getTilavuus(), vertailuTarkkuus);
        Varasto tmp3 = new Varasto(100.0, -100);
        assertEquals(0.0, tmp3.getSaldo(), vertailuTarkkuus);
        Varasto tmp4 = new Varasto(100.0, 10);
        assertEquals(10.0, tmp4.getSaldo(), vertailuTarkkuus);
        Varasto tmp5 = new Varasto(100.0, 110);
        assertEquals(100.0, tmp5.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoon() {
        varasto.lisaaVarastoon(-1.0);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        varasto.lisaaVarastoon(10000.0);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastosta() {
        varasto.lisaaVarastoon(9.0);
        assertEquals(0.0, varasto.otaVarastosta(-1.0), vertailuTarkkuus);
        assertEquals(9.0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(9.0, varasto.otaVarastosta(10.0), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testToString() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
}
