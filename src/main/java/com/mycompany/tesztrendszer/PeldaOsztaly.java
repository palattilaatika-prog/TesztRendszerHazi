/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tesztrendszer;

/**
 *
 * @author Denes
 */
public class PeldaOsztaly {
    public int osszead(int a, int b) {
        return a + b;
    }

    public int osztas(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException(
                "Nullával nem lehet osztani."
            );
        }

        return a / b;
    }

    public boolean pozitiv(int szam) {
        return szam > 0;
    }
}
