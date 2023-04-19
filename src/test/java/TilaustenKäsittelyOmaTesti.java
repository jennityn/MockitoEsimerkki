import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TilaustenKäsittelyOmaTesti {

    private TilaustenKäsittely tilaustenKäsittely;
    private Asiakas asiakas;
    private Tuote tuote;
    private float alkuSaldo;
    private float alennus;
    private float loppuSaldo;

    @Mock
    private IHinnoittelija hinnoittelijaMock;

    @BeforeEach
    public void setup() {
        alkuSaldo = 200.0f;
        asiakas = new Asiakas(alkuSaldo);
        tilaustenKäsittely = new TilaustenKäsittely();
        hinnoittelijaMock = mock(IHinnoittelija.class);
        tilaustenKäsittely.setHinnoittelija(hinnoittelijaMock);
        alennus = 20.0f;
    }

    @Test
    public void testHintaAlle100() {
        tuote = new Tuote("Halpa", 80);
        loppuSaldo = alkuSaldo - (tuote.getHinta() * (1 - alennus / 100));
        Tilaus tilaus = new Tilaus(asiakas, tuote);
        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(20.0f);
        tilaustenKäsittely.käsittele(tilaus);
        assertEquals(loppuSaldo, asiakas.getSaldo());
    }

    @Test
    public void testHinta100() {
        tuote = new Tuote("Tasan", 100);
        loppuSaldo = alkuSaldo - (tuote.getHinta() * (1 - alennus / 100));
        Tilaus tilaus = new Tilaus(asiakas, tuote);
        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(20.0f);
        tilaustenKäsittely.käsittele(tilaus);
        assertEquals(loppuSaldo, asiakas.getSaldo());
    }

    @Test
    public void testKäsittele_TuotteenHintaSataTaiYli() {
        tuote = new Tuote("Kallis", 120);
        loppuSaldo = alkuSaldo - (tuote.getHinta() * (1 - alennus / 100));
        Tilaus tilaus = new Tilaus(asiakas, tuote);
        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(20.0f);
        tilaustenKäsittely.käsittele(tilaus);
        assertEquals(loppuSaldo, asiakas.getSaldo());
    }
}
