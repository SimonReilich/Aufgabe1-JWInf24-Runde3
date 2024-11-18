# Aufgabe 1 - Quadratisch, praktisch, grün

# Lösungsidee

Um “möglichst quadratisch” zu sein, soll das Verhältnis der Seitenlängen der Rechtecke möglichst nahe an $1$ sein. Mathematisch formuliert: minimiere $max\{ {b/x \over d/y}; {d/y \over b/x } \}$, wobei $x$ die Anzahl an Unterteilungen in der Breite ($b$) und $y$ die Anzahl an Unterteilungen in der Tiefe ($d$) des Grundstücks von Herr Grün.

Als ideale Lösung wird das Tupel $(x; y)$ angesehen, das $max\{ {b/x \over d/y}; {d/y \over b/x } \}$ minimiert und für das $\# Interessenten \leq x * y \leq \# Interessenten * 1,1$ gilt.

Der naivest Ansatz ist dabei, einfach alle Paare $(x; y)$ durchzuprobieren, damit kann immer die optimale Lösung ermittelt werden

# Umsetzung

## Algorithmus

*Die Lösungsidee wurde in Java umgesetzt, auf die Dokumentation des Einlesens der Daten wird verzichtet*

Der Code beginnt damit, den Benutzer nach der Anzahl der Interessenten, der Höhe und der Breite des Grundstücks zu fragen. Die eingegebenen Werte werden als Integer gespeichert.

Eine Liste namens `l` wird erstellt, um alle möglichen Aufteilungen des Grundstücks zu speichern. Jede Aufteilung wird als ein Array mit zwei Elementen dargestellt: der Anzahl der Teilgrundstücke in Höhe und Breite. Die beiden verschachtelten `for`-Schleifen erzeugen alle möglichen Kombinationen von Teilgrundstücken, wobei sichergestellt wird, dass die Gesamtzahl der Teilgrundstücke mindestens der Anzahl der Interessenten und maximal der Anzahl an Interessenten zum Quadrat entspricht.

Nun wird aus der Liste der möglichen Aufteilungen ein `Stream` erstellt. 

Zuerst wird mit `.filter()` nach den Tupeln gefiltert, die der Bedingung der Aufgabenstellung entsprechen (die untere Grenze muss eigentlich nicht mehr überprüft werden, da sie durch den vorherigen Schritt bereits gewährleistet ist), die also mindestens die Anzahl der Interessenten und höchstens 110% der Interessenten ergeben.

Anschließend wird die Aufteilung gefunden, die $max\{{b/x \over d/y}; {d/y \over b/x }\}$ minimiert. Dies geschieht unter Verwendung der `.min()` Funktion des Streams, sowie `Comparator.comparingInt()` und `Math.max()` 

Zum Schluss wird der Stream wieder in eine Liste umgewandelt und das erste Element wird ausgegeben, da es potentiell auch mehrere optimale Lösungen geben kann (diese wären dann alle in der Liste enthalten, es macht keinen Unterschied, welches ausgegeben wird).

# Beispiele

## Beispiel 0

<aside>
📥

Eingabe: 

- Interessenten: 23
- Höhe: 42
- Breite: 66
</aside>

<aside>
📤

Ausgabe: 

5 Grundstücke in der Höhe und 

5 Grundstücke in der Breite

→ 25 Grundstücke

</aside>

## Beispiel 1

<aside>
📥

Eingabe: 

- Interessenten: 19
- Höhe: 15
- Breite: 12
</aside>

<aside>
📤

Ausgabe: 

5 Grundstücke in der Höhe und 

4 Grundstücke in der Breite

→ 20 Grundstücke

</aside>

## Beispiel 2

<aside>
📥

Eingabe: 

- Interessenten: 36
- Höhe: 55
- Breite: 77
</aside>

<aside>
📤

Ausgabe: 

6 Grundstücke in der Höhe und 

6 Grundstücke in der Breite

→ 36 Grundstücke

</aside>

## Beispiel 3

<aside>
📥

Eingabe: 

- Interessenten: 101
- Höhe: 15
- Breite: 15
</aside>

<aside>
📤

Ausgabe: 

13 Grundstücke in der Höhe und 

8 Grundstücke in der Breite

→ 104 Grundstücke

</aside>

## Beispiel 4

<aside>
📥

Eingabe: 

- Interessenten: 1200
- Höhe: 37
- Breite: 2000
</aside>

<aside>
📤

Ausgabe: 

5 Grundstücke in der Höhe und 

251 Grundstücke in der Breite

→ 1255 Grundstücke

</aside>

## Beispiel 5

<aside>
📥

Eingabe: 

- Interessenten: 35000
- Höhe: 365
- Breite: 937
</aside>

<aside>
📤

Ausgabe: 

121 Grundstücke in der Höhe und 

290 Grundstücke in der Breite

→ 35090 Grundstücke

</aside>

# Quellcode

```java
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
		for (int j = interested_parties / i; j <= interested_parties; j++) {
				l.add(new int[] {i, j});
		}
}

int[] solution = l.stream()
		.filter(p -> 
				p[0] * p[1] >= interested_parties && 
				p[0] * p[1] <= interested_parties * 1.1)
		.min(Comparator.comparingInt(p -> 
				Math.max((width / p[0]) / Math.ceilDiv(height, p[1]), 
						(height / p[1]) / Math.ceilDiv(width, p[0]))))
						.stream().toList().getFirst();

System.out.println("Ideale Aufteilung: " + solution[1] + 
		" Grundstücke in der Höhe und " + solution[0] + 
		" Grundstücke in der Breite");
```
