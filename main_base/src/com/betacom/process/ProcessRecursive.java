package com.betacom.process;

import interfaces.ProcessInterface;

public class ProcessRecursive implements ProcessInterface {

	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessRecursive");

		int number = 5, result = 0;

		result = factorial(number);
		System.out.println("Factorial di " +number + "=" +result );


		return false;
	}

	private int factorial (int n) {
		System.out.println("....factorial " + n);
		if (n != 0)
			return n = n*factorial(n-1);
		else
			return 1;
	}
	/*
	 * SPIEGAZIONE DA CLAUDE
	 * 
	 * private int factorial (int n) {
    System.out.println("....factorial " + n);
    if (n != 0)
        return n = n*factorial(n-1);  // Chiamata ricorsiva
    else
        return 1;  // Caso base
}
```

### Come Funziona la Ricorsione

	 **La ricorsione è quando un metodo chiama se stesso.** Ecco come:

1. `factorial(5)` viene chiamato
2. Controlla: `5 != 0`? Sì
3. **Deve calcolare**: `5 * factorial(4)` 
4. Ma prima di moltiplicare, **deve sapere quanto vale** `factorial(4)`
5. Quindi **sospende** l'esecuzione e chiama `factorial(4)`

Questo processo continua creando una "pila" di chiamate:
```
factorial(5) aspetta → 5 * factorial(4)
  factorial(4) aspetta → 4 * factorial(3)
    factorial(3) aspetta → 3 * factorial(2)
      factorial(2) aspetta → 2 * factorial(1)
        factorial(1) aspetta → 1 * factorial(0)
          factorial(0) → ritorna 1 (caso base!)
```

## Fase di "Risalita" - I Calcoli Effettivi

Quando `factorial(0)` ritorna `1`, la pila si "srotola":
```
factorial(0) = 1
factorial(1) = 1 * 1 = 1
factorial(2) = 2 * 1 = 2
factorial(3) = 3 * 2 = 6
factorial(4) = 4 * 6 = 24
factorial(5) = 5 * 24 = 120
```

## Output del Programma
```
Begin ProcessRecursive
....factorial 5
....factorial 4
....factorial 3
....factorial 2
....factorial 1
....factorial 0
Factorial di 5=120

Importante: il metodo NON ritorna "se stesso", ma ritorna il RISULTATO della chiamata a se stesso.
	 */
}


