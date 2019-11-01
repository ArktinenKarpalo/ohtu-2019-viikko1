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
    public void merkkijonoesitys() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void otaLiikaa() {
        varasto.lisaaVarastoon(5.0);
        assertEquals(5.0, varasto.otaVarastosta(10.0), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaNeg() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-1);
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);

        assertEquals(5.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysHukka() {
        varasto.lisaaVarastoon(500);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysNeg() {
        varasto.lisaaVarastoon(-10);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriVarastoAlkusaldoNeg() {
        assertEquals(0.0, new Varasto(5, -5).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriVarastoAlkusaldoOk() {
        assertEquals(3.0, new Varasto(5, 3).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriVarastoAlkusaldoHukka() {
        assertEquals(5.0, new Varasto(5, 20).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriKayttokelvotonVarastoAlkusaldoHukka() {
        assertEquals(0.0, new Varasto(-5, 20).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriKayttokelvotonVarasto() {
        assertEquals(0.0, new Varasto(-10).getSaldo(), vertailuTarkkuus);
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

}