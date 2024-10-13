# Aufgabe 1 - Quadratisch, praktisch, grün

# Lösungsidee

Um “möglichst quadratisch” zu sein, soll das Verhältnis der Seitenlängen der Rechtecke möglichst nahe an $1$ sein. Mathematisch formuliert: minimiere $max\{ {b/x \over d/y}; {d/y \over b/x } \}$, wobei $x$ die Anzahl an Unterteilungen in der Breite ($b$) und $y$ die Anzahl an Unterteilungen in der Tiefe ($d$) des Grundstücks von Herr Grün.

Als ideale Lösung wird das Tupel $(x; y)$ angesehen, das $max\{ {b/x \over d/y}; {d/y \over b/x } \}$ minimiert und für das $\ Interessenten \leq x * y \leq \ Interessenten * 1,1$ gilt.

Der naivest Ansatz ist dabei, einfach alle Paare $(x; y)$ durchzuprobieren

# Umsetzung

## Algorithmus

*Auf die Dokumentation des Einlesens der Daten wird verzichtet*

## Analyse

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
				p[0] * p[1] >= interested_parties && p[0] * p[1] <= interested_parties * 1.1)
		.min(Comparator.comparingInt(p -> 
				Math.max((width / p[0]) / Math.ceilDiv(height, p[1]), 
						(height / p[1]) / Math.ceilDiv(width, p[0])))).stream().toList().getFirst();

System.out.println("Ideale Aufteilung: " + solution[1] + 
		" Grundstücke in der Höhe und " + solution[0] + " Grundstücke in der Breite");
```
