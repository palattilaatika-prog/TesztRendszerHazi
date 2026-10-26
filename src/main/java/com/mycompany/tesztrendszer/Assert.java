/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tesztrendszer;
import java.util.Objects;
/**
 *
 * @author Denes
 */
public class Assert {
    public static void egyenlo(Object vart, Object kapott) {
        if (!Objects.equals(vart, kapott)) {
            throw new AssertHiba(
                "Várt: " + vart + ", kapott: " + kapott
            );
        }
    }

    public static void igaz(boolean feltetel) {
        if (!feltetel) {
            throw new AssertHiba("Igaz értéket vártunk, de hamis lett.");
        }
    }

    public static void hamis(boolean feltetel) {
        if (feltetel) {
            throw new AssertHiba("Hamis értéket vártunk, de igaz lett.");
        }
    }

    public static void nemNull(Object objektum) {
        if (objektum == null) {
            throw new AssertHiba("Az objektum null, pedig nem lehetne.");
        }
    }

    public static void dobja(
            Class<? extends Throwable> vart,
            Runnable muvelet) {

        try {
            muvelet.run();
        } catch (Throwable kapott) {
            if (vart.isInstance(kapott)) {
                return;
            }

            throw new AssertHiba(
                "Várt kivétel: " + vart.getSimpleName()
                + ", kapott: " + kapott.getClass().getSimpleName()
            );
        }

        throw new AssertHiba(
            "Várt kivétel: " + vart.getSimpleName()
            + ", de nem keletkezett kivétel."
        );
    }
}
