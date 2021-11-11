// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24




#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include <algorithm>
#include <string>
#include "ConjuntosDisjuntos.h"

using namespace std;

bool resuelveCaso() {

	int f, c;
	char car;
	std::cin >> f >> c;

	if (!std::cin)return false;
	
	std::cin.get(car);

	std::vector<bool> estadoCasillas(f*c, false);
	ConjuntosDisjuntos conjuntos(f*c);
	int maximo = 0;
	int cont = 0;

	string linea;
	getline(cin, linea);

	for (int i = 0; i < c; ++i) {
		if (linea[i] == '#') {
			estadoCasillas[cont] = true;
			if (i > 0 && estadoCasillas[i] == estadoCasillas[i - 1]) {
				conjuntos.unir(i, i - 1);
			}
			maximo = std::max(maximo, conjuntos.tam(i));
		}
		++cont;
	}

	for (int i = 1; i < f; ++i) {
		getline(cin, linea);
		for (int j = 0; j < c; ++j) {
			if (linea[j] == '#') {
				estadoCasillas[cont] = true;

				if (estadoCasillas[cont] == estadoCasillas[cont - c])//arriba
					conjuntos.unir(cont, cont - c);
				if (j < c - 1 && estadoCasillas[cont] == estadoCasillas[cont - c + 1])//arriba dcha
					conjuntos.unir(cont, cont - c + 1);

				if (j > 0 && estadoCasillas[cont] == estadoCasillas[cont - 1])//izqda -----------el primero de la fila no tiene elemento a su izquierda
					conjuntos.unir(cont, cont - 1);

				if (j > 0 && estadoCasillas[cont] == estadoCasillas[cont - c - 1])//arriba izda-----------el primero de la fila no tiene elemento a su izquierda
					conjuntos.unir(cont, cont - c - 1);

				maximo = std::max(maximo, conjuntos.tam(cont));
			}
			++cont;
		}
	}
	std::cout << maximo;

	int N;
	std::cin >> N;
	int fila, col;

	for (int i = 0; i < N; ++i) {
		std::cin >> fila >> col;
		int k = c * (fila - 1) + col - 1;
		estadoCasillas[k] = true;

		if (fila > 1 && estadoCasillas[k - c]) {
			conjuntos.unir(k, k - c);
		}
		if (fila < f && estadoCasillas[k + c]) {
			conjuntos.unir(k, k + c);
		}
		if (col < c && estadoCasillas[k + 1]) {
			conjuntos.unir(k, k + 1);
		}
		if (col > 1 && estadoCasillas[k - 1]) {
			conjuntos.unir(k, k - 1);
		}

		if (fila < f && col < c && estadoCasillas[k + c + 1]) {
			conjuntos.unir(k, k + c + 1);
		}
		if (fila < f && col > 1 && estadoCasillas[k + c - 1]) {
			conjuntos.unir(k, k + c - 1);
		}

		if (fila > 1 && col < c && estadoCasillas[k - c + 1]) {
			conjuntos.unir(k, k - c + 1);
		}
		if (fila > 1 && col > 1 && estadoCasillas[k - c - 1]) {
			conjuntos.unir(k, k - c - 1);
		}

		maximo = std::max(maximo, conjuntos.tam(k));
		std::cout << " " << maximo;

		std::cout << '\n';
		return true;
	}
}
int main() {
	// ajuste para que cin extraiga directamente de un fichero
#ifndef DOMJUDGE
	std::ifstream in("datos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif

	while (resuelveCaso());

	// restablecimiento de cin
#ifndef DOMJUDGE
	std::cin.rdbuf(cinbuf);
	system("pause");
#endif
	return 0;
}