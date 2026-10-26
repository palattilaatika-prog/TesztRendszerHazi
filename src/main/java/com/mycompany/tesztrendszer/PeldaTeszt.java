/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tesztrendszer;

/**
 *
 * @author Denes
 */
public class PeldaTeszt {
    private PeldaOsztaly pelda;

    @Elotte
    public void init() {
        pelda = new PeldaOsztaly();
        System.out.println("  [Elotte] Teszt inicializálása");
    }

    @Utana
    public void takaritas() {
        System.out.println("  [Utana] Teszt befejezése");
    }

    @Teszt
    public void osszeadasPozitivSzamokkal() {
        Assert.egyenlo(5, pelda.osszead(2, 3));
    }

    @Teszt
    public void osszeadasNegativSzamokkal() {
        Assert.egyenlo(-5, pelda.osszead(-2, -3));
    }

    @Teszt
    public void osztas() {
        Assert.egyenlo(5, pelda.osztas(10, 2));
    }

    @Teszt
    public void pozitivSzamEllenorzese() {
        Assert.igaz(pelda.pozitiv(10));
    }

    @Teszt
    public void nullaNemPozitiv() {
        Assert.hamis(pelda.pozitiv(0));
    }

    @Teszt
    @VartKivetel(IllegalArgumentException.class)
    public void osztasNullaval() {
        pelda.osztas(10, 0);
    }

    @Teszt
    public void kivetelEllenorzeseAsserttal() {
        Assert.dobja(
            IllegalArgumentException.class,
            () -> pelda.osztas(10, 0)
        );
    }

    // SZÁNDÉKOSAN BUKÓ TESZT 1
    @Teszt
    public void hibasOsszeadasElvartEredmenye() {
        Assert.egyenlo(100, pelda.osszead(2, 3));
    }

    // SZÁNDÉKOSAN BUKÓ TESZT 2
    @Teszt
    public void hibasPozitivEllenorzes() {
        Assert.igaz(pelda.pozitiv(-5));
    }
}
