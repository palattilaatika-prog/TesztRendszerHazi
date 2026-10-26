/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tesztrendszer;
import java.lang.reflect.*;
import java.util.*;
/**
 *
 * @author Denes
 */
public class TesztFuttato {
    public void futtat(Class<?> tesztOsztaly) {
        int sikeres = 0;
        int bukott = 0;
        int hibas = 0;

        long kezdet = System.nanoTime();

        List<Method> tesztek = new ArrayList<>();

        for (Method m : tesztOsztaly.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Teszt.class)) {
                tesztek.add(m);
            }
        }

        tesztek.sort(Comparator.comparing(Method::getName));

        System.out.println("Tesztosztály: " + tesztOsztaly.getSimpleName());
        System.out.println("Talált tesztek: " + tesztek.size());
        System.out.println();

        for (Method teszt : tesztek) {
            long tesztKezdet = System.nanoTime();

            Object peldany = null;
            Throwable eredetiHiba = null;
            boolean vartaKivetel = teszt.isAnnotationPresent(
                VartKivetel.class
            );

            try {
                peldany = tesztOsztaly
                    .getDeclaredConstructor()
                    .newInstance();

                for (Method m : tesztOsztaly.getDeclaredMethods()) {
                    if (m.isAnnotationPresent(Elotte.class)) {
                        m.setAccessible(true);
                        m.invoke(peldany);
                    }
                }

                teszt.setAccessible(true);
                teszt.invoke(peldany);

            } catch (InvocationTargetException e) {
                eredetiHiba = e.getCause();

            } catch (Throwable e) {
                eredetiHiba = e;

            } finally {
                if (peldany != null) {
                    for (Method m : tesztOsztaly.getDeclaredMethods()) {
                        if (m.isAnnotationPresent(Utana.class)) {
                            try {
                                m.setAccessible(true);
                                m.invoke(peldany);
                            } catch (InvocationTargetException e) {
                                if (eredetiHiba == null) {
                                    eredetiHiba = e.getCause();
                                }
                            } catch (Throwable e) {
                                if (eredetiHiba == null) {
                                    eredetiHiba = e;
                                }
                            }
                        }
                    }
                }
            }

            VartKivetel vart = teszt.getAnnotation(VartKivetel.class);

            if (vart!=null) {
                if (eredetiHiba == null) {
                    eredetiHiba = new AssertHiba(
                        "Várt kivétel: "
                        + vart.value().getSimpleName()
                        + ", de nem keletkezett kivétel."
                    );
                } else if (vart.value().isInstance(eredetiHiba)) {
                    eredetiHiba = null;
                } else {
                    eredetiHiba = new AssertHiba(
                        "Várt kivétel: " + vart.value().getSimpleName()
                        + ", kapott: "
                        + eredetiHiba.getClass().getSimpleName()
                    );
                }
            }

            long ido = (System.nanoTime() - tesztKezdet) / 1_000_000;

            if (eredetiHiba == null) {
                sikeres++;
                System.out.println(
                    "✔ SIKERES: " + teszt.getName()
                    + " (" + ido + " ms)"
                );
            } else if (eredetiHiba instanceof AssertHiba) {
                bukott++;
                System.out.println(
                    "✘ BUKOTT: " + teszt.getName()
                    + " - " + eredetiHiba.getMessage()
                );
            } else {
                hibas++;
                System.out.println(
                    "✘ HIBA: " + teszt.getName()
                    + " - " + eredetiHiba
                );
            }
        }

        long osszIdo = (System.nanoTime() - kezdet) / 1_000_000;

        System.out.println("\n--- ÖSSZESÍTÉS ---");
        System.out.println("Sikeres: " + sikeres);
        System.out.println("Bukott: " + bukott);
        System.out.println("Hibás: " + hibas);
        System.out.println("Összes futási idő: " + osszIdo + " ms");
    }
}
