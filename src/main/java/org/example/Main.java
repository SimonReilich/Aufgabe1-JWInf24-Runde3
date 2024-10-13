package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args)
            throws IOException
    {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Anzahl der Interessenten: ");
        int interested_parties = Integer.parseInt(reader.readLine());
        System.out.print("\n");

        System.out.print("Höhe des Grundstücks in Metern: ");
        int height = Integer.parseInt(reader.readLine());
        System.out.print("\n");

        System.out.print("Breite des Grundstücks in Metern: ");
        int width = Integer.parseInt(reader.readLine());
        System.out.print("\n");

        reader.close();

        List<int[]> l = new ArrayList<>();

        for (int i = 1; i <= interested_parties; i++) {
            for (int j = Math.ceilDiv(interested_parties, i); i * j <= interested_parties * 1.1; j++) {
                l.add(new int[] {i, j});
            }
        }

        int[] solution = l.stream()
                .filter(p -> p[0] * p[1] != 0)
                .filter(p -> p[0] * p[1] >= interested_parties && p[0] * p[1] <= interested_parties * 1.1)
                .min(Comparator.comparingInt(p -> Math.max((width / p[0]) / Math.ceilDiv(height, p[1]), (height / p[1]) / Math.ceilDiv(width, p[0])))).stream().toList().getFirst();

        System.out.println("Ideale Aufteilung: " + solution[1] + " Grundstücke in der Höhe und " + solution[0] + " Grundstücke in der Breite");
    }
}